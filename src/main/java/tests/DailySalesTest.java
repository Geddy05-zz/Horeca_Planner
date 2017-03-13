package tests;

import nl.planner.persistence.entity.DailySales;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Geddy on 10-3-2017.
 */
public class DailySalesTest extends MLUnitTest{

    Date date = new Date();
    DailySales ds = new DailySales(1,Long.parseLong("1"),date,1000.0,0.25,true,20);

    @Test
    public void getSales() throws Exception {
        assertEquals(1000.0,ds.getSales());
    }


    @Test
    public void getWeekday() throws Exception {
        assertEquals("Weekday" , 7,ds.getWeekday());
    }

}