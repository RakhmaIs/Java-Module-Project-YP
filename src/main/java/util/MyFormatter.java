package util;

public class MyFormatter {

    public static String formatPayment(double amount) {
        String formattedAmount = String.format("%.2f", amount);
        int rubles = (int) Math.floor(amount);
        String ending = rubles % 10 == 1 && rubles % 100 != 11 ? "рубль" : rubles % 10 >= 2
                && rubles % 10 <= 4 && (rubles % 100 < 10 || rubles % 100 >= 20) ? "рубля" : "рублей";
        return formattedAmount + " " + ending;
    }
}

