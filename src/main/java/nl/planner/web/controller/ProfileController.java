package nl.planner.web.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import nl.planner.MailService;
import nl.planner.persistence.Doa.PersonDOA;
import nl.planner.persistence.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


import static com.googlecode.objectify.ObjectifyService.ofy;

@Controller
public class ProfileController {

    private UserService userService = UserServiceFactory.getUserService();

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model){

        return "profile";
    }

    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    public @ResponseBody Person getPerson(HttpServletRequest request ,Model model){
        String userId =  request.getParameter("userMail");

        return PersonDOA.getPersonFromUserID(userId);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(HttpServletRequest request, Model model){
//        User user = userService.getCurrentUser();
//
//        if (!userService.isUserLoggedIn()){
//            return "redirect:/";
//        }
//        String userId = userService.getCurrentUser().getUserId();
        String userId =  request.getParameter("userID");

        String name = request.getParameter("Name (Full name)");
        String dateOfBirth = request.getParameter("Date Of Birth");
        String gender = request.getParameter("Gender");
        String address = request.getParameter("Permanent Address");
        String primaryPhone = request.getParameter("Phone number");
        String secondaryPhone = request.getParameter("Secondary Phone number");
        String overview = request.getParameter("Overview (max 200 words)");

        Person person = PersonDOA.getPersonFromUserID(userId);
        person.update(name,dateOfBirth,gender,address,primaryPhone,secondaryPhone,overview);
        ofy().save().entity(person).now();

        model.addAttribute("person",PersonDOA.getPersonFromUserID(userId));

        return "profile";
    }

    @RequestMapping(value = "/createProfile", method = RequestMethod.GET)
    public String createProfilePage(Model model) {

        model.addAttribute("person",new Person("", "",""));
        return "createProfile";
    }

    @RequestMapping(value = "/createProfile", method = RequestMethod.POST)
    public String createProfile(HttpServletRequest request) {

        String userId = request.getParameter("userID");
        String email = request.getParameter("userID");
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
