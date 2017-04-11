package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import nl.planner.boot.Bootstrap;
import nl.planner.boot.WeatherRequest;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.persistence.Doa.PersonDOA;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.entity.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;

import org.springframework.web.bind.annotation.*;

import javax.cache.Cache;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;
import static com.googlecode.objectify.ObjectifyService.ofy;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, Model model) {

        if (request.getUserPrincipal() != null) {
            return "redirect:dashboard";
        }
        else {
            String thisUrl = request.getRequestURI()+ "login";
            UserService userService = UserServiceFactory.getUserService();

            // ToDo clean up. Remove all html tags from this function
            String message = "<a href=\""
                    + userService.createLoginURL(thisUrl)
                    + " \" class=\"btn btn-default btn-lg\" ><span class=\"network-name\">sign in</span></a> ";
            model.addAttribute("message", message);
        }

        model.addAttribute("Title", "Home page");

        return "home";
    }

    @RequestMapping(value = "/getSales")
    public  @ResponseBody List<Location> getLocations() {
        UserService userService = UserServiceFactory.getUserService();
        return LocationDOA.listOfLocations(userService.getCurrentUser());
    }


    @RequestMapping(value = "/logout")
    public String logout() {

        UserService userService = UserServiceFactory.getUserService();
        String redirectUrl = userService.createLogoutURL("/");

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/login")
    public String login() {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String userId = user.getUserId();

        Person profile = ofy().load().key(
                Key.create(Person.class, userId)).now();
        if (profile != null){
            return "redirect:/dashboard";
        }else{
            return "redirect:/createProfile";
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
        }else{
            UserService userService = UserServiceFactory.getUserService();
            String thisUrl = request.getRequestURI()+ "login";
            String login = userService.createLoginURL(thisUrl);
            return "redirect:/"+login;
        }
    }

    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request, Model model) throws Exception {

        if (request.getUserPrincipal() == null){
            return "redirect:/";
        }
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        Person person = PersonDOA.getPersonFromUser(user);

        if (! person.getActivated()){
            return "redirect:/logout";
        }

        List<String[]> forecastMap;
        if(Bootstrap.cache.containsKey(LocationDOA.listOfLocations(user).get(0).getId())){
            logger.info("get from cache");
            forecastMap = (List<String[]>) Bootstrap.cache.get(LocationDOA.listOfLocations(user).get(0).getId());
        }else {
            forecastMap = LocationController.doTES();
            logger.info("Not in cache");
            Bootstrap.cache.put(LocationDOA.listOfLocations(user).get(0).getId(),forecastMap);
        }

        ofy().save().entity(person).now();

        List< Weather>  weather = new WeatherRequest().getWeather(52.092876,5.104480);

        String message = userService.createLogoutURL("/");
        model.addAttribute("message", message);
        model.addAttribute("locations",LocationDOA.listOfLocations(user));
        model.addAttribute("weatherForecast",weather);
        model.addAttribute("forecast",forecastMap);

        return "dashboard";
    }

    @RequestMapping(value = "/getEmployeeDemand/{date}", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String employeeDemand(@PathVariable String date, HttpServletRequest request, Model model){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        List<String[]> forecastMap = (List<String[]>) Bootstrap.cache.get(LocationDOA.listOfLocations(user).get(0).getId());
        String sales = "";
        for(String[] strl : forecastMap){
            if(strl[0].equals(date)){
                sales = strl[2];
            }
        }
        logger.info(sales);
        int salesRounded = Math.round(Float.parseFloat(sales));
        int waiters = Math.round(salesRounded / 700);
        int barkeepers = Math.round(salesRounded / 800);
        int kitchen = Math.round(salesRounded / 750);
        return "{\"sales\": "+ sales+"," +
                "\"waiters\": "+waiters+" ," +
                "\"barkeepers\":  "+barkeepers+", " +
                "\"kitchen\": "+kitchen+"}";
    }
}