package nl.planner.machineLearning;

import java.util.ArrayList;

/**
 * Created by Geddy on 23-3-2017.
 */
public class LinearRegression {

    public static Double[] forecast(Double[] data, double alpha, double beta, double gamma ,
                                    int period, int numberOfPredictions){
        if(data[0] == null ){
            return new Double[0];
        }
        Double[] forecast= new Double[data.length + numberOfPredictions];

        double[] seasonals = initialSeasonalComponent(data,period);
        double smooth = data[0];
        double trend = initialTrend(data,period);
        for(int i = 0; i < data.length + numberOfPredictions; i++){
            if(i== 0){
                forecast[i] = smooth;
            }else if(i >= data.length){
                int n = i- data.length + 1;
                forecast[i] = (smooth + n * trend) + seasonals[i% period];
            }else{
                double value = data[i];
                double lastSmooth = smooth;
                smooth = alpha * (value - seasonals[i%period]) + (1 - alpha) * (smooth+ trend);
                trend = beta * (smooth - lastSmooth) + (1 - beta) * trend;
                seasonals[i%period] = gamma * (value - lastSmooth - trend) + (1 - gamma) * seasonals[i%period];
                forecast[i] = smooth+trend+seasonals[i%period];
            }
        }
        return forecast;
    }

    // set the first trend
    public static double initialTrend(Double[] data, int period){
        double sum = 0.0;

        for(int i = 0; i < period; i++){
            sum += (data[i + period] - data[i]) / period;
        }
        return sum / period;
    }

    public static double[] initialSeasonalComponent(Double[] data, int period){
        int numberOfSeasons = data.length/period;
        double[] seasonalIndices = new double[period];
        double[] seasonalAverage = new double[numberOfSeasons];

        for (int i = 0; i < numberOfSeasons; i++) {
            for (int j = 0; j < period; j++) {
                seasonalAverage[i] += data[(i * period) + j];
            }
            seasonalAverage[i] /= period;
        }

        for (int i = 0; i < period; i++) {
            double sumOfValuesOverAverage = 0.0;
            for(int j = 0; j < numberOfSeasons; j++) {
                sumOfValuesOverAverage += data[period * j + i] - seasonalAverage[j];
            }
            seasonalIndices[i] = sumOfValuesOverAverage / numberOfSeasons;
        }
        return seasonalIndices;
    }
}
