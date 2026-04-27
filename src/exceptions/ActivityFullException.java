package exceptions;

// Custom exception for when an activity is full
public class ActivityFullException extends Exception {
    public ActivityFullException(String message) {
        super(message);
    }
}