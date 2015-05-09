package sscoble.domain.todo.aggregate
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.eventhandling.annotation.EventHandler
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import org.slf4j.LoggerFactory
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.domain.todo.command.MarkCompleteCommand
import sscoble.domain.todo.event.ToDoItemCompletedEvent
import sscoble.domain.todo.event.ToDoItemCreatedEvent

class ToDoItem extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private String id

    private final static logger = LoggerFactory.getLogger(ToDoItem.class)

    ToDoItem() {}

    @CommandHandler
    ToDoItem(CreateToDoItemCommand command) {
        logger.info("CreateToDoItemCommand: ${command.todoId} :: ${command.description}")
        apply(new ToDoItemCreatedEvent(command.todoId, command.description))
    }

    @EventHandler
    def on(ToDoItemCreatedEvent event) {
        this.id = event.todoId
        logger.info("ToDoItemCreatedEvent: ${event.todoId} :: ${event.description}")
    }

    @CommandHandler
    def markCompleted(MarkCompleteCommand command) {
        logger.info("MarkCompleteCommand: ${command.todoId}")
        apply(new ToDoItemCompletedEvent(id))
    }

    @EventHandler
    def on(ToDoItemCompletedEvent event) {
        logger.info("ToDoItemCompletedEvent: ${event.todoId}")
    }
}
