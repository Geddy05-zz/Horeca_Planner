package nl.planner.persistence.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geddy on 20-4-2017.
 */
public enum Experience {
    New(1),
    Junior(10),
    Medior(15),
    Senior(20);

    private static final Map<Integer, Experience> typesByValue = new HashMap<Integer, Experience>();
    private int value;

    Experience(int value) {
        this.value = value;
    }

    static{//hashmap sucks a bit, esp if you have some collisions so you might need to initialize the hashmap depending on the values count and w/ some arbitrary load factor
        for(Experience channel: values())  typesByValue.put(channel.value, channel);
    }

    public int getValue(){
        return value;
    }

    public static Experience forValue(int value) {
        return typesByValue.get(value);
    }
}
