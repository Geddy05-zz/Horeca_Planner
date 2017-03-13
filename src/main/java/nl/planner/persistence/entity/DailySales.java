package nl.planner.persistence.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Geddy on 10-3-2017.
 */
@Entity
public class DailySales {

    @Id
    private Long id;

    private Date date;
    private int weekday;

    private double sales;
    private double residues;
    private boolean isHoliday;
    private int temperature;

    private DailySales(){}

    public DailySales(final long id,Long locationId, Date date,double sales, double residues, boolean isHoliday,int temperature){
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
