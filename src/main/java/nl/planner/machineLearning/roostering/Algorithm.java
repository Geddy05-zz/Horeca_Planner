package nl.planner.machineLearning.roostering;

import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geddy on 13-4-2017.
 */
public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.50;
    private static final double crossoverRate = 0.8;
    private static final double mutationRate = 0.3;
    private static final int tournamentSize = 6;
    private static final boolean elitism = true;
    public static String locationID;
    public static String userID;

    private EmployeePool pool;
    /* Public methods */
    public Algorithm(String locationID,String userID){
        this.locationID = locationID;
        this.userID = userID;
        pool = new EmployeePool(locationID,userID);
    }

    // Evolve a population
    public Population evolvePopulation(Population pop,String locationID) {
        Population newPopulation = pop;
        pool = new EmployeePool(locationID,userID);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            if (Math.random() <= crossoverRate) {
                RosterIndividual indiv1 = tournamentSelection(pop, null);
                RosterIndividual indiv2 = tournamentSelection(pop, indiv1);

                RosterIndividual newIndiv = crossover(indiv1, indiv2);
                newPopulation.saveIndividual(pop.getWeakkestindex(), newIndiv);
                break;
            }
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            if (Math.random() <= mutationRate) {
                newPopulation.saveIndividual(i, mutate(newPopulation.getIndividual(i),locationID));
            }
        }
        return newPopulation;
    }

    // Crossover individuals
    private RosterIndividual crossover(RosterIndividual indiv1, RosterIndividual indiv2) {
        RosterIndividual newSol = new RosterIndividual(indiv1.getWeek());
        // Crossover
        int dayOfweek = 0;
        for(List<List<Employee[]>> day : newSol.week) {

            int dayn = 0;
            for (List<Employee[]> shift : day) {

                int shiftn = 0;
                for (Employee[] cat : shift) {

                    for (int i = 0; i < cat.length; i++) {

                        if (Math.random() <= uniformRate) {
                            newSol.setGene(dayOfweek,dayn,shiftn,i,
                                    indiv2.getGene(dayOfweek,dayn,shiftn,i));
                        }
                    }
                    shiftn++;
                }
                dayn++;
            }
            dayOfweek++;
        }
        if(isValidePlanning(newSol.week)){
            return newSol;
        }
        if(indiv1.getFitness() <= indiv2.getFitness()){
            return indiv1;
        }
        return indiv2;
    }

    public static boolean isValidePlanning(List<List<List<Employee[]>>> planning){
        for(List<List<Employee[]>> day : planning) {
            ArrayList<Long> employees = new ArrayList<>();
            for (List<Employee[]> shift : day) {
                int shiftCount = 0;
                for (Employee[] cat : shift) {
                    shiftCount++;
                    for (Employee e : cat) {
//                        employees.contains(e.getId());
                        if (listContainsValue(employees,e.getId())) {
                            return false;
                        } else {
                            if(!hasGoodSkills(shiftCount,e)){
                                return false;
                            }
                            employees.add(e.getId());
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean hasGoodSkills(int shift, Employee e){
        for (Skill skill : e.getSkills()){
            if( skill.getValue() == shift){
                return true;
            }
        }
        return false;
    }

    private static boolean listContainsValue(List<Long> list , float value){
        for(Long i: list){
            if (i == value){
                return true;
            }
        }
        return false;
    }

    // Mutate an individual
    private RosterIndividual mutate(RosterIndividual indiv,String locationID) {
        RosterIndividual newSol = new RosterIndividual(indiv.getWeek());

        // Crossover
        int dayOfweek = 0;
        for(List<List<Employee[]>> day : newSol.getWeek()) {

            int dayn = 0;
            for (List<Employee[]> shift : day) {

                int shiftn = 0;
                for (Employee[] cat : shift) {

                    for (int i = 0; i < cat.length; i++) {
                        if(Math.random() <= 0.1) {
                            pool = new EmployeePool(locationID,userID);
                            newSol.setGene(dayOfweek, dayn, shiftn, i,
                                    pool.getRandomEmployee(shiftn + 1));
                        }
                    }
                    shiftn++;
                }
                dayn++;
            }
            dayOfweek++;
        }

        if(isValidePlanning(newSol.week)){
            return newSol;
        }
        return indiv;
    }

    // Select individuals for crossover
    private RosterIndividual tournamentSelection(Population pop, RosterIndividual indiv1) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false,locationID,userID);

        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }

        // Get the fittest
        if(indiv1 != null) {
            return tournament.getFittest(indiv1);
        }else{
            return tournament.getFittest();
        }
    }
}
