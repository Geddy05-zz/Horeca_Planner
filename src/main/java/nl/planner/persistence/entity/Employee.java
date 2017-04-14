package nl.planner.persistence.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

/**
 * Created by Geddy on 11-4-2017.
 */
@Entity

public class Employee {
    @Id
    private Long id;

    private String name;
    private String surname;
    private Date dateOfBirth;
    private Skill[] skills;
    private int[] availableWeekdays;
    private double priceHour;
    private int hoursInContract;

    private Employee(){};

    public Employee(Long id,String name, double priceHour, Skill[] skills,int[] availableWeekdays){
        this.id = id;
        this.name = name;
        this.priceHour = priceHour;
        this.skills = skills;
        this.availableWeekdays = availableWeekdays;
    }

    public Long getId() {
        return id;
    }


    public double getPrice() {
        return priceHour;
    }


    public String getName() {
        return name;
    }


    public Skill[] getSkills() {
        return skills;
    }

    public int[] getAvailableWeekdays() {
        return availableWeekdays;
    }


}
