import java.util.Scanner;

public class CurrencyConverter{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Exchange rates = new Exchange();

        System.out.print("Enter amount: ");
        double amount = in.nextDouble();
        in.nextLine();

        System.out.println("Currencies: USD, EUR, GBP, PLN, CNY");

        System.out.println("Enter currency to convert from: ");
        String fromCurrency = in.nextLine().toUpperCase();

        System.out.println("Enter currency to convert to: ");
        String toCurrency = in.nextLine().toUpperCase();

        double convertedAmount = amount * rates.getExchangeRate(fromCurrency, toCurrency);

        System.out.printf("%f %s = %f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
    }
}

class Exchange {

    private static final double USD_TO_EUR = 0.91138;
    private static final double USD_TO_GBP = 0.80499;
    private static final double USD_TO_PLN = 4.19757;
    private static final double USD_TO_CNY = 6.88786;
    private static final double EUR_TO_USD = 1.09708;
    private static final double GBP_TO_USD = 1.24205;
    private static final double PLN_TO_USD = 0.23808;
    private static final double CNY_TO_USD = 0.14513;

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        switch (fromCurrency) {
            case "USD":
                return switch (toCurrency) {
                    case "EUR" -> USD_TO_EUR;
                    case "GBP" -> USD_TO_GBP;
                    case "PLN" -> USD_TO_PLN;
                    case "CNY" -> USD_TO_CNY;
                    default -> throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
                };
            case "EUR":
                switch (toCurrency) {
                    case "USD":
                        return EUR_TO_USD;
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
                }
            case "GBP":
                switch (toCurrency) {
                    case "USD":
                        return GBP_TO_USD;
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
                }
            case "PLN":
                switch (toCurrency) {
                    case "USD":
                        return PLN_TO_USD;
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
                }
            case "CNY":
                switch (toCurrency) {
                    case "USD":
                        return CNY_TO_USD;
                    default:
                        throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
                }
            default:
                throw new IllegalArgumentException("Unsupported currency: " + fromCurrency);
        }
    }
}