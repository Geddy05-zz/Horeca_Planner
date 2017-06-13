package nl.planner.web.controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.opencsv.CSVReader;
import nl.planner.boot.SQLDatabase;
import nl.planner.boot.SQLQueries;
import nl.planner.machineLearning.LinearRegression;
import nl.planner.persistence.DAO.EmployeeDAO;
import nl.planner.persistence.DAO.LocationDAO;
import nl.planner.persistence.DAO.LogItemDAO;
import nl.planner.persistence.DAO.PersonDAO;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.enums.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.googlecode.objectify.ObjectifyService.ofy;
import nl.planner.persistence.entity.* ;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class LocationController {

    @Autowired
    private LocationDAO locationDOA;
    @Autowired
    private EmployeeDAO employeeDOA;

    private UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(LocationController.class.getName());
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String location(HttpServletRequest request, Model model) {

//        if (request.getUserPrincipal() == null){
//            return "redirect:/";
////        }
//
//        String userId = userService.getCurrentUser().getUserId();
//        List<Location> locations = ofy().load().type(Location.class).ancestor(Key.create(Person.class, userId)).list();
//
//        model.addAttribute("locations",locations);

        return "locations";
    }

    @RequestMapping(value = "/getLocations", method = RequestMethod.GET)
    public @ResponseBody
    List<Location> getlocations(HttpServletRequest request, Model model) {

        String mail = request.getParameter("userMail");
        List<Location> locations = ofy().load().type(Location.class).ancestor(Key.create(Person.class, mail)).list();

        return locations;
    }

    @RequestMapping(value = "/getLocation", method = RequestMethod.GET)
    public @ResponseBody
    Location getlocation(HttpServletRequest request, Model model) {

        String mail = request.getParameter("userMail");
        Long locationID = Long.parseLong(request.getParameter("locationID"));
        List<Location> locations = ofy().load().type(Location.class).ancestor(Key.create(Person.class, mail)).list();
        Location location = null;
        for (Location location1 : locations) {
            if (location1.getId() == locationID) {
                location = location1;
            }
        }

        return location;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model) {

        String mail = request.getParameter("userID");
        model.addAttribute("person", PersonDAO.getPersonFromUserID(mail));

        String name = request.getParameter("Name");
        String address = request.getParameter("Adress");
        String city = request.getParameter("City");

        locationDOA.createLocation(mail, name, address, city);
        List<Location> locations = locationDOA.listOfLocations(mail);

        model.addAttribute("locations", locations);
        return "locations";
    }

    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public String location(@PathVariable String locationId, HttpServletRequest request, Model model) {

        logger.info(locationId);

        List<String[]> forecastMap = doTES(Long.parseLong(locationId));

        model.addAttribute("forecast", forecastMap);
        model.addAttribute("locationId", locationId);
        return "locationSettings";
    }

    @RequestMapping(value = "/location/getEmployees", method = RequestMethod.GET)
    public @ResponseBody
    List<Employee> getEmployees(HttpServletRequest request) {
        String locationId = request.getParameter("locationID");
        String userID = request.getParameter("userMail");

        Location location = locationDOA.getLocationFromId(userID, locationId);
        logger.info(" get EMPLOYEES !!!!!!!!!!!!!!");

        return location.getEmployees();
    }

    @RequestMapping(value = "/location/addEmployee", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addEmployee(HttpServletRequest request, Model model) {

        // get params from request
        String locationId = request.getParameter("locationID");
        String userID = request.getParameter("userMail");
        String name = request.getParameter("name");
        String[] skillsString = request.getParameterValues("skills");
        int experienceNr = Integer.parseInt(request.getParameter("experience"));
        String[] weekdaysA = request.getParameterValues("availableWeekdays");
        double price = Double.parseDouble(request.getParameter("price"));
        int contractHours = Integer.parseInt(request.getParameter("contractHours"));

        // select boxes input to skill enum.
        Skill[] skills = new Skill[skillsString.length];
        for (int i = 0; i < skillsString.length; i++) {
            skills[i] = Skill.valueOf(Integer.parseInt(skillsString[i]));
        }

        // cast string array to int array without use of java 8
        int[] weekdaysAv = new int[weekdaysA.length];
        for (int i = 0; i < weekdaysA.length; i++) {
            weekdaysAv[i] = Integer.parseInt(weekdaysA[i]);
        }

        // create the user and store in datastore.
        Location location = locationDOA.getLocationFromId(userID, locationId);
        employeeDOA.createEmployee(location, name, price, skills, weekdaysAv, Experience.forValue(experienceNr), contractHours);

    }

    @RequestMapping(value = "/location/deleteEmployee", method = RequestMethod.POST)
    public String deleteEmployee(HttpServletRequest request, Model model) {

        // Get params from request
        String locationId = request.getParameter("locationId");
        String userID = request.getParameter("userID");
        String employeeId = request.getParameter("employeeId");

        Location location = locationDOA.getLocationFromId(userID, locationId);
        Employee employee = location.getEmployeeById(Long.parseLong(employeeId));
        employeeDOA.deleteEmployee(employee, location);

        model.addAttribute("location", location);
        return "redirect:/location/" + location.getId();
    }

    //TODO: deal with correct date front-end and backend
    @RequestMapping(value = "/location/addSales", method = RequestMethod.POST)
    public String  addSalesOfDay(HttpServletRequest request, Model model) throws Exception {

        String locationId = request.getParameter("locationId");
        String UserId = request.getParameter("userID");
        String sales = request.getParameter("number");
        String dateString = request.getParameter("date");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dateString);
        int weekday = 0;
        boolean holiday = true;
        int temp = 15;
        float rain = (float) 0.0;

        storeSalesItem(date, Float.parseFloat(sales), weekday, holiday, temp, rain, locationId);

        LogItemDAO.createLocation(UserId,"Added sales for " + dateString);

        model.addAttribute("location", locationId);
        return "locationSettings";
    }

    @RequestMapping(value = "/location/uploadCSV/{locationId}", method = RequestMethod.POST)
    public String uploadCSV(@PathVariable String locationId, HttpServletRequest request, Model model) throws IOException, ParseException {

        String user = request.getParameter("userID");

        CSVReader reader = new CSVReader(request.getReader());

        List myEntries = reader.readAll();
        Boolean header = true;

        // Read csv and store the values in the SQL datastore
        for (int i = 0; i < myEntries.size(); i++) {
            String[] l = (String[]) myEntries.get(i);
            if (l.length > 1) {
                if (!header) {

                    //0 index 1:date 2:sales 3:weekday 4:holiday 5:temp 6:weather
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(l[1]);
                    float sales = Float.parseFloat(l[2]);
                    int weekday = Integer.parseInt(l[3]);
                    Boolean holiday = Boolean.parseBoolean(l[4]);
                    int temp = Integer.parseInt(l[5]);
                    float rain = Float.parseFloat(l[6]);

                    // store data in sql database
                    storeSalesItem(date, sales, weekday, holiday, temp, rain, locationId);

                } else {
                    header = false;
                }
            }
        }

        model.addAttribute("location", locationId);

        return "locationSettings";
    }


    /**
     * Function store sales data in the SQL Database
     *
     * @param date    date of the data point
     * @param sales   sales in float
     * @param weekday weekday in numbers
     * @param holiday boolean value if is is a holiday or not
     * @param temp    temperature the day of the data point
     * @param rain    amount of rain the at the day
     * @param key     location key for sql database
     */
    private void storeSalesItem(Date date, float sales, int weekday,
                                Boolean holiday, int temp, float rain, String key) {

        String url = SQLDatabase.getUrl();
        //0 index 1:date 2:sales 3:weekday 4:holiday 5:temp 6:weather;

        // set right parameters to query.
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement statementCreateVisit = conn.prepareStatement(SQLQueries.insertSalesData)) {
            statementCreateVisit.setDouble(2, sales);
            statementCreateVisit.setDate(1, new java.sql.Date(date.getTime()));
            statementCreateVisit.setInt(3, weekday);
            statementCreateVisit.setBoolean(4, holiday);
            statementCreateVisit.setInt(5, temp);
            statementCreateVisit.setDouble(6, rain);
            statementCreateVisit.setString(7, key);
            statementCreateVisit.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            logger.info("SQL error: " + e.toString());
        }
    }


    /**
     * Function get data dales data from the sql datastore.
     * Calls the Tripple exponential smoothing method with the data from sql datastore
     *
     * @return list with predicated sales values and real sales values
     */
    public static List<String[]> doTES(Long locationID) {
        String url = SQLDatabase.getUrl();

        List<String[]> forecastMap = new ArrayList<String[]>();
        Double[] forecast = new Double[]{0.0};

        // Do Sql request and process the data
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement statementCreateVisit = conn.prepareStatement(SQLQueries.getSalesDataQuery(locationID))) {

            // Get results
            ResultSet results = statementCreateVisit.executeQuery();

            ArrayList<Double> salesNumbers = new ArrayList<Double>();
            ArrayList<Date> dateList = new ArrayList<Date>();

            // Store values in the Arrays
            while (results.next()) {
                Date date = results.getDate("timestamp");
                dateList.add(date);
                salesNumbers.add(results.getDouble("sales"));
            }
            Double[] data = salesNumbers.toArray(forecast);

            // Do Triple exponential smoothing
            // TODO: develop a function that optimize the parameters for LinearRegression
            forecast = LinearRegression.forecast(data, 0.1, 0.6, 0.3, 7, 30);
            forecastMap = formatForecastOutput(forecast, salesNumbers, dateList);

        } catch (SQLException e) {
            logger.info("SQL error: " + e.toString());
        }
        return forecastMap;
    }

    /**
     * Function that maps date list en list of sales / forecast to a single list.
     *
     * @param forecast     forecasting values
     * @param salesNumbers sales data till now
     * @param dateList     all dates from historic data we used for forecasting
     * @return a list that contains array of string with [date , sales,forecast] values
     */
    private static List<String[]> formatForecastOutput(Double[] forecast,
                                                       ArrayList<Double> salesNumbers,
                                                       ArrayList<Date> dateList) {
        if (dateList.size() < 1) {
            return new ArrayList<>();
        }

        List<String[]> forecastMap = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(dateList.get(dateList.size() - 1));
        for (int i = 0; i < forecast.length; i++) {
            String[] map = new String[3];

            // if not predicted set date and sales.
            if (i < salesNumbers.size() - 1) {
                map[0] = formatter.format(dateList.get(i));
                map[1] = salesNumbers.get(i).toString();

                // We forecasting create a date.
            } else {
                c.add(Calendar.DATE, 1);
                map[0] = formatter.format(c.getTime());
                map[1] = "-";
            }
            map[2] = forecast[i].toString();
            forecastMap.add(map);
        }
        return forecastMap;
    }

    @RequestMapping(value = "/editOrganisation", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateOrganisation(HttpServletRequest request) throws IOException, ParseException {

        // get params from request
        String locationId = request.getParameter("locationId");
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        String postal = request.getParameter("postal");
        String adress = request.getParameter("Adress");
        String city  = request.getParameter("City");
        String mo = request.getParameter("mo");
        String tu = request.getParameter("tu");
        String we = request.getParameter("we");
        String th = request.getParameter("th");
        String fr = request.getParameter("fr");
        String sa = request.getParameter("sa");
        String su = request.getParameter("su");

        LocationDAO.updateLocation(userID, locationId,name,postal,adress,city,mo,tu,we,th,fr,sa,su);

    }
}
