package nl.planner.web.controller;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import nl.planner.ForecastService;
import nl.planner.boot.Bootstrap;
import nl.planner.machineLearning.roostering.Algorithm;
import nl.planner.machineLearning.roostering.FitnessCalculator;
import nl.planner.machineLearning.roostering.Population;
import nl.planner.machineLearning.roostering.RosterIndividual;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.persistence.entity.Employee;
import nl.planner.persistence.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Controller
public class PlanningController {
    private static final Logger logger = Logger.getLogger(LocationController.class.getName());


    @RequestMapping(value = "location/{locationId}/createSchedule", method = RequestMethod.POST)
    public String createSchedulePost(@PathVariable String locationId,HttpServletRequest request,Model model) {
        // Add the task to the default queue.

        String mail = request.getParameter("userID");
//        String locationID = request.getParameter("locationID");

        Bootstrap.queue.add(TaskOptions.Builder.withUrl("/worker/"+locationId)
                .param("locationID", locationId)
                .param("userID", mail));

        model.addAttribute("locationId", locationId);

        return "Schedule";
    }

    @RequestMapping(value = "location/{locationId}/getSchedule", method = RequestMethod.GET)
    public @ResponseBody List<List<List<List<Employee>>>> getSchedule(@PathVariable String locationId, HttpServletRequest request,Model model) {
        // Add the task to the default queue.

        String mail = request.getParameter("userMail");
        String week = request.getParameter("weeknr");
        String locationID = locationId;
        LocationDOA locationDOA = new LocationDOA();
        Location location = locationDOA.getLocationFromId(mail, locationId);

        return location.getPlanning();
    }

    @RequestMapping(value = "location/{locationId}/createSchedule", method = RequestMethod.GET)
    public String showSchedule(@PathVariable String locationId, Model model) {
//
//        String mail = request.getParameter("userMail");
//
//        LocationDOA locationDOA = new LocationDOA();
//        Location location = locationDOA.getLocationFromId(user, locationId);
//
//        if (location == null) {
//            return "redirect:/dashboard";
//        }
//        model.addAttribute("location", location);

        model.addAttribute("locationId", locationId);

        return "Schedule";
    }


    @RequestMapping(value = "/location/{locationId}/removePlanning", method = RequestMethod.GET)
    public String location(@PathVariable String locationId, HttpServletRequest request, Model model) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        LocationDOA locationDOA = new LocationDOA();
        Location location = locationDOA.getLocationFromId(user, locationId);

        location.setPlanning(new ArrayList<List<List<Employee[]>>>());
        ofy().save().entity(location).now();

        model.addAttribute("location", location);
        return "Schedule";
    }


    @RequestMapping(value = "/worker/{locationID}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void createSchedule(@PathVariable String locationID,HttpServletRequest request) {
        String userID = request.getParameter("userID");

        List<List<int[]>> planningFrame = createScheduleFrame(userID);

        Algorithm ga = new Algorithm(locationID, userID, planningFrame);

        Population population = new Population(20, true, locationID, userID, planningFrame);
        double fitness = population.getFittest().getFitness();
        RosterIndividual fittest = population.getFittest();

        int generationCount = 0;
        while (fitness > FitnessCalculator.getMaxFitness() && generationCount < 750) {
            generationCount++;
            logger.info("Generation: " + generationCount + " Fitness: " + population.getFittest().getFitness());

            population = ga.evolvePopulation(population, locationID);
            fittest = population.getFittest();
            if (Algorithm.isValidePlanning(fittest.week)) {
                fitness = fittest.getFitness();
            }
        }

        // Print results in log for debugging
        logger.info("Solution found!");
        logger.info("Generation: " + generationCount);
        logger.info("Fitness: " + fittest.getFitness());

        // Save Schedule of the week
        LocationDOA locationDOA = new LocationDOA();
        Location location = locationDOA.getLocationFromId(userID, locationID);

        location.setPlanning(fittest.getWeek());
        ofy().save().entity(location).now();
    }

    private List<List<int[]>> createScheduleFrame(String userID) {

        List<String[]> forecastMap = ForecastService.getForecast(userID);

        List<String[]> myLastForecast = forecastMap.subList(forecastMap.size() - 8, forecastMap.size() - 1);

        List<List<int[]>> week = new ArrayList<>();
        for (String[] sales : myLastForecast) {
            List<int[]> day = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                int salesRounded = Math.round(Float.parseFloat(sales[2]));

                // TODO:  numbers need to come from the location
                int waiters = Math.round((salesRounded / 650) / 2) > 0 ? Math.round((salesRounded / 650) / 2) : 1;
                int barkeepers = Math.round((salesRounded / 750) / 2) > 0 ? Math.round((salesRounded / 750) / 2) : 1;
                int kitchen = Math.round((salesRounded / 1000) / 2) > 0 ? Math.round((salesRounded / 900) / 2) : 1;

                int[] shift = new int[]{waiters, barkeepers, kitchen};
                day.add(shift);
            }
            week.add(day);
        }
        return week;
    }

    private static void printFittest(RosterIndividual myPop) {

        for (List<List<Employee[]>> day : myPop.week) {

            System.out.println("--------------day------------");
            for (List<Employee[]> shift : day) {

                for (Employee[] cat : shift) {

                    for (Employee employee : cat) {
                        logger.info(employee.getId() + " - " + employee.getName() + " ,");
                    }
                }
            }
        }
    }
}
