package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredential;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;
import com.googlecode.objectify.Key;
import nl.planner.ForecastService;
import nl.planner.boot.Bootstrap;
import nl.planner.boot.WeatherRequest;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.persistence.Doa.PersonDOA;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.entity.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
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
    public String home(HttpServletRequest request, Model model) throws FileNotFoundException {

//        FileInputStream serviceAccount = new FileInputStream("WEB-INF/firebaseSettings.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
//                .build();
//
//        try {
//            FirebaseApp.initializeApp(options);
//        }catch (Exception e){
//            logger.info(e.getLocalizedMessage());
//        }
//        FirebaseAuth.getInstance().verifyIdToken("");



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
//        return LocationDOA.listOfLocations(userService.getCurrentUser());
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
        if (profile != null){
            return "{\"isNewUser \":0}";
        }else{
            return "{\"isNewUser \":1}";
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
    //old
//    @RequestMapping(value = "/dashboard")
//    public String dashboard(HttpServletRequest request, Model model) throws Exception {
//
//        UserService userService = UserServiceFactory.getUserService();
//        User user = userService.getCurrentUser();
//
//        Person person = PersonDOA.getPersonFromUser(user);
//
//        if (! person.getActivated()){
//            return "redirect:/logout";
//        }
//
//        List<String[]> forecastMap = ForecastService.getForecast(user.getUserId());
//
//        ofy().save().entity(person).now();
//
//        List< Weather>  weather = new WeatherRequest().getWeather(52.092876,5.104480);
//
//        Date date = new Date();
//        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
//
//        List<String[]> weekOverview = new ArrayList<>();
//        int nextDays =-1;
//        for(String[] strl : forecastMap){
//            if (strl[0].equals(modifiedDate) || nextDays > 0 ){
//                if (strl[0].equals(modifiedDate)){
//                    nextDays = 7;
//                }
//                int salesRounded = Math.round(Float.parseFloat(strl[2]));
//                int waiters = Math.round(salesRounded / 700);
//                int barkeepers = Math.round(salesRounded / 850);
//                int kitchen = Math.round(salesRounded / 1000);
//                logger.info(strl[0]);
//                String[] value = new String[]{strl[0],String.valueOf(waiters),String.valueOf(barkeepers), String.valueOf(kitchen)};
//                weekOverview.add(value);
//                nextDays--;
//            }
//        }
//
//        String message = userService.createLogoutURL("/");
//        model.addAttribute("message", message);
//        model.addAttribute("locations",LocationDOA.listOfLocations(user));
//        model.addAttribute("weatherForecast",weather);
//        model.addAttribute("forecast",forecastMap);
//        model.addAttribute("weekForecast",weekOverview);
//
//
//        return "dashboard";
//    }
    //new
    @RequestMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request, Model model) throws Exception {

        // do forecast
//        List<String[]> forecastMap = ForecastService.getForecast(user.getUserId());
//
//        ofy().save().entity(person).now();

        List< Weather>  weather = new WeatherRequest().getWeather(52.092876,5.104480);

//        Date date = new Date();
//        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
//
//        List<String[]> weekOverview = new ArrayList<>();
//        int nextDays =-1;
//        for(String[] strl : forecastMap){
//            if (strl[0].equals(modifiedDate) || nextDays > 0 ){
//                if (strl[0].equals(modifiedDate)){
//                    nextDays = 7;
//                }
//                int salesRounded = Math.round(Float.parseFloat(strl[2]));
//                int waiters = Math.round(salesRounded / 700);
//                int barkeepers = Math.round(salesRounded / 850);
//                int kitchen = Math.round(salesRounded / 1000);
//                logger.info(strl[0]);
//                String[] value = new String[]{strl[0],String.valueOf(waiters),String.valueOf(barkeepers), String.valueOf(kitchen)};
//                weekOverview.add(value);
//                nextDays--;
//            }
//        }
//
//        String message = userService.createLogoutURL("/");
//        model.addAttribute("message", message);
//        model.addAttribute("locations",LocationDOA.listOfLocations(user));
        model.addAttribute("weatherForecast",weather);
//        model.addAttribute("forecast",forecastMap);
//        model.addAttribute("weekForecast",weekOverview);


        return "dashboard";
    }

    @RequestMapping(value = "/dashboard/forecast", method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody List<String[]> forecast (HttpServletRequest request) throws Exception {
        String mail = request.getParameter("userMail");
        List<String[]> forecastMap = ForecastService.getForecast(mail);
        return forecastMap;

    }



    @RequestMapping(value = "/getEmployeeDemand/{date}", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String employeeDemand(@PathVariable String date, HttpServletRequest request, Model model){

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        // Get forecast from mem cache
        List<String[]> forecastMap = (List<String[]>) Bootstrap.cache.get(LocationDOA.listOfLocations(user).get(0).getId());
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
}