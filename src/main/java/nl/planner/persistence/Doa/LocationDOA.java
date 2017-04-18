package nl.planner.persistence.Doa;

import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.users.User;

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

    /**
     * get all the location from a given user
     * @param user the user
     * @return all the users locations
     */
    public static List<Location> listOfLocations(User user){

        return ofy().load().type(Location.class)
                .ancestor(Key.create(Person.class, user.getUserId()))
                .list();
    }

    /**
     * Get the correct location corresponding with the locationId
     * @param user the user for getting the correct organisation
     * @param locationId location id
     * @return returns the location corresponding with the location id.
     */
    public Location getLocationFromId(User user,String locationId){

        Person person = PersonDOA.getPersonFromUser(user);

        return ofy().load().type(Location.class)
                .parent(person)
                .id(Long.parseLong(locationId))
                .now();
    }

    /**
     * Get the correct location corresponding with the locationId
     * @param userId the userId for getting the correct organisation
     * @param locationId location id
     * @return returns the location corresponding with the location id.
     */
    public Location getLocationFromId(String userId,String locationId){

        Person person = PersonDOA.getPersonFromUserID(userId);

        return ofy().load().type(Location.class)
                .parent(person)
                .id(Long.parseLong(locationId))
                .now();
    }
}
