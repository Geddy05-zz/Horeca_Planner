package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Profile class stores user's profile data.
 */
@Entity
public class Person {
    /**
     *  Use userId as the datastore key.
     */
    @Id
    private String userId;
    private String displayName;
    private String mainEmail;
    private String secondaryPhone="" ;
    private String dateOfBirth = "";
    private String gender = "";
    private String primaryPhone ="";
    private String overview="";
    private String address = "";

    public List<Long> getLocationKeys() {
        return locationKeys;
    }

    public void addLocationKeys(Long locationKeys) {
        this.locationKeys.add(locationKeys);
    }

    private List<Long> locationKeys = new ArrayList<>(0);

    private Person() {}

    public Person(String userId, String displayName, String mainEmail) {
        this.userId = userId;
        this.displayName = displayName;
        this.mainEmail = mainEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void update(String name ,String dateOfBirth , String gender ,String address ,String primaryPhone ,
                       String secondaryPhone ,String overview){
        this.displayName = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.overview = overview;
    }

    public String getPhoneNumber() {
        return primaryPhone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public String getOverview() {
        return overview;
    }
}
