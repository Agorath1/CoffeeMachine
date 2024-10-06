package machine;
import java.util.Scanner;

public class CoffeeMachine {
    private static int totalWater = 400;
    private static int totalMilk = 540;
    private static int totalCoffeeBeans = 120;
    private static int disposableCups = 9;
    private static int totalMoney = 550;
    private static int cupsMade = 0;
    private static Scanner sc = new Scanner(System.in);
    private static int[][] table = new int[][] {{250, 0, 16, 4},
            {350, 75, 20, 7},
            {200, 100, 12, 6}};

    public static void main(String[] args) {
        boolean loop = true;

        do {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String input = sc.next();
            switch (input) {
                case "buy":
                    getDrink();
                    break;
                case "fill":
                    fillMenu();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "clean":
                    clean();
                    break;
                case "remaining":
                    printInventory();
                    break;
                case "exit":
                    loop = false;
            }
            System.out.println();
        } while (loop);
    }

    public static void clean() {
        System.out.println("I have been cleaned!");
        CoffeeMachine.cupsMade = 0;
    }

    public static void takeMoney() {
        System.out.println("I gave you $" + CoffeeMachine.totalMoney);
        CoffeeMachine.totalMoney = 0;
    }

    public static void printInventory() {
        System.out.println("The coffee machine has:");
        System.out.println(CoffeeMachine.totalWater + " ml of water");
        System.out.println(CoffeeMachine.totalMilk + " ml of milk");
        System.out.println(CoffeeMachine.totalCoffeeBeans + " g of coffee beans");
        System.out.println(CoffeeMachine.disposableCups + " disposable cups");
        System.out.println("$" + CoffeeMachine.totalMoney + " of money");
        System.out.println();
    }

    public static void fillMenu() {
        System.out.println("Write how many ml of water you want to add:");
        CoffeeMachine.totalWater += sc.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        CoffeeMachine.totalMilk += sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        CoffeeMachine.totalCoffeeBeans += sc.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        CoffeeMachine.disposableCups += sc.nextInt();
    }

    public static void getDrink() {
        if (CoffeeMachine.cupsMade >= 10) {
            System.out.println("I need cleaning!");
            return;
        }

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = sc.next();
        if (input.equalsIgnoreCase("back")) return;

        int coffee = Integer.parseInt(input) - 1;
        if (!checkInventory(coffee)) return;
        System.out.println("I have enough resources, making you a coffee!");

        CoffeeMachine.totalWater -= table[coffee][0];
        CoffeeMachine.totalMilk -= table[coffee][1];
        CoffeeMachine.totalCoffeeBeans -= table[coffee][2];
        CoffeeMachine.totalMoney += table[coffee][3];
        CoffeeMachine.disposableCups--;
        CoffeeMachine.cupsMade++;
    }

    public static boolean checkInventory(int coffee) {
        if (CoffeeMachine.totalWater <  table[coffee][0]) {
            System.out.print("Sorry, not enough water!");
            return false;
        }
        if (CoffeeMachine.totalMilk < table[coffee][1]) {
            System.out.print("Sorry, not enough milk!");
            return false;
        }
        if (CoffeeMachine.totalCoffeeBeans < table[coffee][2]) {
            System.out.print("Sorry, not enough coffee beans!");
            return false;
        }
        if (CoffeeMachine.disposableCups < 1) {
            System.out.print("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }
}