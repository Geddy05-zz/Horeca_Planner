package nl.planner.boot;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.googlecode.objectify.ObjectifyService;
import nl.planner.persistence.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.Cache;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import java.util.Collections;

public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
    public static final String databaseName = "test";
    public static Cache cache;
    public static Queue queue;

    protected void init() throws Exception {
        log.info("Starting app");

        registerEntities();
        SQLDatabase.createSalesTable();
        queue = QueueFactory.getDefaultQueue();

        CacheFactory cf = CacheManager.getInstance().getCacheFactory();
        cache = cf.createCache(Collections.emptyMap());
    }

    private void registerEntities() {
        log.info("Registering entities...");
        ObjectifyService.register(Person.class);
        ObjectifyService.register(Location.class);
        ObjectifyService.register(DailySales.class);
        ObjectifyService.register(LogItem.class);
        ObjectifyService.register(Task.class);
        ObjectifyService.register(Employee.class);

        log.info("Done registering entities.");
    }
}
