package util;

import java.util.Scanner;

public class InputHelper {

    // Helper methods for reading different types of input from the user
    public static int readInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    // Helper method to read a double value from the user
    public static double readDouble(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }   

    // Helper method to read a string value from the user
    public static String readString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}