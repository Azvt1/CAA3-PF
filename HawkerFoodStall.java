package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HawkerFoodStall {
    private String stallName;
    private String description;
    private String ownerName;
    private String address;
    private int postal;
    private String preference;
    private ArrayList<StallFoodMenu> stallFoodMenuList = new ArrayList<StallFoodMenu>();

    // Arraylist for containing new Stalls
    static List<HawkerFoodStall> StallsList = new ArrayList<HawkerFoodStall>();



    // public HawkerFoodStall(){};+
    // Constructor
    public HawkerFoodStall(String stallName,String description,String ownerName,String address, int postal) {
        this.stallName = stallName;
        this.description = description;
        this.ownerName = ownerName;
        this.address = address;
        this.postal = postal;
    }

    static String[] FoodPreferences = new String[]{"spicy", "salty", "bitter", "sweet"};

    @Override
    public String toString() {
        String FoodMenuList = "";
        for (int i = 0; i < stallFoodMenuList.size(); i++) {
            FoodMenuList += stallFoodMenuList.get(i);
        }
        return String.format("Name: %s%nDescription: %s%nOwner name: %s%nAddress: %s%nPostal: %d%nMenu: %s%n-----------------------------", stallName, description, ownerName, address, postal, FoodMenuList);
    }
    // Set & Get method for stallName
    public void setStallName(String stallName) {
        this.stallName = stallName;
    }
    public String getStallName() {
        return stallName;
    }

    // Set & Get method for description
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    // Set & Get method for Owner name
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerName() {
        return ownerName;
    }

    // Set & Get methods for Address
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    // Set & get methods for postal
    public void setPostal(int postal) {this.postal = postal;}
    public int getPostal() {
        return postal;
    }

    // Set & get methods for foodPreferences
    public void setFoodPreferences(String preference) {
        this.preference = preference;
    }
    public String getFoodPreferences() {return preference;}

    // Get method for Hawker list
    public void setStallFoodMenuList(ArrayList<StallFoodMenu> stallFoodMenuList) {
        this.stallFoodMenuList = stallFoodMenuList;
    }
    public ArrayList<StallFoodMenu> getStallFoodMenuList() {
        return stallFoodMenuList;
    }

    public void addNewHawkerFoodStall() {
        // Creating an object with empty properties. In the end add filled object to list of all Hawker food stalls
        HawkerFoodStall h1 = new HawkerFoodStall("", "", "", "", 0);
        Scanner newHawker = new Scanner(System.in);
        Scanner foodName = new Scanner(System.in);
        Scanner preference = new Scanner(System.in);

        System.out.println("Here we go! Hawker should have 'name', 'description', 'ownerName', 'address', 'postal' and Menu list");
        System.out.print("Now, enter the name: ");
        String givenName = newHawker.nextLine();
        givenName = givenName.trim();
        h1.setStallName(givenName);

        System.out.print("Now, enter the description: ");
        String givenDescription = newHawker.nextLine();
        givenDescription = givenDescription.trim();
        h1.setDescription(givenDescription);

        System.out.print("Now, enter the ownerName: ");
        String givenOwnerName = newHawker.nextLine();
        givenOwnerName = givenOwnerName.trim();
        h1.setOwnerName(givenOwnerName);

        System.out.print("Now, enter the address: ");
        String givenAddress = newHawker.nextLine();
        givenAddress = givenAddress.trim();
        h1.setAddress(givenAddress);

        boolean keepGoing = true;
        System.out.print("Now enter the postal: ");
        // Try and Catch part !!!
        while (keepGoing) {
            try {
                Scanner input = new Scanner(System.in);
                int givenPostal = input.nextInt();
                if (givenPostal < 0) {
                    System.out.println("Sorry, but the postal can not be negative!");
                    continue;
                } else {
                    h1.setPostal(givenPostal);
                }
                keepGoing = false;
            } catch (InputMismatchException e){
                System.out.println("Sorry but you need to enter a number!");
                System.out.print("Enter the postal: ");
            }
        }

        // Asking to enter type of Hawker food stall in order to print that one which matches user's preferences.
        System.out.println("\n-----------------------------------");
        System.out.println("Now, can you please enter type that match your Hawker Stall. If you don't want, type something" +
                " else");
        System.out.print("Types: ");
        for (int i = 0; i < FoodPreferences.length; i++) {
            System.out.print(FoodPreferences[i] + " ");
        }
        System.out.println("\n-----------------------------------");
        System.out.print("Your type: ");
        String givenPreference = preference.nextLine();
        givenPreference = givenPreference.trim();
        boolean preferenceIsAdded = false;
        for (int i = 0; i < FoodPreferences.length; i++) {
            if (FoodPreferences[i].equals(givenPreference.toLowerCase())) {
                h1.setFoodPreferences(givenPreference);
                System.out.println("----------------------");
                System.out.println("Thank you very much!");
                System.out.println("----------------------");
                preferenceIsAdded = true;
                break;
            }
        }
        if (!preferenceIsAdded) h1.setFoodPreferences("none");
        // Adding menu to Hawker food stall, to stop, user need to type 'stop' when food name is asked.
        System.out.println();
        System.out.println("""
                Now, write your menu, each meal should contain 'food name', 'calories', 'price'.
                                       -----IMPORTANT-----
                When you want to finish creating menu just type 'stop', when food name will be asked again.
                """);
        while (true) {
            StallFoodMenu s1 = new StallFoodMenu("", 0, 0);
            System.out.println("------------------------------------------");
            System.out.print("Enter food name: ");
            String givenFoodName = foodName.nextLine();
            givenFoodName = givenFoodName.trim();
            if (givenFoodName.equals("stop")) {
                break;
            }
            else {
                s1.setFoodName(givenFoodName);

                keepGoing = true;
                while (keepGoing) {
                    try {
                        Scanner calories = new Scanner(System.in);
                        System.out.print("Enter calories of the food: ");
                        double givenCalories = calories.nextDouble();
                        if (givenCalories < 0) {
                            System.out.println("Sorry, but the number of calories should not be negative");
                            continue;
                        } else {
                            s1.setCalories(givenCalories);
                        }
                        keepGoing = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Sorry, but calories should be a number");
                    }
                }

                keepGoing = true;
                while (keepGoing) {
                    try {
                        Scanner price = new Scanner(System.in);
                        System.out.print("Enter price of the food: ");
                        double givenPrice = price.nextDouble();
                        if (givenPrice < 0) {
                            System.out.println("Sorry, but the number should be not negative.");
                            continue;
                        } else {
                            s1.setPrice(givenPrice);
                        }
                        keepGoing = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Sorry, but the price should be a number");
                    }
                }

                h1.stallFoodMenuList.add(s1);
                // DO A SET method FOR THE MENU
            }

        }
        StallsList.add(h1);
        System.out.println("-----------------------------");
        System.out.println("** Hawker stall was successfully added! **");
        System.out.println("------------------------------");
        System.out.println("Your Hawker stall looks like:");
        System.out.println(h1);
    }

    public void deleteHawkerFoodStall() {
        Scanner deleteHawker = new Scanner(System.in);
        ShowAllHawkerStalls();
        System.out.println("Enter the name of Hawker stall that you want to delete: ");
        String deleteName = deleteHawker.nextLine().toLowerCase();
        deleteName = deleteName.trim();
        boolean wasNotDeleted = true;
        for (int i = 0; i < StallsList.size(); i++) {
            if (StallsList.get(i).getStallName().toLowerCase().equals(deleteName)) {
                StallsList.remove(i);
                stallFoodMenuList.clear();
                System.out.println("------------------------------------");
                System.out.println("Hawker food stall has been deleted!");
                System.out.println("------------------------------------");
                wasNotDeleted = false;
                break;
            }
        }
        if (wasNotDeleted) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Hawker food stall was not deleted, because there is no such Hawker food stall =(");
            System.out.println("----------------------------------------------------------------------------------");
        }
    }

    public static void ShowAllHawkerStalls() {
        for (int i = 0; i < StallsList.size(); i++) {
            System.out.println(StallsList.get(i));
        }
    }


}