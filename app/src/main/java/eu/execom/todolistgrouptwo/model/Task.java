package eu.execom.todolistgrouptwo.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Model representing a task.
 */
public class Task {


    private int id;

    private String title;


    private String description;

//    @DatabaseField(columnName = "user", canBeNull = false, foreign = true)
//    private User user;

    private boolean finished;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(int id, String title, String description, boolean finished) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.finished = finished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;

    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
