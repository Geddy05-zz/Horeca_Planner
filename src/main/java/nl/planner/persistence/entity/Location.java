package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.util.ArrayList;
import java.util.List;

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
    private String city;
    private List<Long> salesKeys = new ArrayList<>(0);

    public List<Long> getLocationKeys() {
        return salesKeys;
    }

    public void addSalesKeys(Long salesKeys) {
        this.salesKeys.add(salesKeys);
    }

    private Location(){}

    public Location(final Long id, String personId, String name, String address, String city){
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
