package tests;

import nl.planner.persistence.entity.DailySales;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DailySalesTest extends MLUnitTest {

    private Date date = new Date();
    private DailySales ds = new DailySales(1, Long.parseLong("1"), date, 1000.0, 0.25, true, 20);

    // Maximum eventual consistency.
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
                    .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void getSales() throws Exception {
        assertEquals(1000.0,ds.getSales(),0);
    }


    @Test
    public void getWeekday() throws Exception {
        assertEquals("Weekday", 1, ds.getWeekday());
    }

}