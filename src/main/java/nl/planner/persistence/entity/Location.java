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
    private String openingHours;
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

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

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

    public void renewEmployeesList(){
        employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        if (employees != null) {
            return employees;
        }
        return new ArrayList<>();
    }

    public void setPlanning(List<List<List<Employee[]>>> planning) {
        Gson gson = new Gson();
        String json = gson.toJson(planning);
        this.planning = json;
    }

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

}
