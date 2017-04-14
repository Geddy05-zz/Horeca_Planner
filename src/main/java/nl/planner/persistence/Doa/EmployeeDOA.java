package nl.planner.persistence.Doa;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Person;
import nl.planner.persistence.entity.Skill;

import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Geddy on 12-4-2017.
 */
public class EmployeeDOA {

    public Employee createEmployee(Location location, String name,double priceHour, Skill[] skills,int[] availableWeekdays){

        Key<Location> locationKey = Key.create(Location.class,location.getId());
        final Key<Employee> EmployeeKey = factory().allocateId(locationKey,Employee.class);
        final long employeeId = EmployeeKey.getId();

        Employee employee = new Employee(employeeId,name,priceHour,skills,availableWeekdays);

        location.addEmployee(employee);
        ofy().save().entities(employee,location).now();
        return employee;
    }

    public void deleteEmployee(Employee employee,Location location){
        location.removeEmployee(employee);
        ofy().delete().entities(employee);
    }
}
