package nl.planner.persistence.Doa;

import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import nl.planner.web.controller.HomeController;

import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.users.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class LocationDOA {

    public void createLocation(User user, String name, String adress ,String city){

        Key<Person> personKey = Key.create(Person.class,user.getUserId());
        final Key<Location> locationKey = factory().allocateId(personKey,Location.class);
        final long locationId = locationKey.getId();

        Person person = PersonDOA.getPersonFromUser(user);
        Location location = new Location(locationId,user.getUserId(),name,adress, city);

        person.addLocationKeys(locationId);
        ofy().save().entities(person,location).now();
    }

    public static List<Location> listOfLocations(User user){

        return ofy().load().type(Location.class)
                .ancestor(Key.create(Person.class, user.getUserId()))
                .list();
    }

    public Location getLocationFromId(User user,String locationId){

        Person person = PersonDOA.getPersonFromUser(user);

        return ofy().load().type(Location.class)
                .parent(person)
                .id(Long.parseLong(locationId))
                .now();
    }
    public Location getLocationFromId(String user,String locationId){

        Person person = PersonDOA.getPersonFromUserID(user);

        return ofy().load().type(Location.class)
                .parent(person)
                .id(Long.parseLong(locationId))
                .now();
    }
}
