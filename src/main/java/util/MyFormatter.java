package util;

public class MyFormatter {
    public static String responseFormat(double perPersonPay) {
        String perPay = String.valueOf(perPersonPay);
        if (perPay.endsWith("1") && !perPay.equals("11")) {
            return "рубль";
        } else if (perPay.endsWith("0") || (perPay.endsWith("5") || perPay.endsWith("6")
                || perPay.endsWith("7") || perPay.endsWith("8") || perPay.endsWith("9"))) {
            return "рублей";
        } else {
            return "рубля";
        }
    }
}

