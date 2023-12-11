package service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import app.Main;
import model.Product;
import util.MyFormatter;


public class Calculator {
    private ArrayList<Product> products = new ArrayList<>();

    private double sum;

    private double peopleCount;

    private final Scanner scanner;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


    public void startDevideCalculator() {
        String strInput;
        System.out.println("Введите кол-во людей на которых необходимо разделить счёт. " +
                "От 2-х человек.");

        try {
            strInput = scanner.next();
            peopleCount = Integer.parseInt(strInput);
            if (peopleCount <= 1) {
                System.err.println("Введите положительное число больше 1");
            } else {
                addProduct();
            }

        } catch (InputMismatchException | NumberFormatException exc) {
            System.err.println("Вы ввели некорректное значение." +
                    "\nПожалуйста, введите целое число больше 1.");
        }
    }

    public void showAllPriceAndProducts() {
        if (!products.isEmpty()) {
            System.out.println("Все добавленные блюда и цены на данный момент:");
            for (Product product : products) {
                System.out.println(product.getProductName() + " = " + product.getPrice());
            }
        } else {
            System.out.println("На данный момент добавленных блюд - нет.");
        }
    }


    public void addProduct() {
        String productName = "product";
        String priceReponse = "";
        double price = 0;
        boolean whileContinue;

        do {
            try {
                scanner.nextLine();
                System.out.println("Введите название блюда: ");
                productName = scanner.nextLine();


                System.out.println("Введите цену: ");
                priceReponse = scanner.nextLine();

                price = Double.parseDouble(priceReponse);

            } catch (InputMismatchException | NumberFormatException exc) {
                System.err.println("Введите число в формате: \"рубли\" или " +
                        "\"рубли.копейки\" - больше 0");

            }

            if (price > 0 && !productName.isEmpty() && !isProductNameHasAnyDigits(productName)) {
                Product newProduct = new Product(productName, price);
                sum += price;
                products.add(newProduct);

                System.out.println("\u001B[32m" + "Блюдо: \"" + productName + "\" по цене:" +
                        " \"" + price + "\" - успешно добавлено." + "\u001B[0m");

                showAllPriceAndProducts();

                System.out.printf("\u001B[32m" + "Каждый человек должен заплатить по: %s" + "\u001B[0m"
                        , MyFormatter.formatPayment(calculate()));

            } else {
                System.err.println("Блюдо не добавлено. \nПроверьте данные:\n" +
                        "1. Цена должна быть положительным числом больше 0 - актуальное значение: " + priceReponse + "\n" +
                        "2. Наименование блюда должно быть строковым значением и не должно состоять " +
                        "из пробелов или быть пустым - актуальное значение: " + productName);
            }
            whileContinue = addAnotherOneOrNot();
        } while (whileContinue);
    }

    public void stopDevideCalculator() {
        String exit = "";
        System.out.println("Введите \"завершить\" для подтверждения");
        exit = scanner.next();
        if (exit.equalsIgnoreCase("завершить")) {
            scanner.close();
            showAllPriceAndProducts();
            stopRunning();
        } else {
            System.err.println("Для выхода выберите пункт меню - " +
                    "\"2.Выйти\" - затем для подтверждения введите - \"завершить\".");
        }
    }

    public static void stopRunning() {
        Main.appClose = true;
    }

    private double calculate() {
        return sum / peopleCount;
    }

    private boolean isProductNameHasAnyDigits(String productName) {
        return productName.trim().chars().anyMatch(Character::isDigit);
    }

    private boolean addAnotherOneOrNot() {
        System.out.println("\n1. Добавить еще одно блюддо.\n2. Выход. ");
        String choice = scanner.next();

        switch (choice) {
            case "1" -> {
                return true;
            }
            case "2" -> {
                stopDevideCalculator();
                return false;
            }
            default -> {
                System.err.println("Выберите пункт меню и введите: 1 или 2");
                return false;
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Double.compare(that.sum, sum) == 0 && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, sum);
    }
}
