package nl.planner.persistence.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import nl.planner.persistence.enums.Experience;

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

    public int getHoursInContract() {
        return hoursInContract;
    }

    private int hoursInContract;
    private Experience experience;

    private Employee(){};

    public Employee(Long id, String name, double priceHour, Skill[] skills, int[] availableWeekdays, Experience ex,int hoursInContract){
        this.id = id;
        this.name = name;
        this.priceHour = priceHour;
        this.skills = skills;
        this.availableWeekdays = availableWeekdays;
        this.experience = ex;
        this.hoursInContract = hoursInContract;
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

    public boolean isAvailableOnDay(int nr){
        for(int i : availableWeekdays){
            if(i == nr){
                return true;
            }
        }
        return false;
    }

    public Experience getExperience() {
        if(experience == null){
            experience = Experience.Junior;
        }
        return experience;
    }

}
