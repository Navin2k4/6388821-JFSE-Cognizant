public class IterativeForecast implements ForecastStrategy {
    @Override
    public double forecast(double initialValue, double[] growthRates, int year) {
        double result = initialValue;
        for (int i = 0; i < year; i++) {
            result += result * growthRates[i];
        }
        return result;
    }
}
