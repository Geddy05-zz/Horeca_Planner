package nl.planner.persistence.Doa;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Person;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Geddy on 24-3-2017.
 */
public class PersonDOA {

    /**
     * Get the person profile from User in the request header
     * @param user
     * @return the person with the given user.
     */
    public static Person getPersonFromUser(User user) {
        // First fetch it from the datastore.
        Person profile = ofy().load().key(
                Key.create(Person.class, user.getUserId())).now();
        if (profile == null) {
            // Create a new Profile if not exist.
            String email = user.getEmail();
            profile = new Person(user.getUserId(), email,email);
        }
        return profile;
    }

    /**
     * get the person from the user ID
     * @param userID user id
     * @return the person with the given user id.
     */
    public static Person getPersonFromUserID(String userID) {
        // First fetch it from the datastore.
        Person profile = ofy().load().key(
                Key.create(Person.class, userID)).now();
        if (profile == null) {
            // Create a new Profile if not exist.
            String email = userID;
            profile = new Person(userID, email,email);
        }
        return profile;
    }
}
