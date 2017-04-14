package nl.planner.web.controller;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import nl.planner.boot.Bootstrap;
import nl.planner.machineLearning.roostering.Algorithm;
import nl.planner.machineLearning.roostering.FitnessCalculator;
import nl.planner.machineLearning.roostering.Population;
import nl.planner.machineLearning.roostering.RosterIndividual;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Controller
public class PlanningController {
    private static final Logger logger = Logger.getLogger(LocationController.class.getName());


    @RequestMapping(value = "/createSchedule", method = RequestMethod.GET)
    public String createSchedule(){
        // Add the task to the default queue.
        UserService userService = UserServiceFactory.getUserService();
        String userId = userService.getCurrentUser().getUserId();
        Bootstrap.queue.add(TaskOptions.Builder.withUrl("/worker")
                .param("locationID", "1")
                .param("userID",userId));

        return "profile";
    }

    @RequestMapping(value = "location/{locationId}/createSchedule", method = RequestMethod.GET)
    public String showSchedule(@PathVariable String locationId,Model model){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        LocationDOA locationDOA = new LocationDOA();
        Location location = locationDOA.getLocationFromId(user,locationId);

        model.addAttribute("location",location);
        return "Schedule";
    }

    @RequestMapping(value = "/worker", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void createSchedule(HttpServletRequest request) {
        String locationID = request.getParameter("locationID");
        String userID = request.getParameter("userID");

        Algorithm ga = new Algorithm(locationID,userID);

        Population population = new Population(20,true,locationID, userID);
        double fitness = population.getFittest().getFitness();
        RosterIndividual fittest = population.getFittest();

        int generationCount = 0;
        while (fitness > FitnessCalculator.getMaxFitness()) {
            generationCount++;
            logger.info("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());

            population = ga.evolvePopulation(population,locationID);
            fittest = population.getFittest();
            if(Algorithm.isValidePlanning(fittest.week)) {
                fitness = fittest.getFitness();
            }
        }

        // print results in log for debugging
        logger.info("Solution found!");
        logger.info("Generation: " + generationCount);
        logger.info("Best Planning:");
        printFittest(fittest);

        // Save Schedule of the week
        LocationDOA locationDOA = new LocationDOA();
        Location location =locationDOA.getLocationFromId(userID,locationID);

        location.setPlanning(fittest.getWeek());
        ofy().save().entity(location).now();
    }

    private static void printFittest(RosterIndividual myPop ){
        for (List<List<Employee[]>> day : myPop.week) {
            System.out.println("--------------day------------") ;
            for (List<Employee[]> shift : day) {
//                System.out.println() ;
                for (Employee[] cat : shift) {
                    for (Employee employee : cat) {
                        logger.info(employee.getId()+" - "+employee.getName() + " ,");
                    }
                }
//                System.out.println();
            }
        }
//        System.out.println();
    }
}
