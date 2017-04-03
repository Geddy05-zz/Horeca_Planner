package nl.planner.persistence.enums;

public enum TaskType {
    NewPlanning(1),
    ChangedPlanning(10),
    NewRealisation(15);

    private int value;

    TaskType(int value) {
        this.value = value;
    }
}