package nl.planner.boot;

import com.googlecode.objectify.ObjectifyService;
import nl.planner.persistence.entity.*;
import nl.planner.persistence.Doa.LocationDOA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.cache.Cache;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import java.sql.*;
import java.util.Collections;

public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
    public static final String databaseName = "test";
    public static Cache cache;

    protected void init() throws Exception {
        log.info("Starting app");

        registerEntities();
        SQLDatabase.createSalesTable();

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

        log.info("Done registering entities.");
    }
}
