package model;

public class Booking {
    private int bookingId;
    private Member member;
    private Activity activity;
    private String dateTime;

    public Booking(int bookingId, Member member, Activity activity, String dateTime) {
        this.bookingId = bookingId;
        this.member = member;
        this.activity = activity;
        this.dateTime = dateTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Member getMember() {
        return member;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", member=" + member +
                ", activity=" + activity +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}