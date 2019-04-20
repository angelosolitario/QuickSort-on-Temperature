/*
 * Author: Angelo Solitario
 * Project for 510
 *
 * */

package com.angelosolitario;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner newScanner = new Scanner(System.in);
        HashMap<String, Double> data;
        double[] array;

        String country; // state

        int input;
        boolean flag = true;

        System.out.println("Welcome!");
        data = readIn("data2.txt");
        array = convert(data);
        int len = array.length;
        quickSort(array, 0, len - 1);

        while (flag) {
            System.out.println("Please enter a number");
            printMenu();
            input = scanner.nextInt();

            switch (input) {
                case 1:
                    String result = getKeyFromValue(data, array[len - 1]);
                    System.out.println(result + " : " + array[len - 1] + "\n");
                    break;
                case 2:
                    String result2 = getKeyFromValue(data, array[0]);
                    System.out.println(result2 + " : " + array[0] + "\n");
                    break;
                case 3:
                    double temperature;
                    try {
                        System.out.println("\nPlease type in a state");
                        country = newScanner.nextLine().toLowerCase();
                        temperature = data.get(country.toLowerCase());
                        System.out.println(temperature + "\n");
                    } catch (InputMismatchException e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:
                    System.out.println("Enter a number!");
                    int number = scanner.nextInt();
                    if (number > len) {
                        System.out.println("Please enter a number <= " + len + "\n");
                    } else {
                        String result3 = getKeyFromValue(data, array[len - number]);
                        System.out.println(result3 + " : " + array[len - number] + "\n");
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a number!");
                    break;
            }
        }
        System.out.println("Thank you!");
    }


    // a function to convert the hashmap values into an array
    public static double[] convert(HashMap<String, Double> hashMap) {
        ArrayList<Double> list = new ArrayList<>(hashMap.values());
        double[] newList = new double[list.size()];

        for (int i = 0; i < list.size(); i++) {
            newList[i] = list.get(i);
        }
        return newList;
    }

    // this is the only way I could think of to get the key from the value
    public static String getKeyFromValue(HashMap<String, Double> hashMap, Double state) {
        for (String d : hashMap.keySet()) {
            if (hashMap.get(d).equals(state)) {
                return d;
            }
        }
        if (!hashMap.containsValue(state)) {
            System.out.println("State not found!");
        }
        return null;
    }

    //TODO DONE
    // quicksort
    public static void quickSort(double[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);  // Before pi
            quickSort(array, pi + 1, high); // After pi
        }
    }

    //TODO DONE
    //partition
    public static int partition(double[] array, int low, int high) {
        double pivot = array[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (array[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        double temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;

    }

    //TODO DONE
    // this function prints out a menu
    public static void printMenu() {
        System.out.println("1 - Hottest State in F");
        System.out.println("2 - Coldest State in F");
        System.out.println("3 - Look up a state");
        System.out.println("4 - Look up #th hottest state");
        System.out.println("0 -  to exit");
    }

    //TODO DONE
    // a function to read in data from text file
    // for some reason <Double, String> only reads in 47 elements
    public static HashMap<String, Double> readIn(String fileName) {
        HashMap<String, Double> temperatureStates = new HashMap<>(); // changed from HashMap
        File file = new File(fileName); // path of the text file
        String line;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] states = line.split(":");
                temperatureStates.put(states[0].toLowerCase(), Double.parseDouble(states[1]));
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return temperatureStates;
    }
}
