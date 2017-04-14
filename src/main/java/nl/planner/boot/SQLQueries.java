package nl.planner.boot;

/**
 * Created by Geddy on 11-4-2017.
 */
public class SQLQueries {

    private SQLQueries(){};

    public static final String dropeSalesTable = "DROP TABLE "+SQLDatabase.databaseName;
    public static final String createSalesTable = "CREATE TABLE IF NOT EXISTS "+SQLDatabase.databaseName
            +" ( ID FLOAT NOT NULL AUTO_INCREMENT,location_key VARCHAR(255) NOT NULL "
            + ", sales DOUBLE NOT NULL, timestamp DATETIME NOT NULL, weekday INT NOT NULL,"
            + "residues DOUBLE NOT NULL, is_holiday BOOLEAN, temperature INT NOT NULL,"
            + "PRIMARY KEY (ID) )";

    //0 index 1:date 2:sales 3:weekday 4:holiday 5:temp 6:weather;
    public static final String insertSalesData = "INSERT INTO "+SQLDatabase.databaseName
            +" (timestamp,sales, weekday, is_holiday,temperature,residues,location_key)"+
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static String getSalesDataQuery(Long id){
        return "SELECT * From "+Bootstrap.databaseName+ " WHERE location_key ="+id  ;
    }
}
