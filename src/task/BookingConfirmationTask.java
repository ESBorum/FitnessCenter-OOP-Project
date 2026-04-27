package task;

import model.Booking;

// Runnable task responsible for simulating a booking confirmation being sent asynchronously.
public class BookingConfirmationTask implements Runnable {
    private Booking booking;

    public BookingConfirmationTask(Booking booking) {
        this.booking = booking;
    }

    @Override
    public void run() {
        System.out.println("Sending booking confirmation for booking ID: " + booking.getBookingId());

        // Simulates the time it would take to send a confirmation email.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Confirmation task was interrupted.");
        }

        System.out.println("Confirmation sent to: " + booking.getMember().getEmail());
    }
}