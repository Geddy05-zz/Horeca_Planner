package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Geddy on 10-3-2017.
 */

// Do we really need this ?????
@Entity
public class DailySales {

    @Parent
    private Key<Location> locationKey;

    @Id
    private Long id;

    private Date date;
    private int weekday;

    private double sales;
    private double residues;
    private boolean isHoliday;
    private int temperature;


    public DailySales(final long id,Long locationId, Date date,double sales, double residues, boolean isHoliday,int temperature){

//        this.locationKey = Key.create(Location.class,locationId);
        this.id = id;
        this.sales = sales;
        this.residues = residues;
        this.isHoliday = isHoliday;
        this.temperature = temperature;
        this.date = date;

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);

        // week starts on sunday
        this.weekday = calendar.get(Calendar.DAY_OF_WEEK);
    }

    public double getSales() {
        return sales;
    }

    public int getWeekday() {
        return weekday;
    }
}
