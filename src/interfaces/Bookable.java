package interfaces;

import model.Member;

// Interface for bookable activities
public interface Bookable {
    boolean bookMember(Member member);
    boolean cancelBooking(Member member);
}