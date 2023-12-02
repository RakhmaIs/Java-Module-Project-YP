import java.util.Scanner;

import service.Calculator;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Calculator calculator = new Calculator(scanner);
    static boolean closeApp = false;

    public static void main(String[] args) {

        while (!closeApp) {

            printMenu();
            String command = scanner.next();

            if (command.equals("1")) {
                calculator.startDevideCalculator();
            } else if (command.equals("2")) {
                calculator.showAllPriceAndProducts();
                closeApp = calculator.stopDevideCalculator();
            } else {
                System.err.println("Выберите пункт меню - введите: 1 или 2");
            }
        }

    }

    public static void printMenu() {
        System.out.println("""
                Приветствую!
                Что вы хотите сделать?
                1.Разделить счёт
                2.Выйти""");
    }
}