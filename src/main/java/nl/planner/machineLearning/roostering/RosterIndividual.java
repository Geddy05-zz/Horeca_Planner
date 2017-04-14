package nl.planner.machineLearning.roostering;

import nl.planner.persistence.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geddy on 13-4-2017.
 */
public class RosterIndividual {
    public List<List<List<Employee[]>>> week = new ArrayList<>();
    int shifts = 2;
    // Cache
    private double fitness = 0;

    public RosterIndividual(){}

    public RosterIndividual(List<List<List<Employee[]>>> week ){
        this.week =week;
    }

    public void generateIndividual(int shifts,String locationID,String userID){
        this.shifts = shifts;
//        EmployeePool pool = new EmployeePool();
        week = new ArrayList<>();
        for(int i = 0; i< 7; i++){
            List<List<Employee[]>> day = new ArrayList<>();
            EmployeePool pool = new EmployeePool(locationID,userID);
            for(int shift =1 ; shift < shifts; shift++) {
                List<Employee[]> shiftList = new ArrayList<>();
                Employee[] waiters = new Employee[]{pool.getRandomEmployee(1), pool.getRandomEmployee(1)};
                Employee[] bartenders = new Employee[]{pool.getRandomEmployee(2), pool.getRandomEmployee(2)};
                Employee[] kitchen = new Employee[]{pool.getRandomEmployee(3), pool.getRandomEmployee(3)};
                shiftList.add(waiters);
                shiftList.add(bartenders);
                shiftList.add(kitchen);
                day.add(shiftList);

            }
            week.add(day);
        }
        if(!Algorithm.isValidePlanning(week)){
            generateIndividual(shifts,locationID,userID);
        }
    }

    public Employee getGene(int dayOfweek,int day,int shift,int index){
        return week.get(dayOfweek).get(day).get(shift)[index];
    }

    public void setGene(int dayOfweek, int day, int shift, int index, Employee value){
        List<List<List<Employee[]>>> tempWeek = week;
        tempWeek.get(dayOfweek).get(day).get(shift)[index] = value;
        week = tempWeek;
        fitness = 0;
    }

    public int size(){
        return week.size();
    }

    public double getFitness(){
        return FitnessCalculator.getFitness(this);
    }

    public List<List<List<Employee[]>>> getWeek() {
        List<List<List<Employee[]>>> tempWeek = new ArrayList<>();

        for(List<List<Employee[]>> day : week) {
            List<List<Employee[]>> tday = new ArrayList<>();
            for (List<Employee[]> shift : day) {
                List<Employee[]> tshift = new ArrayList<>();
                for (Employee[] cat : shift) {
                    Employee[] tcat =  new Employee[cat.length];
                    int count = 0;
                    for(Employee e : cat){
                        tcat[count] = new Employee(e.getId(),e.getName(),e.getPrice(),e.getSkills(),e.getAvailableWeekdays());
                        count++;
                    }
                    tshift.add(tcat);
                }
                tday.add(tshift);
            }
            tempWeek.add(tday);
        }
        return tempWeek;
    }
}
