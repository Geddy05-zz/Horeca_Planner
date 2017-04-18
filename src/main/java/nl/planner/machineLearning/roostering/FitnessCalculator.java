package nl.planner.machineLearning.roostering;

import nl.planner.persistence.entity.Employee;

import java.util.List;

/**
 * Created by Geddy on 13-4-2017.
 */
public class FitnessCalculator {

    static double getFitness(RosterIndividual individual){
        double fitness = 0;
        for(List<List<Employee[]>> day : individual.getWeek()){
            for(List<Employee[]> shift : day) {
                for (Employee[] cat : shift) {
                    for (Employee employee:cat) {
                        fitness += employee.getPrice();
                    }
                }
            }
        }
        return fitness;
    }

    /** Get optimum fitness
     *
     * @return maximal accepted fitness value
     */
    public static double getMaxFitness() {
        return 650.0;
//        return maxFitness;
    }
}
