package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import nl.planner.ForecastService;
import nl.planner.boot.Bootstrap;
import nl.planner.boot.WeatherRequest;
import nl.planner.persistence.DAO.LocationDAO;
import nl.planner.persistence.DAO.PersonDAO;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.LogItem;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.entity.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;
import java.util.*;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import static com.googlecode.objectify.ObjectifyService.ofy;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, Model model) throws FileNotFoundException {

        if (request.getUserPrincipal() != null) {
            return "redirect:dashboard";
        }

        model.addAttribute("Title", "Home page");

        return "home";
    }

    @RequestMapping(value = "/getSales")
    public  @ResponseBody List<Location> getSales( HttpServletRequest request) {

        String mail = request.getParameter("userMail");
        String locationID = request.getParameter("locationID");
        Location location = LocationDAO.getLocationFromId(mail,locationID);

        return new ArrayList<>();
    }


    @RequestMapping(value = "/logout")
    public String logout() {

        UserService userService = UserServiceFactory.getUserService();
        String redirectUrl = userService.createLogoutURL("/");

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login( HttpServletRequest request) throws FileNotFoundException {
        logger.info("login");

        String mail = request.getParameter("userMail");
        Person profile = ofy().load().key(
                Key.create(Person.class, mail)).now();

        // returns if the user have a profile or not
        if (profile != null){
            return "0";
        }
        else{
            return "1";
        }
    }

    @RequestMapping(value = "/activate/{userId}")
    public String activateProfile(@PathVariable String userId,HttpServletRequest request) {

        logger.info(userId);
        Person profile = ofy().load().key(
                Key.create(Person.class, userId)).now();

        if (profile != null){
            profile.activateAccount();
            ofy().save().entity(profile).now();
            return "redirect:/dashboard";
        }
        else{
            UserService userService = UserServiceFactory.getUserService();
            String thisUrl = request.getRequestURI()+ "login";
            String login = userService.createLoginURL(thisUrl);
            return "redirect:/"+login;
        }
    }

    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request, Model model) throws Exception {

        // get weather data
        List< Weather>  weather = null;
        try{
            weather = new WeatherRequest().getWeather(52.092876,5.104480);
        }
        catch (Exception e){
            logger.warning(e.getMessage());
        }

        model.addAttribute("weatherForecast",weather);
        return "dashboard";
    }

    @RequestMapping(value = "/dashboard/forecast", method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody List<String[]> forecast (HttpServletRequest request) throws Exception {
        String mail = request.getParameter("userMail");
        List<String[]> forecastMap = ForecastService.getForecast(mail);

        // return last  50 results
        if(forecastMap.size() > 50) {
            return forecastMap.subList(forecastMap.size() - 50, forecastMap.size());
        }

        return forecastMap;
    }

    @RequestMapping(value = "/dashboard/getLocations", method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody List<Location> getLocations (HttpServletRequest request) throws Exception {
        String mail = request.getParameter("userMail");

        List<Location>  locations = LocationDAO.listOfLocations(mail);

        return locations;
    }

    @RequestMapping(value = "/getEmployeeDemand/{date}", method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody String employeeDemand(@PathVariable String date, HttpServletRequest request, Model model){

        String mail = request.getParameter("userMail");

        // Get forecast from mem cache
        List<String[]> forecastMap = ForecastService.getForecast(mail);
        String sales = "";

        // Get forecast for the selected day
        for(String[] strl : forecastMap){

            if(strl[0].equals(date)){
                sales = strl[2];
            }
        }

        // Round values to integers
        int salesRounded = Math.round(Float.parseFloat(sales));
        int waiters = Math.round(salesRounded / 700);
        int barkeepers = Math.round(salesRounded / 850);
        int kitchen = Math.round(salesRounded / 1000);

        return "{\"sales\": "+ sales+"," +
                "\"waiters\": "+waiters+" ," +
                "\"barkeepers\":  "+barkeepers+", " +
                "\"kitchen\": "+kitchen+"}";
    }

    @RequestMapping(value = "/getLogItems", method = RequestMethod.GET)
    public @ResponseBody List<LogItem> getLogItems(HttpServletRequest request, Model model){
        String mail = request.getParameter("userMail");

        PersonDAO.getPersonFromUserID(mail);

        List<LogItem> logItems  = ofy().load().type(LogItem.class)
                .ancestor(Key.create(Person.class, mail))
                .list();

        return logItems;

    }
}