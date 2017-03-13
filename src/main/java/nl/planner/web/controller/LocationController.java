package nl.planner.web.controller;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;

import nl.planner.persistence.entity.* ;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class LocationController {

    private UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(LocationController.class.getName());

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String location(HttpServletRequest request, Model model){
        if (!userService.isUserLoggedIn()){
            return "redirect:/";
        }
        User user = userService.getCurrentUser();
        String userId = userService.getCurrentUser().getUserId();

        List<Location> locations = ofy().load().type(Location.class).ancestor(Key.create(Person.class, userId)).list();

        model.addAttribute("locations",locations);

        return "locations";
    }

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model){
        if (!userService.isUserLoggedIn()){
            return "redirect:/";
        }
        User user = userService.getCurrentUser();
        String userId = userService.getCurrentUser().getUserId();
        model.addAttribute("person",HomeController.getPersonFromUser(user,userId));

        String name = request.getParameter("Name");
        String adress = request.getParameter("Adress");

        Key<Person> personKey = Key.create(Person.class,userId);
        final Key<Location> locationKey = factory().allocateId(personKey,Location.class);
        final long locationId = locationKey.getId();

        Person person = HomeController.getPersonFromUser(user, user.getUserId());
        Location location = new Location(locationId,userId,name,adress);

        person.addLocationKeys(locationId);
        ofy().save().entities(person,location).now();

        List<Location> locations = ofy().load().type(Location.class).ancestor(Key.create(Person.class, userId)).list();

        model.addAttribute("locations",locations);
        return "locations";
    }

    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public String location(@PathVariable String locationId,Model model){
        if (!userService.isUserLoggedIn()){
            return "redirect:/";
        }
        logger.info(locationId);
        User user = userService.getCurrentUser();
        Person person = HomeController.getPersonFromUser(user, user.getUserId());
        Location location = ofy().load().type(Location.class).parent(person).id(Long.parseLong(locationId)).now();
        model.addAttribute("location",location);
        return "location";
    }
}
