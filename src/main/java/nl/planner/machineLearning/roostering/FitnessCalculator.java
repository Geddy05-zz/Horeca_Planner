package nl.planner.machineLearning.roostering;

import nl.planner.persistence.DAO.LocationDAO;
import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Location;
import nl.planner.persistence.enums.Experience;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geddy on 13-4-2017.
 */
public class FitnessCalculator {

    static double getFitness(RosterIndividual individual , String locationId,String userId){
        double fitness = 10;
        Location location = LocationDAO.getLocationFromId(userId,locationId);
        Map<Long, Double> hoursWorked = new HashMap<Long, Double>();
        Map<Long, Double> hoursContract= new HashMap<Long, Double>();

        for(List<List<Employee[]>> day : individual.getWeek()){

            for(List<Employee[]> shift : day) {
                ArrayList<Long> employees = new ArrayList<>();

                for (Employee[] cat : shift) {

                    // For getting a good balance in experience in the team.
                    // We calculate the squared error zo bigger mistakes make a much bigger impact on the fitness
                    int devider = cat.length * Experience.Medior.getValue();
                    int sumExperience = 0;

                    for (Employee employee:cat) {
                        long employeeId = employee.getId();

                        hoursWorked = addHours(employeeId,hoursWorked);
                        hoursWorked.put(employeeId, (double) employee.getHoursInContract());
                        sumExperience += employee.getExperience().getValue();

                        if(employees.contains(employee.getId())){
                            fitness += 50;
                        }else{
                            hoursContract.put(employeeId,(double) employee.getHoursInContract());
                        }
                    }
                    fitness += Math.pow(sumExperience -  devider ,2);
                }
            }
        }
        return fitness + hoursFitnessNew(hoursWorked,hoursContract,location);
    }

    private static Map<Long, Double> addHours(long id ,Map<Long, Double> hoursWorked){
        if (hoursWorked.containsKey(id)){
            Double hours = hoursWorked.get(id);
            hoursWorked.put(id, hours + 6.0);

        }else {
            hoursWorked.put(id, 6.0);
        }
        return hoursWorked;
    }

    private static double hoursFitness(Map<Long, Double> hoursWorked, Map<Long, Double> hoursContract){
        double fitness = 0.0;
        for(Map.Entry<Long, Double> entry : hoursWorked.entrySet())
        {
            long employee = entry.getKey();
            double worked = entry.getValue();
            double inContract = hoursContract.get(employee);
            if (inContract > 0 && inContract < worked) {
                fitness += (Math.pow(inContract - worked, 2) * 50);
            }else if(inContract > 0 && inContract > worked ){
                fitness += (Math.pow(inContract - worked, 2) * 25);
            }
        }
        return fitness;
    }
    private static double hoursFitnessNew(Map<Long, Double> hoursWorked, Map<Long, Double> hoursContract,Location location){
        double fitness = 0.0;
        for(Employee employee : location.getEmployees())
        {
            if (hoursWorked.containsKey(employee.getId())){
                double worked = hoursWorked.get(employee.getId());
                double inContract = hoursContract.get(employee.getId());
                if (inContract > 0 ) {
                    fitness += (Math.pow(inContract - worked, 2) * 50);
                }
            }else{
                double worked = employee.getHoursInContract();
                if (worked == 0 ){
                    fitness += 50;
                }
                fitness += (Math.pow(employee.getHoursInContract(), 2) * 50);
            }
        }
        return fitness;
    }
    /** Get optimum fitness
     *
     * @return maximal accepted fitness value
     */
    public static double getMaxFitness() {
        return 200.0;
//        return maxFitness;
    }
}
