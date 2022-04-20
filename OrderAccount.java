package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class OrderAccount {
    private int id;
    private String name;
    private String username;
    private String password;
    private ArrayList<String> foodPreferences = new ArrayList<>();
    private ArrayList<StallFoodMenu> orderedFood = new ArrayList<>();

    // Array to create a user
    static ArrayList<OrderAccount> users = new ArrayList<>();


    public OrderAccount() {
    }

    public OrderAccount(int id, String name, String username, String password, ArrayList<String> foodPreferences) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.foodPreferences = foodPreferences;
    }

    @Override
    public String toString() {
        return id + ". Name: " + name + "\nUsername: " + username + "\nPassword: " + password + "\nFood preferences: " +
                printFoodPreferences() + "\nOrdered food: " + orderedFood;
    }

    public String printFoodPreferences() {
        String print = "";
        for (int i = 0; i < foodPreferences.size(); i++) {
            print += foodPreferences.get(i);
        }
        return print;
    }

    public static String printOrderedFood(String username) {
        String print = "";
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)){
                for (int j = 0; j < users.get(i).orderedFood.size(); j++) {
                    print += j+1;
                    print += ". ";
                    print += users.get(i).orderedFood.get(j);
                    print += "\n";
                }
            }
        }
        return print;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFoodPreferences(String[] preferences) {
        if (preferences.length == 1) {
            this.foodPreferences.add(preferences[0]);
        } else {
            for (int i = 0; i < preferences.length; i++) {
                this.foodPreferences.add(preferences[i]);
            }
        }
    }

    public static void createUserAccount() {
        OrderAccount or1 = new OrderAccount();
        or1.id += users.size() + 1;
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("You have selected option 3");
        System.out.println("Great! Let's start creating your account!");
        System.out.println("-------------------------------------------");

        System.out.print("Enter you name: ");
        String name = input.nextLine();
        name = name.trim();
        or1.setName(name);

        System.out.print("Enter a username: ");
        String username = input.nextLine();
        username = username.trim();
        or1.setUsername(username);

        System.out.print("Enter your password: ");
        String password = input.nextLine();
        password = password.trim();
        or1.setPassword(password);

        System.out.println();
        System.out.println("Our system wants to know your preferences from this list {salty, bitter, sweet, spicy}\n" +
                "This will help to sort Hawker food stalls by your preferences when you will order\n" +
                "If you want to choose many, then type in format like: preference1/preference2." +
                "\nIf you don't want to select, then just type something else. Thank you very much!");
        System.out.println();
        System.out.print("Enter your preference: ");
        String preferences = input.nextLine();
        preferences = preferences.toLowerCase();
        preferences = preferences.replaceAll("\\s", ""); // removing all spaces
        String[] parts = preferences.split("/"); // splitting by /
        boolean correctInput = false;
        for (int i = 0; i < parts.length; i++) {
            if (StallFoodMenu.preferences.contains(parts[i])) {
                correctInput = true;
            }
        }
        if (correctInput) or1.setFoodPreferences(parts);

        users.add(or1);
        System.out.println("--------------------------------------------");
        System.out.println("Your account has been successfully created!");
        System.out.println("--------------------------------------------");
    }

    public static boolean accountVerification(String Username, String Password) {
        boolean correctLogin = false;
        for (int i = 0; i < OrderAccount.users.size(); i++) {
            if (OrderAccount.users.get(i).username.equals(Username)) {
                if (OrderAccount.users.get(i).password.equals(Password)) {
                    correctLogin = true;
                }
            }
        }
        return correctLogin;
    }

    public static void insertAccountOrder(String foodName, String username) {
        boolean found = false;
        ArrayList<StallFoodMenu> userOrder = users.get(0).orderedFood;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                found = true;
                userOrder = users.get(i).orderedFood; // getting arraylist of ordered food of user which username matches.
                break;
            }
        }
        if (found) {
            for (int j = 0; j < HawkerFoodStall.StallsList.size(); j++) {
                for (int m = 0; m < HawkerFoodStall.StallsList.get(j).getStallFoodMenuList().size(); m++) {
                    // If food that user entered matches to food name, then add to the user Order
                    if (HawkerFoodStall.StallsList.get(j).getStallFoodMenuList().get(m).getFoodName().equals(foodName)) {
                        userOrder.add(HawkerFoodStall.StallsList.get(j).getStallFoodMenuList().get(m));
                    }
                }
            }
        }
    }

    public static void getMenuThatUserWant(String username) {
        boolean found = false;
        boolean ordered = false;
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)){
                index = i;
                found = true;
                break;
            }
        }
        if (found) {
            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.println("""
                        ** DO NOTE **
                        If you want to exit, type 0""");
                System.out.println();
                System.out.println("============================================================");
                System.out.println("Here is the list of all Hawker stalls");
                System.out.println("============================================================");
                HawkerFoodStall.ShowAllHawkerStalls();
                if (users.get(index).foodPreferences.size() != 0) {
                    System.out.println();
                    System.out.println("Our system can also sort stalls by your preference. It will also show the" +
                            " most expensive food and food which has the greatest amount of calories.");
                    System.out.println();
                    System.out.print("If you want to sort them type 'sort': ");
                    String sortAnswer = input.nextLine();
                    sortAnswer = sortAnswer.trim();
                    if (sortAnswer.toLowerCase().equals("sort")) {
                        System.out.println("===============================================================================================================");
                        // Printing Hawker food stalls with type that matches user preference.
                        for (int i = 0; i < users.get(index).foodPreferences.size(); i++) {
                            for (int j = 0; j < HawkerFoodStall.StallsList.size(); j++) {
                                if (HawkerFoodStall.StallsList.get(j).getFoodPreferences().equals(users.get(index).foodPreferences.get(i))) {
                                    System.out.printf("        %s%n", users.get(index).foodPreferences.get(i).toUpperCase()); // Printing preference, like 'salty', 'bitter' and etc.
                                    System.out.println(HawkerFoodStall.StallsList.get(j));
                                }
                            }
                        }
                        StallFoodMenu.getMostExpensiveFood();
                        StallFoodMenu.getHighestCalorieFood();
                        System.out.println("===============================================================================================================");
                    }
                }
                System.out.println();
                if (!ordered) {
                    System.out.println("If you want quit this option type '0'");
                }
                System.out.println();
                System.out.print("Choose a name of the Hawker stall that you would like to order from: ");
                String chosenStall = input.nextLine();
                chosenStall = chosenStall.trim();
                if (chosenStall.equals("0") && !ordered) {
                    break;
                } else {
                    // Checking if user entered correct name of the Stall
                    while (true) {
                        boolean correctInputOfStall = false;
                        int indexOfHawkerStall = 0;
                        for (int hawkerStalls = 0; hawkerStalls < HawkerFoodStall.StallsList.size(); hawkerStalls++) {
                            if (HawkerFoodStall.StallsList.get(hawkerStalls).getStallName().equals(chosenStall)) {
                                indexOfHawkerStall = hawkerStalls;
                                correctInputOfStall = true;
                                break;
                            }
                        }
                        if (correctInputOfStall) {
                            // adding food
                            boolean correctInputOfFood = false;
                            ArrayList<StallFoodMenu> menu = HawkerFoodStall.StallsList.get(indexOfHawkerStall).getStallFoodMenuList();
                            System.out.println();
                            System.out.println("Now, enter a food name that you want");
                            System.out.println("---------------------------------------------------------");
                            for (int i = 0; i < menu.size(); i++) {
                                System.out.printf("%d. %s%n", i+1, menu.get(i));
                            }
                            System.out.println();
                            System.out.print("Food name: ");
                            String chosenFood = input.nextLine();
                            chosenFood = chosenFood.trim();
                            for (int i = 0; i < menu.size(); i++) {
                                if (menu.get(i).getFoodName().equals(chosenFood)) {
                                    correctInputOfFood = true;
                                    break;
                                }
                            }
                            if (correctInputOfFood) {
                                insertAccountOrder(chosenFood, username);
                                System.out.println("-----------------------");
                                System.out.printf("%s ordered!%n", chosenFood);
                                System.out.println("-----------------------");
                                ordered = true;
                            } else {
                                System.out.println("Sorry, check again the name of the food you entered." +
                                        " The food you entered is not in the list. ");
                            }
                            System.out.println();
                            System.out.println("Do you want to continue ordering in this Stall? (Yes or No)");
                            String answer = input.nextLine();
                            answer = answer.trim();

                            if (answer.toLowerCase().equals("no")) {
                                break;
                            }
                        } else {
                            System.out.println("Sorry, but the name of Hawker stall you entered is not in the list");
                            break;
                        }
                    }
                    if (ordered) {
                        System.out.println();
                        System.out.println("------------------------------");
                        System.out.println("Here is your order so far: ");
                        System.out.println("------------------------------");
                        System.out.println(printOrderedFood(username));
                        System.out.print("If you want to delete something from your order type 'yes'. Otherwise" +
                                " type something else: ");
                        String choiceOfDelete = input.nextLine();
                        choiceOfDelete = choiceOfDelete.trim();
                        if (choiceOfDelete.toLowerCase().equals("yes")) {
                            deleteFoodFromUserOrder(username);
                        }
                    }
                    System.out.println();
                    System.out.print("Do you want to finish your order? (Yes or No): ");
                    String finalAnswer = input.nextLine();
                    finalAnswer = finalAnswer.trim();

                    if (finalAnswer.toLowerCase().equals("yes")) {
                        System.out.println("==========================================================");
                        System.out.println("Your order:");
                        System.out.println(printOrderedFood(username));
                        System.out.printf("Your total order price is: $%.2f %n", getTotalPriceOfOrder(username));
                        System.out.println("Thanks for choosing us!");
                        System.out.println("==========================================================");
                        clearUserOrder(username);
                        break;
                    }
                }
            }
        } else {
            System.out.println("User cannot be found");
        }
    }

    public static void deleteFoodFromUserOrder(String username) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("------------------------------------------");
            System.out.print("Enter the food name that you want to delete: ");
            String foodName = input.nextLine();
            foodName = foodName.trim();
            boolean deleted = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).username.equals(username)) {
                    for (int j = 0; j < users.get(i).orderedFood.size(); j++) {
                        if (users.get(i).orderedFood.get(j).getFoodName().equals(foodName)) {
                            users.get(i).orderedFood.remove(j);
                            deleted = true;
                            break;
                        }
                    }
                    break;
                }
            }
            if (deleted) {
                System.out.println("----------------------------------------------");
                System.out.println("Food was successfully deleted from your order!");
                System.out.println("----------------------------------------------");
            } else {
                System.out.println("Sorry, but the name you entered is not correct.");
            }
            System.out.println();
            System.out.print("Do you want to delete something more? (yes/no): ");
            String answer = input.nextLine();
            answer = answer.trim();
            if (answer.toLowerCase().equals("no")) {
                break;
            }
        }
    }

    public static double getTotalPriceOfOrder(String username) {
        double total = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                for (int j = 0; j < users.get(i).orderedFood.size(); j++) {
                    total += users.get(i).orderedFood.get(j).getPrice();
                }
            }
        }
        return total;
    }

    // This method is needed when user finishes completely his order. If user wants to reorder again, this method helps
    // to clean order before.
    public static void clearUserOrder(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                users.get(i).orderedFood.clear();
            }
        }
    }
}