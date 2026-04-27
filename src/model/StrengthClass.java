package model;

public class StrengthClass extends Activity {
    private String muscleGroup;

    public StrengthClass(int id, String name, int maxParticipants, double price, String muscleGroup) {
        super(id, name, maxParticipants, price);
        this.muscleGroup = muscleGroup;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    @Override
    public String getDescription() {
        return "Strength Class: " + getName() +
                ", Muscle Group: " + muscleGroup;
    }

    @Override
    public String toString() {
        return "StrengthClass{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", maxParticipants=" + getMaxParticipants() +
                ", price=" + getPrice() +
                ", muscleGroup='" + muscleGroup + '\'' +
                '}';
    }
}