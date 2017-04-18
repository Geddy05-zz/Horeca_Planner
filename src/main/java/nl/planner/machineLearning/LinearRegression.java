package nl.planner.machineLearning;

public class LinearRegression {

    /**
     * The start function for triple exponential smoothing
     * @param data a list of doubles
     * @param alpha a smoothing factor
     * @param beta a smoothing factor
     * @param gamma a smoothing factor
     * @param period the length of the season
     * @param numberOfPredictions the amount of predictions you wanna make
     * @return a list with forecasting values
     */
    public static Double[] forecast(Double[] data, double alpha, double beta, double gamma ,
                                    int period, int numberOfPredictions){
        // check if there is a valid data set
        if(data[0] == null ){
            return new Double[0];
        }

        // initialize variables for calculation
        Double[] forecast= new Double[data.length + numberOfPredictions];
        double[] seasonals = initialSeasonalComponent(data,period);
        double smooth = data[0];
        double trend = initialTrend(data,period);

        // loop through the data set + the number of predictions the user wants
        for(int i = 0; i < data.length + numberOfPredictions; i++){

            if(i== 0) {
                forecast[i] = smooth;
            }
            else if(i >= data.length) {
                int n = i- data.length + 1;
                forecast[i] = (smooth + n * trend) + seasonals[i% period];
            }
            else {
                double value = data[i];
                double lastSmooth = smooth;

                // Calculates the smooth,trend, seasonal values for making the forecast
                smooth = alpha * (value - seasonals[i%period]) + (1 - alpha) * (smooth+ trend);
                trend = beta * (smooth - lastSmooth) + (1 - beta) * trend;
                seasonals[i%period] = gamma * (value - lastSmooth - trend) + (1 - gamma) * seasonals[i%period];

                // calculate the forecast with smooth trend en seasonal vectors.
                forecast[i] = smooth+trend+seasonals[i%period];
            }
        }
        return forecast;
    }

    /**
     * Calculate the trend between first and second seasons
     * this function is only used for initialising the regression model
     * @param data list of numeric data points for regression
     * @param period the length of the season
     * @return the trend between first and second season
     */
    private static double initialTrend(Double[] data, int period){
        double sum = 0.0;

        for(int i = 0; i < period; i++){
            sum += (data[i + period] - data[i]) / period;
        }
        return sum / period;
    }

    /**
     * Calculate for each point in the season the deviation compared with the mean.
     * this function is only used for initialising the regression model
     * @param data list of numeric data points for regression
     * @param period the length of the season
     * @return the deviation from te mean in the season
     */
    private static double[] initialSeasonalComponent(Double[] data, int period){

        int numberOfSeasons = data.length/period;
        double[] seasonalIndices = new double[period];
        double[] seasonalAverage = new double[numberOfSeasons];

        // Calculate the average of the season;
        for (int i = 0; i < numberOfSeasons; i++) {
            for (int j = 0; j < period; j++) {
                seasonalAverage[i] += data[(i * period) + j];
            }
            seasonalAverage[i] /= period;
        }

        // Calculate the deviation from a point in the season and the mean of the season
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
