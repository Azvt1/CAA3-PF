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

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences.add(foodPreferences);
    }

    public static void createUserAccount() {
        OrderAccount or1 = new OrderAccount();
        or1.id += users.size() + 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Great! Let's start creating your account!");

        System.out.println("-----------------------------------");
        System.out.println("Enter you name: ");
        String name = input.nextLine();
        or1.setName(name);

        System.out.println("Enter a username: ");
        String username = input.nextLine();
        or1.setUsername(username);

        System.out.println("Enter your password: ");
        String password = input.nextLine();
        or1.setPassword(password);

        System.out.println("Enter your preferences: ");
        String preferences = input.nextLine();
        or1.setFoodPreferences(preferences);

        users.add(or1);
        System.out.println(or1);
        System.out.println("Your account has been successfully created!");
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
        ArrayList<StallFoodMenu> userOrder= users.get(0).orderedFood;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                found = true;
                userOrder = users.get(i).orderedFood;
                break;
            }
        }
        if (found) {
            for (int j = 0; j < HawkerFoodStall.StallsList.size(); j++) {
                for (int m = 0; m < HawkerFoodStall.StallsList.get(j).getStallFoodMenuList().size(); m++) {
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
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)){
                found = true;
                break;
            }
        }
        if (found) {
            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.println("""
                                        ** DO NOTE **
                                        If you want to exit, type 0
                        ------------------------------------------------------------""");
                System.out.println("Here is the list of all Hawker stalls");
                System.out.println("------------------------------------------------------------");
                HawkerFoodStall.ShowAllHawkerStalls();
                System.out.println("Our system can also sort stalls by 4 types of food(salty, bitter, spicy, sweet)");
                System.out.println("If you want to sort them type 'sort': ");
                String sortAnswer = input.nextLine();
                if (sortAnswer.toLowerCase().equals("sort")) {
                    System.out.println("By which type do you want to sort? (salty, bitter, spicy, sweet)");
                    String sorting = input.nextLine();
                    // Просто сделать сортАнсер = преференсу, спросить у юзера про преферс при создании аккаунта
                    // и спроить хочет ли он отсортировать по его преференсу, если да, то ок.
                    switch (sorting) {
                        case "salty":
                            for (int i = 0; i < HawkerFoodStall.StallsList.size(); i++) {
                                if (HawkerFoodStall.StallsList.get(i).getFoodPreferences().equals("salty")){
                                    System.out.println(HawkerFoodStall.StallsList.get(i));
                                }
                            }
                            break;
                        case "bitter":
                            for (int i = 0; i < HawkerFoodStall.StallsList.size(); i++) {
                                if (HawkerFoodStall.StallsList.get(i).getFoodPreferences().equals("bitter")){
                                    System.out.println(HawkerFoodStall.StallsList.get(i));
                                }
                            }
                        case "spicy":
                            for (int i = 0; i < HawkerFoodStall.StallsList.size(); i++) {
                                if (HawkerFoodStall.StallsList.get(i).getFoodPreferences().equals("spicy")){
                                    System.out.println(HawkerFoodStall.StallsList.get(i));
                                }
                            }
                        case "sweet":
                            for (int i = 0; i < HawkerFoodStall.StallsList.size(); i++) {
                                if (HawkerFoodStall.StallsList.get(i).getFoodPreferences().equals("sweet")){
                                    System.out.println(HawkerFoodStall.StallsList.get(i));
                                }
                            }
                    }
                }
                System.out.println("Choose a name of the Hawker stall that you would like to order from: ");
                String chosenStall = input.nextLine();
                if (chosenStall.equals("0")) {
                    break;
                } else {
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
                            boolean correctInputOfFood = false;
                            ArrayList<StallFoodMenu> menu = HawkerFoodStall.StallsList.get(indexOfHawkerStall).getStallFoodMenuList();
                            System.out.println("Now, enter a food name that you want: ");
                            System.out.println("---------------MENU---------------");
                            System.out.println(menu);
                            String chosenFood = input.nextLine();
                            for (int i = 0; i < menu.size(); i++) {
                                if (menu.get(i).getFoodName().equals(chosenFood)) {
                                    correctInputOfFood = true;
                                    break;
                                }
                            }
                            if (correctInputOfFood) {
                                insertAccountOrder(chosenFood, username);
                                System.out.println("Ordered!");
                                ordered = true;
                            } else {
                                System.out.println("Sorry, check again the name of the food you entered." +
                                        " The food you entered is not in the list. ");
                            }
                            System.out.println("Do you want to continue ordering in this Stall? (Yes or No)");
                            String answer = input.nextLine();

                            if (answer.toLowerCase().equals("no")) {
                                break;
                            }
                        } else {
                            System.out.println("Sorry, but the name of Hawker stall you entered is not in the list");
                            break;
                        }
                    }
                    if (ordered) {
                        System.out.println("Here is your order so far: ");
                        System.out.println(printOrderedFood(username));
                        System.out.println("If you want to delete something from your order type 'yes'. Otherwise" +
                                " type something else: ");
                        String choiceOfDelete = input.nextLine();
                        if (choiceOfDelete.toLowerCase().equals("yes")) {
                            deleteFoodFromUserOrder(username);
                        }
                    }
                    System.out.println("Do you want to finish your order? (Yes or No)");
                    String finalAnswer = input.nextLine();

                    if (finalAnswer.toLowerCase().equals("yes")) {
                        System.out.println("==========================================================");
                        System.out.printf("Your total order price is: %.2f %n", getTotalPriceOfOrder(username));
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
            System.out.println("Enter the food name that you want to delete: ");
            String foodName = input.nextLine();
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
                System.out.println("Food was successfully deleted from your order!");
            } else {
                System.out.println("Sorry, but the name you entered is not correct.");
            }
            System.out.println("Do you want to delete something more? (yes/no)");
            String answer = input.nextLine();
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

    public static void clearUserOrder(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                users.get(i).orderedFood.clear();
            }
        }
    }
}