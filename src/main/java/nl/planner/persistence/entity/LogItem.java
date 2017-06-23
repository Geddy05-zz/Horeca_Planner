package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import sun.rmi.runtime.Log;

import java.util.Date;

/**
 * Created by Geddy on 28-3-2017.
 */

@Entity
public class LogItem {
    @Parent
    private Key<Person> personKey;

    @Id
    private Long id;
    private String message;
    @Index
    private Long date;

    private LogItem(){}

    public LogItem(final Long id, String personID, String message){
        this.personKey = Key.create(Person.class, personID);
        this.id = id;
        this.message = message;
        this.date = (new Date().getTime() / 1000);

    }

    public String getMessage() {
        return message;
    }

    public Long getDate() {
        return date;
    }
}
