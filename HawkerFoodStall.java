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
    //public enum FoodPreference { spicy, salty, bitter, sweet };
    //public FoodPreference preference = FoodPreference.bitter;

    static String[] FoodPreferences = new String[]{"spicy", "salty", "bitter", "sweet"};

    @Override
    public String toString() {
        return "Name = " + stallName + ", description = " + description + ", owner Name = " + ownerName + ", " +
                "address = " + address + ", postal = " + postal + ", menu = " + stallFoodMenuList;
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
        HawkerFoodStall h1 = new HawkerFoodStall("", "", "", "", 0);
        Scanner newHawker = new Scanner(System.in);
        Scanner foodName = new Scanner(System.in);
        Scanner preference = new Scanner(System.in);


        System.out.println("Here we go! Hawker should have 'name', 'description', 'ownerName', 'address', 'postal' and Menu list");
        System.out.print("Now, enter the name: ");
        String givenName = newHawker.nextLine();
        h1.setStallName(givenName);

        System.out.print("Now, enter the description: ");
        String givenDescription = newHawker.nextLine();
        h1.setDescription(givenDescription);

        System.out.print("Now, enter the ownerName: ");
        String givenOwnerName = newHawker.nextLine();
        h1.setOwnerName(givenOwnerName);

        System.out.print("Now, enter the address: ");
        String givenAddress = newHawker.nextLine();
        h1.setAddress(givenAddress);

        boolean keepGoing = true;
        int givenPostal = 0;
        System.out.print("Now enter the postal: ");
        while (keepGoing) {
            try {
                Scanner postalInput = new Scanner(System.in);
                givenPostal = postalInput.nextInt();
                keepGoing = false;
            } catch (InputMismatchException e) {
                System.out.println("Sorry, but the postal should be a number.");
                System.out.print("Enter a postal: ");
            }
        }
        h1.setPostal(givenPostal);

        System.out.println("Now, can you please enter type that match your Hawker Stall. If you don't want, type something" +
                " else");
        for (int i = 0; i < FoodPreferences.length; i++) {
            System.out.println(FoodPreferences[i]);
        }
        String givenPreference = preference.nextLine();
        for (int i = 0; i < FoodPreferences.length; i++) {
            if (FoodPreferences[i].equals(givenPreference.toLowerCase())) {
                h1.setFoodPreferences(givenPreference);
                System.out.println("Thank you very much!");
                break;
            } else {
                System.out.println("=(");
                break;
            }
        }
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
                        s1.setCalories(givenCalories);
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
                        s1.setPrice(givenPrice);
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
        System.out.println("** Hawker stall was successfully added! **");
        System.out.println("Your Hawker stall looks like:");
        System.out.println(h1);
    }

    public void deleteHawkerFoodStall() {
        Scanner deleteHawker = new Scanner(System.in);
        System.out.println("Enter the name of Hawker stall that you want to delete: ");
        for (int j = 0; j < StallsList.size(); j++) {
            System.out.println(StallsList.get(j));
        }
        String deleteName = deleteHawker.nextLine().toLowerCase();
        boolean wasNotDeleted = true;
        for (int i = 0; i < StallsList.size(); i++) {
            if (StallsList.get(i).getStallName().toLowerCase().equals(deleteName)) {
                StallsList.remove(i);
                stallFoodMenuList.clear();
                System.out.println("Hawker food stall has been deleted!");
                wasNotDeleted = false;
                break;
            }
        }
        if (wasNotDeleted) {
            System.out.println("Hawker food stall was not deleted, because there is no such Hawker food stall =(");
        }
    }

    public static void ShowAllHawkerStalls() {
        for (int i = 0; i < StallsList.size(); i++) {
            System.out.println(StallsList.get(i));
        }
    }


}