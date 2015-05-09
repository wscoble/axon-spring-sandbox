package sscoble.domain.todo.event

/**
 * Created by sscoble on 5/7/15.
 */
class ToDoItemCreatedEvent {
    final String todoId
    final String description

    ToDoItemCreatedEvent(String todoId, String description) {
        this.todoId = todoId
        this.description = description
    }
}
