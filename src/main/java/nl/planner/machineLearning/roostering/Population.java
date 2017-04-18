package nl.planner.machineLearning.roostering;

/**
 * Created by Geddy on 13-4-2017.
 */
public class Population {
    private RosterIndividual[] individuals;
    private int shift = 3;

    /**
     * initialisation function that creates a population.
     * @param populationSize the size of the population
     * @param initialise a boolean if the function have to create new individuals
     * @param locationID the id of the location.
     * @param userID the id of the user.
     */
    public Population(int populationSize, boolean initialise,String locationID, String userID) {
        individuals = new RosterIndividual[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                RosterIndividual newIndividual = new RosterIndividual();
                newIndividual.generateIndividual(shift,locationID,userID);
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public RosterIndividual getIndividual(int index) {
        return individuals[index];
    }

    public RosterIndividual getFittest() {
        RosterIndividual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() >= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public int getWeakkestindex() {
        RosterIndividual weakkest = individuals[0];
        int index = 0;
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (weakkest.getFitness() <= getIndividual(i).getFitness()) {
                weakkest = getIndividual(i);
                index = i;
            }
        }
        return index;
    }

    public RosterIndividual getFittest(RosterIndividual fit) {
        RosterIndividual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() >= getIndividual(i).getFitness() && !getIndividual(i).equals(fit)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */

    /**
     * Get population size
     * @return size of the individuals.
     */
    public int size() {
        return individuals.length;
    }

    /**
     * add individual at a given index.
     * @param index the index where the individual belongs.
     * @param indiv the new individual.
     */
    public void saveIndividual(int index, RosterIndividual indiv) {
        individuals[index] = new RosterIndividual(indiv.getWeek());
    }
}
