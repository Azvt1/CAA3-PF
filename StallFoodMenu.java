package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StallFoodMenu {
    private String foodName;
    private double calories;
    private double price;


    //Constructor
    public StallFoodMenu(String foodName, double calories, double price) {
        this.foodName = foodName;
        this.calories = calories;
        this.price = price;
    }

    @Override
    public String toString() {
        return "food name: " + foodName + ", calories: " + calories + ", price: " + price;
    }

    // Set & get methods for the foodName
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    // Set & get methods for calories
    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }

    // Set & get methods for price
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void addNewFoodMenu() {
        boolean found = false;
        int index = 0;
        for (int names = 0; names < HawkerFoodStall.StallsList.size(); names++) {
            System.out.println(HawkerFoodStall.StallsList.get(names));
        }
        System.out.println("Enter name of the Hawker stall to which you want add a menu(write exactly how the name is" +
                " written): ");
        Scanner chooseHawkerToAddMenu = new Scanner(System.in);
        String chosenHawkerToAdd = chooseHawkerToAddMenu.nextLine();// name of the Hawker stall that user wants to add menu from
        for (int n = 0; n < HawkerFoodStall.StallsList.size(); n++) {
            if (HawkerFoodStall.StallsList.get(n).getStallName().equals(chosenHawkerToAdd)) {
                found = true;
                index = n;
                break;
            }
        }
        if (found) {
            ArrayList<StallFoodMenu> chosenHawker = HawkerFoodStall.StallsList.get(index).getStallFoodMenuList();
            Scanner input = new Scanner(System.in);
            StallFoodMenu s1 = new StallFoodMenu("", 0, 0);

            System.out.println("Now, you can add you food menu!");
            System.out.print("Enter a food name: ");
            String inputFoodName = input.nextLine();
            s1.setFoodName(inputFoodName);

            System.out.print("Enter calories: ");
            double inputCalories = input.nextDouble();
            s1.setCalories(inputCalories);

            System.out.print("Enter price: ");
            double inputPrice = input.nextDouble();
            s1.setPrice(inputPrice);

            chosenHawker.add(s1);
            System.out.println("Your food menu was successfully added!");
        } else {
            System.out.println("Sorry, but the name you entered is not on the list =( ");
        }
    }

    public void deleteFoodFromMenu() {
        Scanner inputOfHawkerName = new Scanner(System.in);
        int index = 0;
        boolean FoundTheName = false;
        for (int names = 0; names < HawkerFoodStall.StallsList.size(); names++) {
            System.out.println(HawkerFoodStall.StallsList.get(names));
        }
        System.out.println("Choose the name of the Hawker stall, that you want to delete menu from: ");
        String ChosenHawkerName = inputOfHawkerName.nextLine();
        for (int i = 0; i < HawkerFoodStall.StallsList.size(); i++) {
            if (HawkerFoodStall.StallsList.get(i).getStallName().equals(ChosenHawkerName)) {
                index = i;
                FoundTheName = true;
                break;
            }
        }
        if (FoundTheName) {
            ArrayList<StallFoodMenu> ChosenHawkerList = HawkerFoodStall.StallsList.get(index).getStallFoodMenuList();
            System.out.println(ChosenHawkerList);
            System.out.println("Now enter the name of the food, that you want to delete: ");
            Scanner inputName = new Scanner(System.in);
            String NameToDelete = inputName.nextLine();
            for (int foodNameIndex = 0; foodNameIndex < ChosenHawkerList.size(); foodNameIndex++) {
                if (ChosenHawkerList.get(foodNameIndex).foodName.equals(NameToDelete)) {
                    ChosenHawkerList.remove(foodNameIndex);
                    System.out.println("Menu has been successfully deleted!");
                    break;
                }
            }
        } else {
            System.out.println("Sorry but the name you entered is not on the list =(");
        }
    }


    // Run through StallList, then take first object, return from it Stallfoodmenulist, add prices of food to the food
    // prices array, and same for the rest. Finally, detecting maximum value from prices array, after what, return all
    // menus that have same amount of price.
    public void getMostExpensiveFood() {
        // Adding prices to the array
        ArrayList<Double> foodPrices = new ArrayList<Double>();
        for (int objects = 0; objects < HawkerFoodStall.StallsList.size(); objects++) {
            ArrayList<StallFoodMenu> menu = new ArrayList<>();
            menu = HawkerFoodStall.StallsList.get(objects).getStallFoodMenuList();
            for (int i = 0; i < menu.size(); i++) {
                foodPrices.add(menu.get(i).price);
            }
        }
        // Finding the maximum
        double maximum = -1;
        double price = 0;
        for (int j = 0; j < foodPrices.size(); j++) {
            price = foodPrices.get(j);
            if (price > maximum) {
                maximum = price;
            }
        }
        // Printing the menu with maximum value
        for (int n = 0; n < HawkerFoodStall.StallsList.size(); n++) {
            ArrayList<StallFoodMenu> menu = new ArrayList<>();
            menu = HawkerFoodStall.StallsList.get(n).getStallFoodMenuList();
            for (int m = 0; m < menu.size(); m++) {
                if (menu.get(m).price == maximum) {
                    System.out.printf("The most expensive food is %s, its price: %s, its calories: %s.",
                            menu.get(m).getFoodName(), menu.get(m).getPrice(), menu.get(m).getCalories());
                    System.out.printf(" This food is from '%s' stall\n", HawkerFoodStall.StallsList.get(n).getStallName());
                }
            }
        }
    }


    public void getHighestCalorieFood() {
        ArrayList<Double> foodCalorie = new ArrayList<Double>();
        for (int objects = 0; objects < HawkerFoodStall.StallsList.size(); objects++) {
            ArrayList<StallFoodMenu> menu = new ArrayList<>();
            menu = HawkerFoodStall.StallsList.get(objects).getStallFoodMenuList();
            for (int i = 0; i < menu.size(); i++) {
                foodCalorie.add(menu.get(i).calories);
            }
        }
        // Finding the maximum
        double maximum = -1;
        double calorie = 0;
        for (int j = 0; j < foodCalorie.size(); j++) {
            calorie = foodCalorie.get(j);
            if (calorie > maximum) {
                maximum = calorie;
            }
        }
        // Printing the menu with maximum value
        for (int n = 0; n < HawkerFoodStall.StallsList.size(); n++) {
            ArrayList<StallFoodMenu> menu = new ArrayList<>();
            menu = HawkerFoodStall.StallsList.get(n).getStallFoodMenuList();
            for (int m = 0; m < menu.size(); m++) {
                if (menu.get(m).calories == maximum) {
                    System.out.printf("Food that contains the most calories is: %s, its price: %s, its calories: %s",
                            menu.get(m).getFoodName(), menu.get(m).getPrice(), menu.get(m).getCalories());
                    System.out.printf(" This food is from '%s' stall\n", HawkerFoodStall.StallsList.get(n).getStallName());
                }
            }
        }
    }
}