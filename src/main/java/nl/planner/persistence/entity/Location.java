package nl.planner.persistence.entity;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.JsonObject;
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
    private String postal;
    private String openingHoursMonday;
    private String openingHoursTuesday;
    private String openingHoursWednesday;
    private String openingHoursThursday;
    private String openingHoursFriday;
    private String openingHoursSaturday;
    private String openingHoursSunday;
    private List<Employee> employees;

    private String planning;

    private Location() {}

    public Location(final Long id, String personId, String name, String address, String city){

        this.personKey = Key.create(Person.class, personId);
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.employees = new ArrayList<>();
        this.planning = null;
    }

    public void update(String name,
                       String postal, String adress, String city,
                       String mo, String tu, String we, String th,String fr,String sa, String su){
        this.name = name;
        this.postal = postal;
        this.address = adress;
        this.city = city;
        this.openingHoursMonday = mo;
        this.openingHoursTuesday = tu;
        this.openingHoursWednesday = we;
        this.openingHoursThursday = th;
        this.openingHoursFriday = fr;
        this.openingHoursSaturday = sa;
        this.openingHoursSunday = su;
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

//    public String getOpeningHours() {
//        return openingHours;
//    }
//
//    public void setOpeningHours(String openingHours) {
//        this.openingHours = openingHours;
//    }

    public void addEmployee(Employee employee) {

        if(this.employees == null){
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee){

        if(employees.contains(employee)){
            employees.remove(employee);
        }
    }

    public Employee getEmployeeById(long id){

        Employee employee = null;

        for( Employee emp : employees){
            if(emp.getId() == id)
                employee = emp;
        }

        return employee;
    }

    /**
     * This function is only used for testing. It creates a new employeeList
     */
    public void renewEmployeesList(){
        employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        if (employees != null) {
            return employees;
        }
        return new ArrayList<>();
    }

    /**
     * A function that formats the planning to a string so the program
     * can store the data.
     * @param planning
     */
    public void setPlanning(List<List<List<Employee[]>>> planning) {

        Gson gson = new Gson();
        String json = gson.toJson(planning);
        this.planning = json;
    }

    /**
     * A function that get the data structure we need for computation.
     * data is stored as a string.
     * @return returns the weekplanning by day by shift.
     */
    public List<List<List<List<Employee>>>> getPlanning() {
        if(planning == null){
            return new ArrayList<>();
        }
        Gson gson = new Gson();

        List<List<List<List<LinkedTreeMap>>>>  a = gson.fromJson(planning,ArrayList.class);

        List<List<List<List<Employee>>>> formattedList= new ArrayList<>();
        for(List<List<List<LinkedTreeMap>>> day: a){
            List<List<List<Employee>>> shiftsADay = new ArrayList<>();
            for(List<List<LinkedTreeMap>> shift: day){
                List<List<Employee>> shiftTypesInShift = new ArrayList<>();
                for(List<LinkedTreeMap> shftType : shift ){
                    List<Employee> employeesInShift= new ArrayList<>();
                    for(LinkedTreeMap empl : shftType){
                        JsonObject j = gson.toJsonTree(empl).getAsJsonObject();
                        Employee employee = gson.fromJson(j,Employee.class);
                        employeesInShift.add(employee);
                    }
                    shiftTypesInShift.add(employeesInShift);
                }
                shiftsADay.add(shiftTypesInShift);
            }
            formattedList.add(shiftsADay);
        }

        return formattedList;
    }
    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public String getOpeningHoursMonday() {
        return openingHoursMonday;
    }

    public String getOpeningHoursTuesday() {
        return openingHoursTuesday;
    }

    public String getOpeningHoursWednesday() {
        return openingHoursWednesday;
    }

    public String getOpeningHoursThursday() {
        return openingHoursThursday;
    }

    public String getOpeningHoursFriday() {
        return openingHoursFriday;
    }

    public String getOpeningHoursSaturday() {
        return openingHoursSaturday;
    }

    public String getOpeningHoursSunday() {
        return openingHoursSunday;
    }
}
