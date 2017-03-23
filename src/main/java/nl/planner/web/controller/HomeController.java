package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static java.lang.System.out;

/**
 * Created by Geddy on 8-3-2017.
 */

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request,Locale locale, Model model) {
        if (request.getUserPrincipal() != null) {
            return "redirect:dashboard";
        } else {
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

    @RequestMapping(value = "/logout")
    public String logout(Locale locale, Model model) {
        UserService userService = UserServiceFactory.getUserService();
        String redirectUrl = userService.createLogoutURL("/");
//        model.addAttribute("message", message);

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/login")
    public String login(Locale locale, Model model) {
        logger.info("Login done");
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
    public String dashboard(HttpServletRequest request, Model model) {
        if (request.getUserPrincipal() == null){
            return "redirect:/";
        }
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String userId = user.getUserId();
        Person person = getPersonFromUser(user,userId);

        if (! person.getActivated()){
            return "redirect:/logout";
        }

        ofy().save().entity(person).now();

        String message = userService.createLogoutURL("/");
        model.addAttribute("message", message);

        return "dashboard";
    }

    //ToDo: change location of this function
    public static Person getPersonFromUser(User user, String userId) {
        // First fetch it from the datastore.
        Person profile = ofy().load().key(
                Key.create(Person.class, userId)).now();
        if (profile == null) {
            // Create a new Profile if not exist.
            String email = user.getEmail();
            profile = new Person(userId, email,email);
        }
        return profile;
    }
}
