package nl.planner.persistence.DAO;

import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.users.User;

import java.util.List;

public class LocationDAO {

    public void createLocation(String userID, String name, String adress ,String city){

        Key<Person> personKey = Key.create(Person.class,userID);
        final Key<Location> locationKey = factory().allocateId(personKey,Location.class);
        final long locationId = locationKey.getId();

        Person person = PersonDAO.getPersonFromUserID(userID);
        Location location = new Location(locationId,userID,name,adress, city);

        person.addLocationKeys(locationId);
        ofy().save().entities(person,location).now();
    }

    public static void updateLocation(String userID, String  locationId, String name,
                                      String postal, String adress, String city,
                                      String mo, String tu, String we, String th,String fr,String sa, String su){

        Location location = getLocationFromId(userID,locationId);
        location.update(name,postal,adress,city,mo,tu,we,th,fr,sa,su);

        ofy().save().entities(location).now();

    }

    /**
     * get all the location from a given user
     * @param user the user
     * @return all the users locations
     */
//    public static List<Location> listOfLocations(User user){
//        List<Location> locations = ofy().load().type(Location.class)
//                .ancestor(Key.create(Person.class, user.getUserId()))
//                .list();
//
//        if(locations == null){
//            return new ArrayList<>();
//        }
//        return locations;
//    }

    /**
     * get all the location from a given user
     * @param userid the user
     * @return all the users locations
     */
    public static List<Location> listOfLocations(String userid){

        return ofy().load().type(Location.class)
                .ancestor(Key.create(Person.class, userid))
                .list();
    }

    /**
     * Get the correct location corresponding with the locationId
     * @param user the user for getting the correct organisation
     * @param locationId location id
     * @return returns the location corresponding with the location id.
     */
    public static Location getLocationFromId(User user,String locationId){

        Person person = PersonDAO.getPersonFromUser(user);

        return ofy().load().type(Location.class)
                .parent(person)
                .id(Long.parseLong(locationId))
                .now();
    }

    /**
     * Get the correct location corresponding with the locationId
     * @param userId the userId for getting the correct organisation
     * @param locationId location ids
     * @return returns the location corresponding with the location id.
     */
    public static Location getLocationFromId(String userId,String locationId){

        Person person = PersonDAO.getPersonFromUserID(userId);

        return ofy().load().type(Location.class)
                .parent(person)
                .id(Long.parseLong(locationId))
                .now();
    }
}
