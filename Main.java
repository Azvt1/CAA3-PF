package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        // Creating some initial Hawker stalls
        HawkerFoodStall example1 = new HawkerFoodStall("ChickenHouse", "very nice", "Azat", "Jurong", 56);
        StallFoodMenu menuExample1 = new StallFoodMenu("Chicken rice", 100, 10);
        ArrayList<StallFoodMenu> listExample1 = new ArrayList<>();
        listExample1.add(menuExample1);
        example1.setStallFoodMenuList(listExample1);

        HawkerFoodStall example2 = new HawkerFoodStall("VegetarianStall", "best place for vegetarians", "Max", "Orchard", 77);
        StallFoodMenu menuExample2 = new StallFoodMenu("Delicious veggies", 50, 7);
        ArrayList<StallFoodMenu> listExample2 = new ArrayList<>();
        listExample2.add(menuExample2);
        example2.setStallFoodMenuList(listExample2);

        HawkerFoodStall example3 = new HawkerFoodStall("Fishy Fishy", "very fishy", "John", "Beauty World Plaza", 45);
        StallFoodMenu menuExample3 = new StallFoodMenu("Golden fish", 75, 20);
        ArrayList<StallFoodMenu> listExample3 = new ArrayList<>();
        listExample3.add(menuExample3);
        example3.setStallFoodMenuList(listExample3);

        HawkerFoodStall.StallsList.add(example1);
        HawkerFoodStall.StallsList.add(example2);
        HawkerFoodStall.StallsList.add(example3);
        // Greetings
        HawkerFoodStall h1 = new HawkerFoodStall("", "", "", "", 0);
        StallFoodMenu s1 = new StallFoodMenu("", 0, 0);
        OrderAccount or1 = new OrderAccount();
        System.out.println("==============================================================");
        System.out.print("Welcome to the app! ");

        int choice = 1;
        boolean userHasAccount = false;

        while (choice != 0) {
            System.out.println("""
                    Choose your option!
                    0 - Finish program.
                    1 - Order food (need to create account first)
                    2 - Managing Hawker Stalls
                    3 - Create an account
                    """);
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            // Exit
            if (choice == 0) {
                System.out.println("Thanks for using our app! =)");
                break;
            } else if (choice == 1) {
                if (userHasAccount) {
                    System.out.println("Nice! Let's get started. Please, log in to your account!");

                    System.out.println("Enter your username: ");
                    Scanner inputToLog = new Scanner(System.in);
                    String Username = inputToLog.nextLine();

                    System.out.println("Enter your password: ");
                    String Password = inputToLog.nextLine();
                    if (OrderAccount.accountVerification(Username, Password)) {
                        System.out.println("---------------------- Welcome! ----------------------------");
                        OrderAccount.getMenuThatUserWant(Username);
                    } else {
                        System.out.println("Username or password is not correct.");
                    }
                } else {
                    System.out.println("Sorry, but in order to user this feature you need to create an account");
                }
            } else if (choice == 2) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("""
                        Great! Now, choose one of the options!
                        1 - Create Hawker food stall
                        2 - Delete Hawker food stall
                        3 - Add menu to your Hawker food stall
                        4 - Delete menu from your Hawker food stall
                        """);
                int choice2 = input2.nextInt();
                if (choice2 == 1) {
                    h1.addNewHawkerFoodStall();
                } else if (choice2 == 2) {
                    h1.deleteHawkerFoodStall();
                } else if (choice2 == 3) {
                    s1.addNewFoodMenu();
                } else if (choice2 == 4) {
                    s1.deleteFoodFromMenu();
                }
            }
            // Creating account
            else if (choice == 3) {
                OrderAccount.createUserAccount();
                userHasAccount = true;
            } else {
                System.out.println("You need to choose one of the options!");
            }
        }
    }
}