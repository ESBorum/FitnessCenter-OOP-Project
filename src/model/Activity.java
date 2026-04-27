package model;

import java.util.ArrayList;
import interfaces.Bookable;

public abstract class Activity implements Bookable {
    private int id;
    private String name;
    private int maxParticipants;
    private double price;
    private ArrayList<Member> participants;

    // Constructor
    public Activity(int id, String name, int maxParticipants, double price) {
        this.id = id;
        this.name = name;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.participants = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public double getPrice() {
        return price;
    }

    // Method to add a participant to the activity
    public ArrayList<Member> getParticipants() {
        return participants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableSpots() {
        return maxParticipants - participants.size();
    }

    public boolean isFull() {
        return participants.size() >= maxParticipants;
    }

    // Method to get basic info about the activity
    public String getBasicInfo() {
        return "Activity ID: " + id +
               ", Name: " + name +
               ", Max Participants: " + maxParticipants +
               ", Price: " + price +
               ", Available Spots: " + getAvailableSpots();
    }

    // Abstract method to get a detailed description of the activity
    public abstract String getDescription();

    // toString method for easy display of activity information
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxParticipants=" + maxParticipants +
                ", price=" + price +
                ", participants=" + participants.size() +
                '}';
    }

        @Override
    public boolean bookMember(Member member) {
        if (isFull()) {
            return false;
        }

        if (participants.contains(member)) {
            return false;
        }

        participants.add(member);
        return true;
    }

    @Override
    public boolean cancelBooking(Member member) {
        return participants.remove(member);
    }
}