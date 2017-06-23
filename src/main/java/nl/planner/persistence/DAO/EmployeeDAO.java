package nl.planner.persistence.DAO;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;
import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Skill;
import nl.planner.persistence.enums.Experience;

import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class EmployeeDAO {

    public Employee createEmployee(final Location location, String name, double priceHour, Skill[] skills,
                                   int[] availableWeekdays, Experience ex, int hoursInContract) {
        Key<Location> locationKey = Key.create(Location.class, location.getId());
        final Key<Employee> EmployeeKey = factory().allocateId(locationKey, Employee.class);
        final long employeeId = EmployeeKey.getId();

        final Employee employee = new Employee(employeeId, name, priceHour, skills, availableWeekdays, ex, hoursInContract);
        location.addEmployee(employee);
        ofy().transact(new Work<Employee>() {
            public Employee run(){
                ofy().clear();
                ofy().save().entities(employee, location);
                return  employee;
            }
        });

        return employee;
    }

    public Employee readEmployee(Location location,long employeeId){
        Employee employee=  null;
         for (Employee e : location.getEmployees()){
             if (e.getId() == employeeId){
                 employee = e;
                 break;
             }
         }
        return employee;
    }

    public void deleteEmployee(Employee employee, Location location) {
        location.removeEmployee(employee);

        ofy().delete().entities(employee).now();
        ofy().save().entity(location).now();
    }
}
