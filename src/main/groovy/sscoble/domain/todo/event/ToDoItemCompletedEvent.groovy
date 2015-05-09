package sscoble.domain.todo.event

/**
 * Created by sscoble on 5/7/15.
 */
class ToDoItemCompletedEvent {
    final String todoId

    ToDoItemCompletedEvent(String todoId) {
        this.todoId = todoId
    }
}
