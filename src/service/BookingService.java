package service;

import java.util.ArrayList;

import exceptions.ActivityFullException;
import exceptions.DuplicateBookingException;
import model.Activity;
import model.Booking;
import model.Member;

// Service class to manage members, activities, and bookings
public class BookingService {
    private Repository<Member> memberRepository;
    private Repository<Activity> activityRepository;
    private Repository<Booking> bookingRepository;

    // Constructor initializes repositories
    public BookingService() {
        memberRepository = new Repository<>();
        activityRepository = new Repository<>();
        bookingRepository = new Repository<>();
    }

    // Methods to add members and activities
    public void addMember(Member member) {
        memberRepository.add(member);
    }

    public void addActivity(Activity activity) {
        activityRepository.add(activity);
    }

    // Method to create a booking with error handling for full activities and duplicate bookings
    public Booking createBooking(int bookingId, Member member, Activity activity, String dateTime)
            throws ActivityFullException, DuplicateBookingException {

        if (activity.isFull()) {
            throw new ActivityFullException("The activity is full.");
        }

        if (activity.getParticipants().contains(member)) {
            throw new DuplicateBookingException("This member is already booked for this activity.");
        }

        activity.bookMember(member);

        Booking booking = new Booking(bookingId, member, activity, dateTime);
        bookingRepository.add(booking);

        return booking;
    }

    // Method to cancel a booking by ID
    public boolean cancelBooking(int bookingId) {
        for (Booking booking : bookingRepository.getAll()) {
            if (booking.getBookingId() == bookingId) {
                booking.getActivity().cancelBooking(booking.getMember());
                bookingRepository.remove(booking);
                return true;
            }
        }

        return false;
    }

    // Methods to find members and activities by ID
    public Member findMemberById(int id) {
        for (Member member : memberRepository.getAll()) {
            if (member.getId() == id) {
                return member;
            }
        }

        return null;
    }

    // Method to find an activity by ID
    public Activity findActivityById(int id) {
        for (Activity activity : activityRepository.getAll()) {
            if (activity.getId() == id) {
                return activity;
            }
        }

        return null;
    }

    // Methods to display all members, activities, and bookings
    public void showAllMembers() {
    if (memberRepository.getAll().isEmpty()) {
        System.out.println("No members found.");
        return;
    }

    for (Member member : memberRepository.getAll()) {
        System.out.println(member);
    }
}

    public void showAllBookings() {
    if (bookingRepository.getAll().isEmpty()) {
        System.out.println("No bookings found.");
        return;
    }

    for (Booking booking : bookingRepository.getAll()) {
        System.out.println(booking);
    }
}

    public void showAllActivities() {
    if (activityRepository.getAll().isEmpty()) {
        System.out.println("No activities found.");
        return;
    }

    for (Activity activity : activityRepository.getAll()) {
        System.out.println(activity);
    }
}

    public ArrayList<Member> getMembers() {
        return memberRepository.getAll();
    }

    public ArrayList<Activity> getActivities() {
        return activityRepository.getAll();
    }

    public ArrayList<Booking> getBookings() {
        return bookingRepository.getAll();
    }

    // Sorts members alphabetically by name, ignoring uppercase/lowercase differences.
    public void sortMembersByName() {
    memberRepository.getAll().sort(
        (m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName())
    );
    }

    // Sorts activities from cheapest to most expensive.
    public void sortActivitiesByPrice() {
        activityRepository.getAll().sort(
            (a1, a2) -> Double.compare(a1.getPrice(), a2.getPrice())
        );
    }
    
    // Prints only activities that still have available spots.
    public void showAvailableActivities() {
        activityRepository.getAll()
                .stream()
                .filter(activity -> !activity.isFull())
                .forEach(System.out::println);
    }
}