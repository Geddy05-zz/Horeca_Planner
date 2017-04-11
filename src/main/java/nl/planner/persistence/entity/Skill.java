package nl.planner.persistence.entity;

/**
 * Created by Geddy on 11-4-2017.
 */
public enum Skill {
    Kitchen(3),
    Bar(2),
    Waiter(1);

    private int value;

    private Skill(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
