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

    public Algorithm(String locationID,String userID){
        this.locationID = locationID;
        this.userID = userID;
        pool = new EmployeePool(locationID,userID);
    }

    /**
     * This is the main function of the genetic algorithm.
     * The evolve function desides which individuals are in the tournament
     * and also decides if the algorithm does the mutation.
     * @param population the current population.
     * @param locationID the id of the location.
     * @return the new population\.
     */
    public Population evolvePopulation(Population population,String locationID) {
        Population newPopulation = population;
        pool = new EmployeePool(locationID,userID);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, population.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        // Loop over the population size and create new individuals with the crossover function
        for (int i = elitismOffset; i < population.size(); i++) {

            if (Math.random() <= crossoverRate) {
                RosterIndividual indiv1 = tournamentSelection(population, null);
                RosterIndividual indiv2 = tournamentSelection(population, indiv1);

                RosterIndividual newIndiv = crossover(indiv1, indiv2);
                newPopulation.saveIndividual(population.getWeakkestindex(), newIndiv);
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

    /**
     * The crossover function creates a new individual from the 2 selected individuals.
     * It choose randomly which gene from te 2 selected individuals the new individual gets.
     * @param indiv1 first selected individual
     * @param indiv2 second selected individual
     * @return the new individual
     */
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


    /**
     * A function that checks if the planning is valid. It checks if a employee have the correct skills
     * and that there are no employees who have te work a double shift.
     * @param planning the created planning
     * @return true is a valid planning false isn't valid.
     */
    public static boolean isValidePlanning(List<List<List<Employee[]>>> planning){

        for(List<List<Employee[]>> day : planning) {
            ArrayList<Long> employees = new ArrayList<>();

            for (List<Employee[]> shift : day) {
                int shiftCount = 0;

                for (Employee[] cat : shift) {
                    shiftCount++;

                    for (Employee e : cat) {

                        if (listContainsValue(employees,e.getId())) {
                            return false;
                        }

                        else {

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

    /**
     * The mutation function change a couple of genes for possible finding a better solution.
     * @param indiv the individual that's selected for mutation
     * @param locationID the location id.
     * @return the mutated individual
     */
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

                        // decides which genes have to change.
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

    /**
     * A function that select a X number of individuals that parcipated with the tournament
     * The individual with the highest fitness wins the tournament.
     * @param pop the population where the function gets the individuals from
     * @param indiv1 a selected individual from the previous tournament.
     * @return the winner from the tournament.
     */
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
