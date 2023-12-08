package app;

import java.util.Scanner;

import service.Calculator;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Calculator calculator = new Calculator(scanner);
    public static boolean appClose = false;

    public static void main(String[] args) {

        while (!appClose) {

            printMenu();
            String command = scanner.next();

            switch (command) {
                case "1" -> calculator.startDevideCalculator();
                case "2" -> {
                    calculator.stopDevideCalculator();
                }
                default -> System.err.println("Выберите пункт меню - введите: 1 или 2");
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
