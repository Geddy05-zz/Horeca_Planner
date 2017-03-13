package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * Location class stores locations data.
 */
@Entity
public class Location {
    @Parent
    private Key<Person> personKey;

    @Id
    private Long id;

    private String name;
    private String address;

    private Location(){}

    public Location(final Long id, String personId, String name, String address){
        this.personKey = Key.create(Person.class, personId);
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }

    public String getName() {

        return name;
    }
}
