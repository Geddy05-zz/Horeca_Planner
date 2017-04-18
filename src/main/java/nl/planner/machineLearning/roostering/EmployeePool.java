package nl.planner.machineLearning.roostering;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import nl.planner.persistence.Doa.EmployeeDOA;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.entity.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Geddy on 13-4-2017.
 */
public class EmployeePool {

    private List<Employee> employeeList = new ArrayList<>();

    public EmployeePool(String locationID,String userID){

        LocationDOA locationDOA = new LocationDOA();
        Location location =locationDOA.getLocationFromId(userID,locationID);

        for(Employee e : location.getEmployees()){
            employeeList.add(new Employee(e.getId(),e.getName(),e.getPrice(),e.getSkills(),e.getAvailableWeekdays()));
        }
    }

    /**
     * A function the selected a random employee from the poll
     * depending on the needed skills.
     * @param skillNR the required skill
     * @return the selected employee
     */
    public Employee getRandomEmployee(int skillNR){

        Random r = new Random();
        int rNumber = r.nextInt(employeeList.size());
        Employee e = employeeList.get(rNumber);

        for (Skill skill : e.getSkills()){

            if(skill.getValue() == skillNR){
                employeeList.remove(e);
                return e;
            }

        }
        return getRandomEmployee(skillNR);
    }
}
