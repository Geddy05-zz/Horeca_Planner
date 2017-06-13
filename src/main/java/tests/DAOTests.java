package tests;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import nl.planner.persistence.DAO.LocationDAO;
import nl.planner.persistence.DAO.PersonDAO;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;

/**
 * Created by Geddy on 11-6-2017.
 */
public class DAOTests {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private long id= Long.parseLong("1");

    @BeforeClass
    public static void setUpBeforeClass() {
        
        // Reset the Factory so that all translators work properly.
        ObjectifyService.setFactory(new ObjectifyFactory());
        ObjectifyService.register(Person.class);
        ObjectifyService.register(Location.class);
    }

    @Before
    public void setUp() {
        this.helper.setUp();
    }

    @After
    public void tearDown() throws IOException {
        AsyncCacheFilter.complete();
        this.helper.tearDown();
    }

    @Test
    public void getLocation() throws Exception {
        Location location = new Location(id,"1","demo","demo","demo");

        // create location done by LocationsDAO
        Location location1 = LocationDAO.createLocation("1",location.getName(),location.getAddress(),location.getCity());

        assertEquals(location.getAddress(),location1.getAddress());
    }

    @Test
    public void getLocationFromID() throws Exception {

        // first create a person and location and store in database
        Person person = PersonDAO.getPersonFromUserID("1");
        Location location = new Location(id,"1","demo","demo","demo");
        ofy().save().entities(person,location).now();

        // get the location from datastore
        Location location1 = LocationDAO.getLocationFromId("1",location.getId().toString());
        assertEquals(location.getAddress(),location1.getAddress());
    }

}
