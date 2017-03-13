package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Geddy on 8-3-2017.
 */

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request,Locale locale, Model model) {

        UserService userService = UserServiceFactory.getUserService();

        String thisUrl = request.getRequestURI();

        String message = "";
        if (request.getUserPrincipal() != null) {
            return "redirect:dashboard";
        } else {
            // ToDo clean up. Remove all html tags from this function
            message = "<a href=\""
                    + userService.createLoginURL(thisUrl)
                    + " \" class=\"btn btn-default btn-lg\" ><span class=\"network-name\">sign in</span></a> ";
            model.addAttribute("message", message);
        }

        logger.info("Welcome home! the client locale is "+ locale.toString());

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("Title", "Home page");
        model.addAttribute("serverTime", formattedDate );

        return "home";
    }

    @RequestMapping(value = "/logout")
    public String logout(Locale locale, Model model) {
        UserService userService = UserServiceFactory.getUserService();

        String redirectUrl = userService.createLogoutURL("/");
//        model.addAttribute("message", message);

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/dashboard")
    public String dashboard(Locale locale, Model model) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String userId = user.getUserId();
        Person person = getPersonFromUser(user,userId);

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
