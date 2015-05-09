package sscoble.domain.todo.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

/**
 * Created by sscoble on 5/7/15.
 */
class CreateToDoItemCommand {
    @TargetAggregateIdentifier
    final String todoId
    final String description

    CreateToDoItemCommand() {}

    CreateToDoItemCommand(String todoId, String description) {
        this.todoId = todoId
        this.description = description
    }
}
