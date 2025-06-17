import java.util.ArrayList;
import java.util.List;

public class ForecastManager {
    public static List<FinancialYearData> generateForecast(
            ForecastStrategy strategy,
            double initialValue,
            double[] growthRates
    ) {
        List<FinancialYearData> results = new ArrayList<>();
        for (int i = 0; i < growthRates.length; i++) {
            double value = strategy.forecast(initialValue, growthRates, i + 1);
            results.add(new FinancialYearData(i + 1, value));
        }
        return results;
    }
}
