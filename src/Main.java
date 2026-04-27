import exceptions.ActivityFullException;
import exceptions.DuplicateBookingException;
import model.Activity;
import model.Booking;
import model.CardioClass;
import model.Member;
import model.StrengthClass;
import service.BookingService;
import task.BookingConfirmationTask;
import util.InputHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingService bookingService = new BookingService();

        boolean running = true;

        // Main program loop - runs until user exits
        while (running) {
            System.out.println("\n=== FITNESS CENTER BOOKING SYSTEM ===");
            System.out.println("1. Create member");
            System.out.println("2. Create activity");
            System.out.println("3. Show all members");
            System.out.println("4. Show all activities");
            System.out.println("5. Create booking");
            System.out.println("6. Cancel booking");
            System.out.println("7. Show all bookings");
            System.out.println("8. Sort members by name");
            System.out.println("9. Sort activities by price");
            System.out.println("10. Show available activities");
            System.out.println("11. Run thread demo");
            System.out.println("12. Exit");

            int choice = InputHelper.readInt(scanner, "Choose option: ");

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
                    System.out.println("=== ALL BOOKINGS ===");

                    if (bookingService.getBookings().isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        for (Booking booking : bookingService.getBookings()) {
                            System.out.println(booking);
                        }
                    }
                    break;

                case 8:
                    bookingService.sortMembersByName();
                    System.out.println("Members sorted by name.");
                    bookingService.showAllMembers();
                    break;

                case 9:
                    bookingService.sortActivitiesByPrice();
                    System.out.println("Activities sorted by price.");
                    bookingService.showAllActivities();
                    break;

                case 10:
                    System.out.println("=== AVAILABLE ACTIVITIES ===");
                    bookingService.showAvailableActivities();
                    break;

                case 11:
                    runThreadDemo(bookingService);
                    break;

                case 12:
                    running = false;
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static void createMember(Scanner scanner, BookingService bookingService) {
        int id = InputHelper.readInt(scanner, "Enter member ID: ");
        String name = InputHelper.readString(scanner, "Enter name: ");
        String email = InputHelper.readString(scanner, "Enter email: ");
        int age = InputHelper.readInt(scanner, "Enter age: ");

        Member member = new Member(id, name, email, age);
        bookingService.addMember(member);

        System.out.println("Member created successfully.");
    }

    private static void createActivity(Scanner scanner, BookingService bookingService) {
        int id = InputHelper.readInt(scanner, "Enter activity ID: ");
        String name = InputHelper.readString(scanner, "Enter activity name: ");
        int maxParticipants = InputHelper.readInt(scanner, "Enter max participants: ");
        double price = InputHelper.readDouble(scanner, "Enter price: ");

        System.out.println("Choose activity type:");
        System.out.println("1. Strength class");
        System.out.println("2. Cardio class");

        int type = InputHelper.readInt(scanner, "Type: ");

        if (type == 1) {
            String muscleGroup = InputHelper.readString(scanner, "Enter muscle group: ");

            StrengthClass strengthClass = new StrengthClass(id, name, maxParticipants, price, muscleGroup);
            bookingService.addActivity(strengthClass);

            System.out.println("Strength activity created successfully.");

        } else if (type == 2) {
            String intensityLevel = InputHelper.readString(scanner, "Enter intensity level: ");

            CardioClass cardioClass = new CardioClass(id, name, maxParticipants, price, intensityLevel);
            bookingService.addActivity(cardioClass);

            System.out.println("Cardio activity created successfully.");

        } else {
            System.out.println("Invalid activity type.");
        }
    }

    private static void createBooking(Scanner scanner, BookingService bookingService) {
        int bookingId = InputHelper.readInt(scanner, "Enter booking ID: ");
        int memberId = InputHelper.readInt(scanner, "Enter member ID: ");
        int activityId = InputHelper.readInt(scanner, "Enter activity ID: ");
        String dateTime = InputHelper.readString(scanner, "Enter date/time: ");

        Member member = bookingService.findMemberById(memberId);
        Activity activity = bookingService.findActivityById(activityId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }

        try {
            Booking booking = bookingService.createBooking(bookingId, member, activity, dateTime);
            System.out.println("Booking created successfully:");
            System.out.println(booking);

        } catch (ActivityFullException | DuplicateBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static void cancelBooking(Scanner scanner, BookingService bookingService) {
        int bookingId = InputHelper.readInt(scanner, "Enter booking ID to cancel: ");

        boolean cancelled = bookingService.cancelBooking(bookingId);

        if (cancelled) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void runThreadDemo(BookingService bookingService) {
        if (bookingService.getBookings().size() < 2) {
            System.out.println("Create at least 2 bookings before running the thread demo.");
            return;
        }

        Booking booking1 = bookingService.getBookings().get(0);
        Booking booking2 = bookingService.getBookings().get(1);

        Thread thread1 = new Thread(new BookingConfirmationTask(booking1));
        Thread thread2 = new Thread(new BookingConfirmationTask(booking2));

        thread1.start();
        thread2.start();

        System.out.println("Thread demo started. Confirmations are being sent asynchronously.");
    }
}