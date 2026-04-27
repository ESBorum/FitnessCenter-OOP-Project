import java.util.Scanner;

import util.InputHelper;
import exceptions.ActivityFullException;
import exceptions.DuplicateBookingException;
import model.Activity;
import model.Booking;
import model.CardioClass;
import model.Member;
import model.StrengthClass;
import service.BookingService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingService bookingService = new BookingService();

        boolean running = true;

        // Main loop for user interaction
        while (running) {
            System.out.println("\n=== FITNESS CENTER BOOKING SYSTEM ===");
            System.out.println("1. Create member");
            System.out.println("2. Create activity");
            System.out.println("3. Show all members");
            System.out.println("4. Show all activities");
            System.out.println("5. Create booking");
            System.out.println("6. Cancel booking");
            System.out.println("7. Show all bookings");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clears scanner buffer

            switch (choice) {
                case 1:
                    createMember(scanner, bookingService);
                    break;

                case 2:
                    createActivity(scanner, bookingService);
                    break;

                case 3:
                    bookingService.showAllMembers();
                    break;

                case 4:
                    bookingService.showAllActivities();
                    break;

                case 5:
                    createBooking(scanner, bookingService);
                    break;

                case 6:
                    cancelBooking(scanner, bookingService);
                    break;

                case 7:
                    bookingService.showAllBookings();
                    break;

                case 8:
                    running = false;
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    // Helper method to create members
    private static void createMember(Scanner scanner, BookingService bookingService) {
        int id = InputHelper.readInt(scanner, "Enter member ID: ");
        String name = InputHelper.readString(scanner, "Enter name: ");

        String email = InputHelper.readString(scanner, "Enter email: ");

        int age = InputHelper.readInt(scanner, "Enter age: ");

        Member member = new Member(id, name, email, age);
        bookingService.addMember(member);

        System.out.println("Member created successfully.");
    }

    // Helper method to create activities
    private static void createActivity(Scanner scanner, BookingService bookingService) {
        int id = InputHelper.readInt(scanner, "Enter activity ID: ");
        scanner.nextLine();

        String name = InputHelper.readString(scanner, "Enter activity name: ");

        int maxParticipants = InputHelper.readInt(scanner, "Enter max participants: ");

        double price = InputHelper.readDouble(scanner, "Enter price: ");
        scanner.nextLine();

        System.out.println("Choose activity type:");
        System.out.println("1. Strength class");
        System.out.println("2. Cardio class");
        System.out.print("Type: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        // Create activity based on user choice
        if (type == 1) {
            System.out.print("Enter muscle group: ");
            String muscleGroup = scanner.nextLine();

            StrengthClass strengthClass = new StrengthClass(id, name, maxParticipants, price, muscleGroup);
            bookingService.addActivity(strengthClass);

            System.out.println("Strength activity created successfully.");

            // Additional activity types can be added here in the future
        } else if (type == 2) {
            System.out.print("Enter intensity level: ");
            String intensityLevel = scanner.nextLine();

            CardioClass cardioClass = new CardioClass(id, name, maxParticipants, price, intensityLevel);
            bookingService.addActivity(cardioClass);

            System.out.println("Cardio activity created successfully.");

            // Additional activity types can be added here in the future
        } else {
            System.out.println("Invalid activity type.");
        }
    }

    // Helper method to create a booking
    private static void createBooking(Scanner scanner, BookingService bookingService) {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();

        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();

        System.out.print("Enter activity ID: ");
        int activityId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter date/time: ");
        String dateTime = scanner.nextLine();

        Member member = bookingService.findMemberById(memberId);
        Activity activity = bookingService.findActivityById(activityId);

        // Validate member and activity existence
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        // Validate activity existence
        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }

        // Attempt to create booking and handle potential exceptions
        try {
            Booking booking = bookingService.createBooking(bookingId, member, activity, dateTime);
            System.out.println("Booking created successfully:");
            System.out.println(booking);

        } catch (ActivityFullException | DuplicateBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    // Helper method to cancel a booking
    private static void cancelBooking(Scanner scanner, BookingService bookingService) {
        System.out.print("Enter booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        boolean cancelled = bookingService.cancelBooking(bookingId);

        // Inform user of cancellation result
        if (cancelled) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }
}