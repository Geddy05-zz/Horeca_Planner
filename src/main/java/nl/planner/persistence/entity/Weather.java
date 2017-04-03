package nl.planner.persistence.entity;

import java.util.Calendar;
import java.util.Date;

public class Weather {
    String summary;
    double minTemp;
    double maxTemp;
    double rain;
    double rainProbability;
    double windSpeed;

    public long getTimestamp() {
        return timestamp;
    }

    long timestamp;
    int weekday;
    private  Weather(){}

    public Weather(String summary, double minTemp, double maxTemp, double rain,double rainProbability, double windSpeed, long timestamp){

        this.summary = summary;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.rain = rain;
        this.windSpeed = windSpeed;
        this.rainProbability = rainProbability;
        this.timestamp = timestamp;

        // set timestamp and the correct day of the week.
        Date date = new Date((long) timestamp * 1000);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setTime(date);
        this.weekday = cal.get(Calendar.DAY_OF_WEEK) ;
    }

    public String getSummary() {
        return summary;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getRain() {
        return rain;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWeekdayName() {
        String dayName = "Sunday";
        switch (this.weekday) {
            case Calendar.SUNDAY:
                dayName = "Sunday";
                break;
            case Calendar.MONDAY:
                dayName = "Monday";
                break;
            case Calendar.TUESDAY:
                dayName = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                dayName = "Wednesday";
                break;
            case Calendar.THURSDAY:
                dayName = "Thursday";
                break;
            case Calendar.FRIDAY:
                dayName = "Friday";
                break;
            case Calendar.SATURDAY:
                dayName = "Saturday";
                break;
        }
        return dayName;
    }
}
