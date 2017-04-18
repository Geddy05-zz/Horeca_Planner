package nl.planner.boot;

import nl.planner.persistence.entity.Weather;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherRequest {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger log = LoggerFactory.getLogger(SQLDatabase.class);
    private static final String apiKey = "7a656824437c05f95bef2c574e9c2d42";

    /**
     * This function do the call to the forecast.io api and retrieve
     * weather forecast for the current day and next 6 days
     * @param latitude latitude of the location.
     * @param longitude longitude of the location.
     * @return list of weather objects.
     * @throws Exception
     */
    public List< Weather> getWeather(double latitude, double longitude)throws Exception{

        // Create correct api url with apiKey , latitude and longitude
        String urlString = "https://api.forecast.io/forecast/"+apiKey+"/"
                + String.valueOf(latitude) + ","
                + String.valueOf(longitude);

        // Create the connection
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        // Get request body
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null){
            response.append(output);
        }
        in.close();

        // Create json object
        JSONObject json = new JSONObject(response.toString());

        return handleGetResponse(json);
    }


    /**
     * Get daily forecast form the json
     * @param json json format from forecast.io api
     * @return list of weather data objects
     * @throws JSONException
     */
    private List< Weather> handleGetResponse(JSONObject json) throws JSONException {

        // Get array of daily weather forecast.
        JSONArray daily = json.getJSONObject("daily").getJSONArray("data");
        List<Weather> weatherForecast = new ArrayList<Weather>();

        for(int i = 0 ; i < daily.length() -1 ; i++) {

            // Create weather forecast object
            JSONObject today = daily.getJSONObject(i);
            weatherForecast.add( new Weather(
                    today.getString("summary"),
                    (((today.getDouble("temperatureMin") - 32) * 5) / 9), // store temp in celsius
                    (((today.getDouble("temperatureMax") - 32) * 5) / 9), // store temp in celsius
                    today.getDouble("precipIntensity"),
                    today.getDouble("precipProbability"),
                    today.getDouble("windSpeed"),
                    today.getLong("time")));
        }

        return weatherForecast;
    }
}
