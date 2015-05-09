package sscoble.entity;

public class TodoItemEntity {
    public final String todoId;
    public final String description;
    public final boolean complete;

    public TodoItemEntity(String todoId, String description, boolean complete) {
        this.todoId = todoId;
        this.description = description;
        this.complete = complete;
    }
}
