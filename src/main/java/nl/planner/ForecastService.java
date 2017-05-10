package nl.planner;

import nl.planner.boot.Bootstrap;
import nl.planner.persistence.Doa.LocationDOA;
import nl.planner.web.controller.LocationController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geddy on 19-4-2017.
 */
public class ForecastService {

    public static List<String[]> getForecast(String userID) {
        List<String[]> forecastMap;
        try {
            if (Bootstrap.cache.containsKey(LocationDOA.listOfLocations(userID).get(0).getId())) {
                forecastMap = (List<String[]>) Bootstrap.cache.get(LocationDOA.listOfLocations(userID).get(0).getId());
                if (forecastMap.size() < 1) {
                    forecastMap = LocationController.doTES(LocationDOA.listOfLocations(userID).get(0).getId());
                    Bootstrap.cache.put(LocationDOA.listOfLocations(userID).get(0).getId(), forecastMap);
                }
            } else {
                forecastMap = LocationController.doTES(LocationDOA.listOfLocations(userID).get(0).getId());
                Bootstrap.cache.put(LocationDOA.listOfLocations(userID).get(0).getId(), forecastMap);
            }
        }catch (Exception e){
            return new ArrayList<>();
        }
        return forecastMap;
    }
}
