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
    private double fitness = 0;

    public RosterIndividual(){}

    public RosterIndividual(List<List<List<Employee[]>>> week ){
        this.week =week;
    }

    /**
     * This function creates the first individuals for the generic algorithm.
     * @param planningFrame The number of shifts
     * @param locationID the id of the location (needed for getting the correct employees)
     * @param userID the id of the user (needed for getting the correct employees)
     */
    public void generateIndividual(List<List<int[]>> planningFrame, String locationID,String userID){
        this.shifts = shifts;
        week = new ArrayList<>();

        int dayNumber = 0;
        for(List<int[]> days : planningFrame){

            List<List<Employee[]>> day = new ArrayList<>();

            for(int[] shift: days) {
                EmployeePool pool = new EmployeePool(locationID,userID,dayNumber);

                List<Employee[]> shiftList = new ArrayList<>();
                int count = 0;

                for(int nr : shift){
                    count++;
                    Employee[] waiters = new Employee[nr];

                    for(int i=0; i< nr; i++){
                        waiters[i] = pool.getRandomEmployee(count);
                    }
                    shiftList.add(waiters);
                }

                day.add(shiftList);
            }

            week.add(day);
            dayNumber++;
        }

        if(!Algorithm.isValidePlanning(week)){
            generateIndividual(planningFrame,locationID,userID);
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

    /**
     * create a copy of the week planning
     * @return the planning from a individual
     */
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
                        tcat[count] = new Employee(e.getId(),e.getName(),e.getPrice(),e.getSkills(),
                                e.getAvailableWeekdays(), e.getExperience(),e.getHoursInContract());
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
