package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.opencsv.CSVReader;
import nl.planner.boot.Bootstrap;
import nl.planner.machineLearning.LinearRegression;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    LocationDOA locationDOA;

    private UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(LocationController.class.getName());
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String location(HttpServletRequest request, Model model){
        if (request.getUserPrincipal() == null){
            return "redirect:/";
        }

        String userId = userService.getCurrentUser().getUserId();
        List<Location> locations = ofy().load().type(Location.class).ancestor(Key.create(Person.class, userId)).list();

        model.addAttribute("locations",locations);

        return "locations";
    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model){
        if (request.getUserPrincipal() == null){
            return "redirect:/";
        }
        User user = userService.getCurrentUser();
        String userId = userService.getCurrentUser().getUserId();
        model.addAttribute("person",HomeController.getPersonFromUser(user,userId));

        String name = request.getParameter("Name");
        String adress = request.getParameter("Adress");
        String city = request.getParameter("City");

        locationDOA.createLocation(user,name,adress,city);
        List<Location> locations = locationDOA.listOfLocations(user);

        model.addAttribute("locations",locations);
        return "locations";
    }

    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public String location(@PathVariable String locationId,HttpServletRequest request,Model model){
        if (request.getUserPrincipal() == null){
            return "redirect:/";
        }
        logger.info(locationId);

        List<String[]> forecastMap = doTES();

        User user = userService.getCurrentUser();
        Location location = locationDOA.getLocationFromId(user,locationId);

        model.addAttribute("forecast",forecastMap);
        model.addAttribute("location",location);
        return "location";
    }

    @RequestMapping(value="/location/{locationId}" , method = RequestMethod.POST)
    public String uploadCSV(@PathVariable String locationId,HttpServletRequest request,Model model) throws IOException, ParseException {

        User user = userService.getCurrentUser();

        CSVReader reader = new CSVReader(request.getReader());

        List myEntries = reader.readAll();
        Boolean header = true;

        //ToDo clean this ugly code
        for(int i = 0; i < myEntries.size(); i++){
            String[] l = (String[])myEntries.get(i);
            if(l.length > 1)  {
                if(!header) {
                    //0 index 1:date 2:sales 3:weekday 4:holiday 5:temp 6:weather

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(l[1]);
                    float sales = Float.parseFloat(l[2]);
                    int weekday = Integer.parseInt(l[3]);
                    Boolean holiday = Boolean.parseBoolean(l[4]);
                    int temp = Integer.parseInt(l[5]);
                    float rain = Float.parseFloat(l[6]);
                    // store data in sql database
                    storeSalesItem(date,sales,weekday,holiday,temp,rain,locationId);

                }else{
                    header = false;
                }
            }
        }
        List<String[]> forecastMap = doTES();
        model.addAttribute("forecast",forecastMap);
        return "location";
    }

    private void storeSalesItem( Date date, float sales, int weekday,
                                 Boolean holiday, int temp, float rain, String key){
        String url = Bootstrap.getUrl();
        //0 index 1:date 2:sales 3:weekday 4:holiday 5:temp 6:weather

        final String createVisitSql = "INSERT INTO "+Bootstrap.databaseName
                +" (timestamp,sales, weekday, is_holiday,temperature,residues,location_key)"+
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // set right parameters to query.
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement statementCreateVisit = conn.prepareStatement(createVisitSql)) {
            statementCreateVisit.setDouble(2,sales);
            statementCreateVisit.setDate(1,new java.sql.Date(date.getTime()));
            statementCreateVisit.setInt(3,weekday);
            statementCreateVisit.setBoolean(4,holiday);
            statementCreateVisit.setInt(5,temp);
            statementCreateVisit.setDouble(6,rain);
            statementCreateVisit.setString(7,key);
            statementCreateVisit.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            logger.info("SQL error: "+e.toString());
        }
    }

    private List<String[]> doTES(){
        String url = Bootstrap.getUrl();
        final String getSalesDataQuery = "SELECT * From "+Bootstrap.databaseName;

        List<String[]> forecastMap = new ArrayList<String[]>();
        Double[] forecast = new Double[]{0.0};

        // Do Sql request and process the data
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement statementCreateVisit = conn.prepareStatement(getSalesDataQuery)) {
            ResultSet results = statementCreateVisit.executeQuery();

            ArrayList<Double> salesNumbers = new ArrayList<Double>();
            ArrayList<Date> dateList = new ArrayList<Date>();

            // Store values in the Arrays
            while (results.next()){
                Date date = results.getDate("timestamp");
                dateList.add(date);
                salesNumbers.add(results.getDouble("sales"));
            }
            Double[] data = salesNumbers.toArray(forecast);

            // Do Triple exponential smoothing
            forecast = LinearRegression.forecast(data, 0.1,0.6,0.3,7,30);
            forecastMap = formatForecastOutput(forecast,salesNumbers,dateList);

        }catch(SQLException e) {
            logger.info("SQL error: "+e.toString());
        }
        return forecastMap;
    }

    private List<String[]> formatForecastOutput(Double[] forecast,
                                                ArrayList<Double> salesNumbers,
                                                ArrayList<Date> dateList){

        List<String[]> forecastMap = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(dateList.get(dateList.size() -1));
        for(int i = 0; i < forecast.length; i++){
            String[] map = new String[3];

            // if not predicted set date and sales.
            if(i < salesNumbers.size() -1){
                map[0] = formatter.format(dateList.get(i));
                map[1] = salesNumbers.get(i).toString();

            // We forecasting create a date.
            }else{
                c.add(Calendar.DATE,1);
                map[0] = formatter.format(c.getTime());
                map[1] = "-";
            }
            map[2] = forecast[i].toString();
            forecastMap.add(map);
        }
        return forecastMap;
    }
}
