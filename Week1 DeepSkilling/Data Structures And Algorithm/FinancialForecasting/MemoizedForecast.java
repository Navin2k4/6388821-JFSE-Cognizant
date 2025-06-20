import java.util.HashMap;
import java.util.Map;

public class MemoizedForecast implements ForecastStrategy{
    private final Map<Integer, Double> memo = new HashMap<>();

    @Override
    public double forecast(double initialValue, double[] growthRates, int year) {
        if (year == 0) return initialValue;

        if (memo.containsKey(year)) {
            return memo.get(year);
        }

        double previousValue = forecast(initialValue, growthRates, year - 1);
        double currentValue = previousValue + (previousValue * growthRates[year - 1]);
        memo.put(year, currentValue);

        return currentValue;
    }
}
