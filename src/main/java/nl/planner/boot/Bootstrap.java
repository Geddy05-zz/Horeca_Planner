package nl.planner.boot;

import com.googlecode.objectify.ObjectifyService;
import nl.planner.persistence.entity.DailySales;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.entity.Location;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.Date;

import static java.lang.System.out;

public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
    public static final String databaseName = "test";

    public static String getUrl() {
        if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
            // Check the System properties to determine if we are running on appengine or not
            // Google App Engine sets a few system properties that will reliably be present on a remote
            // instance.
            try {
                // Load the class that provides the new "jdbc:google:mysql://" prefix.
                Class.forName("com.mysql.jdbc.GoogleDriver");
            } catch (ClassNotFoundException e) {
                log.info(e.toString());
            }
            return System.getProperty("ae-cloudsql.cloudsql-database-url");
        } else {
            // Set the url with the local MySQL database connection url when running locally
            return System.getProperty("ae-cloudsql.local-database-url");
        }
    }

    protected void init() throws ServletException {
        log.info("Starting app");

        registerEntities();
        createSalesTable();
    }

    private void registerEntities() {
        log.info("Registering entities...");
        ObjectifyService.register(Person.class);
        ObjectifyService.register(Location.class);
        ObjectifyService.register(DailySales.class);

        log.info("Done registering entities.");
    }

    private void createSalesTable() throws ServletException {
        final String createTableSql = "CREATE TABLE IF NOT EXISTS "+databaseName+" ( ID FLOAT NOT NULL AUTO_INCREMENT,location_key VARCHAR(255) NOT NULL "
                + ", sales DOUBLE NOT NULL, timestamp DATETIME NOT NULL, weekday INT NOT NULL,"
                + "residues DOUBLE NOT NULL, is_holiday BOOLEAN, temperature INT NOT NULL,"
                + "PRIMARY KEY (ID) )";

        try (Connection conn = DriverManager.getConnection(getUrl());) {
            conn.createStatement().executeUpdate(createTableSql);

        } catch (SQLException e) {
            throw new ServletException("SQL error", e);
        }
    }
}
