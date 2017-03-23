package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import nl.planner.MailService;
import nl.planner.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Geddy on 9-3-2017.
 */

@Controller
public class ProfileController {

    private UserService userService = UserServiceFactory.getUserService();

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest request, Model model){
        User user = userService.getCurrentUser();
        if (!userService.isUserLoggedIn()){
            return "redirect:/";
        }
//        User user = userService.getCurrentUser();
        String userId = userService.getCurrentUser().getUserId();
        model.addAttribute("person",HomeController.getPersonFromUser(user,userId));
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(HttpServletRequest request, Model model){
        User user = userService.getCurrentUser();
        if (!userService.isUserLoggedIn()){
            return "redirect:/";
        }
        String userId = userService.getCurrentUser().getUserId();

        String name = request.getParameter("Name (Full name)");
        String dateOfBirth = request.getParameter("Date Of Birth");
        String gender = request.getParameter("Gender");
        String address = request.getParameter("Permanent Address");
        String primaryPhone = request.getParameter("Phone number");
        String secondaryPhone = request.getParameter("Secondary Phone number");
        String overview = request.getParameter("Overview (max 200 words)");

        Person person = HomeController.getPersonFromUser(user, user.getUserId());
        person.update(name,dateOfBirth,gender,address,primaryPhone,secondaryPhone,overview);
        ofy().save().entity(person).now();

        model.addAttribute("person",HomeController.getPersonFromUser(user,userId));

        return "profile";
    }

    @RequestMapping(value = "/createProfile", method = RequestMethod.GET)
    public String createProfilePage(HttpServletRequest request, Model model) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String userId = user.getUserId();
        String email = user.getEmail();
        Person person = new Person(userId, email,email);

        model.addAttribute("person",person);
        return "createProfile";
    }

    @RequestMapping(value = "/createProfile", method = RequestMethod.POST)
    public String createProfile(HttpServletRequest request, Model model) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String userId = user.getUserId();
        String email = user.getEmail();
        Person person = new Person(userId, email,email);

        String name = request.getParameter("Name (Full name)");
        String dateOfBirth = request.getParameter("Date Of Birth");
        String gender = request.getParameter("Gender");
        String address = request.getParameter("Permanent Address");
        String primaryPhone = request.getParameter("Phone number");
        String secondaryPhone = request.getParameter("Secondary Phone number");
        String overview = request.getParameter("Overview (max 200 words)");

        person.update(name,dateOfBirth,gender,address,primaryPhone,secondaryPhone,overview);
        ofy().save().entity(person).now();
        String baseUrl = request.getServerName();
        MailService.sendRegistrationMail(person ,baseUrl);

        return "redirect:/dashboard";

    }
}
