package sscoble.domain.todo.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

/**
 * Created by sscoble on 5/7/15.
 */
class MarkCompleteCommand {
    @TargetAggregateIdentifier
    final String todoId

    MarkCompleteCommand() {}

    MarkCompleteCommand(String todoId) {
        this.todoId = todoId
    }
}
