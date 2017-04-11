package nl.planner.persistence.entity;

import com.googlecode.objectify.annotation.Id;

import java.util.Date;

/**
 * Created by Geddy on 11-4-2017.
 */
public class Employee {
    @Id
    private Long id;

    private String name;
    private String surname;
    private Date dateOfBirth;
    private Skill[] skills;
    private double priceHour;

    public Employee(Long id,String name, double priceHour, Skill[] skills){
        this.id = id;
        this.name = name;
        this.priceHour = priceHour;
        this.skills = skills;
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

}
