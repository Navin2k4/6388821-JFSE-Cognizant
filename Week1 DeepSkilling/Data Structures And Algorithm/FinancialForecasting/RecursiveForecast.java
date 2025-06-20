public class RecursiveForecast implements ForecastStrategy {
    @Override
    public double forecast(double initialValue, double[] growthRates, int year) {
        if (year == 0) return initialValue;

        double previousValue = forecast(initialValue, growthRates, year - 1);
        return previousValue + (previousValue * growthRates[year - 1]);
    }
}
