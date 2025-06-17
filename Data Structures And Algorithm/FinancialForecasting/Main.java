import java.util.List;

public class Main {
    public static void main(String[] args) {
        double initialValue = 1000.0;
        double[] growthRates = {0.10, 0.12, 0.08, 0.15, 0.09};

        ForecastStrategy strategy = new MemoizedForecast();
        List<FinancialYearData> forecast = ForecastManager.generateForecast(strategy, initialValue, growthRates);

        System.out.println("Financial Forecast:");
        forecast.forEach(System.out::println);

//        Financial Forecast:
//        Year 1: ₹1100.00
//        Year 2: ₹1232.00
//        Year 3: ₹1330.56
//        Year 4: ₹1530.14
//        Year 5: ₹1667.86
    }
}