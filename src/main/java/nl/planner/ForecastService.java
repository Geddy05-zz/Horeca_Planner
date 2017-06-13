package nl.planner;

import nl.planner.boot.Bootstrap;
import nl.planner.persistence.DAO.LocationDAO;
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
            if (Bootstrap.cache.containsKey(LocationDAO.listOfLocations(userID).get(0).getId())) {
                long l = LocationDAO.listOfLocations(userID).get(0).getId();
                forecastMap = (List<String[]>) Bootstrap.cache.get(l);
                if (forecastMap.size() < 1) {
                    forecastMap = LocationController.doTES(LocationDAO.listOfLocations(userID).get(0).getId());
                    Bootstrap.cache.put(LocationDAO.listOfLocations(userID).get(0).getId(), forecastMap);
                }
            } else {
                forecastMap = LocationController.doTES(LocationDAO.listOfLocations(userID).get(0).getId());
                Bootstrap.cache.put(LocationDAO.listOfLocations(userID).get(0).getId(), forecastMap);
            }
        }catch (Exception e){
            try {
                forecastMap = LocationController.doTES(LocationDAO.listOfLocations(userID).get(0).getId());
            }catch (Exception ex){
                forecastMap = new ArrayList<>();
            }
//            Bootstrap.cache.put(LocationDAO.listOfLocations(userID).get(0).getId(), forecastMap);
        }
        return forecastMap;
    }
}
