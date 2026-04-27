package model;

public class CardioClass extends Activity {
    private String intensityLevel;

    public CardioClass(int id, String name, int maxParticipants, double price, String intensityLevel) {
        super(id, name, maxParticipants, price);
        this.intensityLevel = intensityLevel;
    }

    public String getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(String intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    @Override
    public String getDescription() {
        return "Cardio Class: " + getName() +
                ", Intensity Level: " + intensityLevel;
    }

    @Override
    public String toString() {
        return "CardioClass{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", maxParticipants=" + getMaxParticipants() +
                ", price=" + getPrice() +
                ", intensityLevel='" + intensityLevel + '\'' +
                '}';
    }
}