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

    public static List<String[]> getForecast(String userID,String locationId) {
        List<String[]> forecastMap;
        try {
            if (Bootstrap.cache.containsKey(LocationDAO.getLocationFromId(userID,locationId))){
                long l = LocationDAO.getLocationFromId(userID,locationId).getId();
                forecastMap = (List<String[]>) Bootstrap.cache.get(locationId);
                if (forecastMap.size() < 1) {
                    forecastMap = LocationController.doTES(LocationDAO.getLocationFromId(userID,locationId).getId());
                    Bootstrap.cache.put(LocationDAO.getLocationFromId(userID,locationId).getId(), forecastMap);
                }
            } else {
                forecastMap = LocationController.doTES(LocationDAO.getLocationFromId(userID,locationId).getId());
                Bootstrap.cache.put(LocationDAO.getLocationFromId(userID,locationId).getId(), forecastMap);
            }
        }catch (Exception e){
            try {
                forecastMap = LocationController.doTES(LocationDAO.getLocationFromId(userID,locationId).getId());
            }catch (Exception ex){
                forecastMap = new ArrayList<>();
            }
//            Bootstrap.cache.put(LocationDAO.listOfLocations(userID).get(0).getId(), forecastMap);
        }
        return forecastMap;
    }
}
