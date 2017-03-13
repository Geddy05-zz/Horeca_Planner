package nl.planner.boot;

import com.googlecode.objectify.ObjectifyService;
import nl.planner.persistence.entity.DailySales;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    protected void init() {
        log.info("Starting app");

        registerEntities();
    }

    private void registerEntities() {
        log.info("Registering entities...");
        ObjectifyService.register(Person.class);
        ObjectifyService.register(Location.class);
        ObjectifyService.register(DailySales.class);

        log.info("Done registering entities.");
    }

}
