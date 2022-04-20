package com.company;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        // Creating some initial Hawker stalls
        HawkerFoodStall example1 = new HawkerFoodStall("ChickenHouse", "very nice", "Azat", "Jurong", 56);
        StallFoodMenu menuExample1 = new StallFoodMenu("Chicken rice", 100, 10);
        ArrayList<StallFoodMenu> listExample1 = new ArrayList<>();
        listExample1.add(menuExample1);
        example1.setStallFoodMenuList(listExample1);
        example1.setFoodPreferences("bitter");

        HawkerFoodStall example2 = new HawkerFoodStall("VegetarianStall", "best place for vegetarians", "Max", "Orchard", 77);
        StallFoodMenu menuExample2 = new StallFoodMenu("Delicious veggies", 50, 7);
        ArrayList<StallFoodMenu> listExample2 = new ArrayList<>();
        listExample2.add(menuExample2);
        example2.setStallFoodMenuList(listExample2);
        example2.setFoodPreferences("sweet");

        HawkerFoodStall example3 = new HawkerFoodStall("Fishy Fishy", "very fishy", "John", "Beauty World Plaza", 45);
        StallFoodMenu menuExample3 = new StallFoodMenu("Golden fish", 75, 20);
        ArrayList<StallFoodMenu> listExample3 = new ArrayList<>();
        listExample3.add(menuExample3);
        example3.setStallFoodMenuList(listExample3);
        example3.setFoodPreferences("salty");

        HawkerFoodStall example4 = new HawkerFoodStall("Pizza Stall", "Italian pizza", "Alex", "West Mall", 85);
        StallFoodMenu menuExample4 = new StallFoodMenu("Margarita", 100, 25);
        ArrayList<StallFoodMenu> listExample4 = new ArrayList<>();
        listExample4.add(menuExample4);
        example4.setStallFoodMenuList(listExample4);
        example4.setFoodPreferences("spicy");

        HawkerFoodStall.StallsList.add(example1);
        HawkerFoodStall.StallsList.add(example2);
        HawkerFoodStall.StallsList.add(example3);
        HawkerFoodStall.StallsList.add(example4);

        // Greetings
        HawkerFoodStall h1 = new HawkerFoodStall("", "", "", "", 0);
        StallFoodMenu s1 = new StallFoodMenu("", 0, 0);
        System.out.println("==============================================================");
        System.out.println("                   Welcome to the app! ");
        System.out.println("==============================================================");

        int choice = 1;
        boolean userHasAccount = false;

        // Main body
        while (true) {
            boolean keepGoing = true;
            System.out.println("""
                    Choose your option!
                    0 - Finish program.
                    1 - Order food (need to create account first)
                    2 - Managing Hawker Stalls
                    3 - Create an account
                    """);
            while (keepGoing) {
                try {
                    Scanner input = new Scanner(System.in);
                    choice = input.nextInt();
                    keepGoing = false;
                } catch (InputMismatchException e) {
                    System.out.println("Sorry, but you need to enter a number!");
                    System.out.print("Enter your choice: ");
                }
            }
            // Exit
            if (choice == 0) {
                System.out.println("---------------------------------");
                System.out.println("Your option is 0");
                System.out.println("Thanks for using our app! =)");
                System.out.println("---------------------------------");
                break;
            }
            // Ordering food!
            else if (choice == 1) {
                if (userHasAccount) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("You have selected option 1");
                    System.out.println("Nice! Let's get started. Please, log in to your account!");
                    System.out.println("---------------------------------------------------------");

                    System.out.print("Enter your username: ");
                    Scanner inputToLog = new Scanner(System.in);
                    String Username = inputToLog.nextLine();
                    Username = Username.trim();

                    System.out.print("Enter your password: ");
                    String Password = inputToLog.nextLine();
                    Password = Password.trim();
                    if (OrderAccount.accountVerification(Username, Password)) {
                        System.out.println("---------------------------------------------");
                        System.out.println("You have successfully logged in. Welcome in!");
                        System.out.println("---------------------------------------------");
                        OrderAccount.getMenuThatUserWant(Username); // method which is responsible for ordering food
                    } else {
                        System.out.println("-------------------------------------");
                        System.out.println("Username or password is not correct.");
                        System.out.println("-------------------------------------");
                    }
                }
                // if user hasn't created account yet.
                else {
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("Sorry, but in order to user this feature you need to create an account");
                    System.out.println("----------------------------------------------------------------------");
                }
            }
            // Managing Hawker food Stalls
            else if (choice == 2) {
                int choice2 = 0;
                keepGoing = true;
                System.out.println("""
                        ---------------------------
                        You have selected option 2.
                        ---------------------------
                        Great! Now, choose one of the options!
                        1 - Create Hawker food stall
                        2 - Delete Hawker food stall
                        3 - Add menu to Hawker food stall
                        4 - Delete menu from Hawker food stall
                        """);
                while (keepGoing) {
                    try {
                        Scanner input2 = new Scanner(System.in);
                        choice2 = input2.nextInt();
                        keepGoing = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Sorry, but you need to choose on of the options");
                        System.out.print("Enter your option: ");
                    }
                }
                if (choice2 == 1) {
                    System.out.println("----------------------------");
                    System.out.println("You have selected option 1.");
                    System.out.println("----------------------------");
                    h1.addNewHawkerFoodStall();
                } else if (choice2 == 2) {
                    System.out.println("----------------------------");
                    System.out.println("You have selected option 2.");
                    System.out.println("----------------------------");
                    h1.deleteHawkerFoodStall();
                } else if (choice2 == 3) {
                    System.out.println("----------------------------");
                    System.out.println("You have selected option 3.");
                    System.out.println("----------------------------");
                    s1.addNewFoodMenu();
                } else if (choice2 == 4) {
                    System.out.println("----------------------------");
                    System.out.println("You have selected option 4.");
                    System.out.println("----------------------------");
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