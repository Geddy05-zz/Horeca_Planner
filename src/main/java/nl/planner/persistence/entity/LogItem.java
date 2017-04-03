package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * Created by Geddy on 28-3-2017.
 */

@Entity
public class LogItem {
    @Parent
    private Key<Location> locationKeyKey;

    @Id
    private Long id;
}
