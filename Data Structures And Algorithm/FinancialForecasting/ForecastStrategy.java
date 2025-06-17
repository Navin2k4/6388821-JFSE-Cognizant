public interface ForecastStrategy {
    double forecast(double initialValue, double[] growthRates, int year);
}
