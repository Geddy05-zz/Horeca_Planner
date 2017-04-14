package nl.planner.persistence.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geddy on 11-4-2017.
 */
public enum Skill {
    Kitchen(3),
    Bar(2),
    Waiter(1);

    private int value;
    private static Map<Integer,Skill> map= new HashMap<>();

    private Skill(int value) {
        this.value = value;
    }

    static {
        for (Skill skill : Skill.values()) {
            map.put(skill.value, skill);
        }
    }

    public static Skill valueOf(int skillType) {
        return (Skill) map.get(skillType);
    }

    public int getValue(){
        return value;
    }
}
