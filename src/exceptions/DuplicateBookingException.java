package exceptions;

// Custom exception for when a member tries to book an activity they are already booked for
public class DuplicateBookingException extends Exception {
    public DuplicateBookingException(String message) {
        super(message);
    }
}