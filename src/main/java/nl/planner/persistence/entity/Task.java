package nl.planner.persistence.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import nl.planner.persistence.enums.TaskType;
import org.springframework.context.annotation.Profile;

import java.util.Date;
import java.util.UUID;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Task {

    @Parent
    private Key<Location> locationKeyKey;

    @Id
    private Long id;
    private TaskType type;
    private String message;
    private Date date;

    private Task(TaskType type, String message, Date date){
        this.type = type;
        this.message = message;
        this.date = date;
    }
}
