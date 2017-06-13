package nl.planner.persistence.DAO;

import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.LogItem;
import nl.planner.persistence.entity.Person;

import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Geddy on 9-6-2017.
 */
public class LogItemDAO {

    public static void createLocation(String userID, String message){
        Key<Person> personKey = Key.create(Person.class,userID);
        final Key<LogItem> logItemKey = factory().allocateId(personKey,LogItem.class);
        final long locationId = logItemKey.getId();

        Person person = PersonDAO.getPersonFromUserID(userID);
        LogItem logItem = new LogItem(locationId,userID,message);
        person.addLocationKeys(locationId);

        ofy().save().entities(person,logItem).now();
    }
}
