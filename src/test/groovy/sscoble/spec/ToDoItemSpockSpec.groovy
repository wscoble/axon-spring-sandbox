package sscoble.spec
import org.axonframework.domain.EventMessage
import org.axonframework.eventhandling.EventListener
import sscoble.domain.todo.aggregate.ToDoItem
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.domain.todo.command.MarkCompleteCommand
import sscoble.domain.todo.event.ToDoItemCompletedEvent
import sscoble.domain.todo.event.ToDoItemCreatedEvent
import sscoble.helpers.GetRandom

/**
 * Created by sscoble on 5/7/15.
 */
class ToDoItemSpockSpec extends AxonSpec implements GetRandom {
    def sut = ToDoItem.class
    def listener = Mock(EventListener)

    def setup() {
        boot(sut)
        eventBus.subscribe(listener)
    }

    void "test create todo item command fires todo item created event with matching properties"() {
        given:
        def command = new CreateToDoItemCommand(getRandomTodoId(), getRandomDescription())

        when:
        commandGateway.sendAndWait(command)

        then:
        1 * listener.handle({EventMessage e ->
            e.payloadType == ToDoItemCreatedEvent.class &&
                    ((ToDoItemCreatedEvent) e.payload).todoId == command.todoId &&
                    ((ToDoItemCreatedEvent) e.payload).description == command.description
        })
    }

    void "test complete todo item command fires todo item completed event with matching properties"() {
        given:
        def todoId = getRandomTodoId()
        def createTodoItemCommand = new CreateToDoItemCommand(todoId, getRandomDescription())
        commandGateway.sendAndWait(createTodoItemCommand)

        and:
        def command = new MarkCompleteCommand(todoId)

        when:
        commandGateway.sendAndWait(command)

        then:
        1 * listener.handle({EventMessage e ->
            e.payloadType == ToDoItemCompletedEvent.class && ((ToDoItemCompletedEvent) e.payload).todoId == command.todoId
        })
    }

    void "test complete todo item command throws an exception if there was no create command fired first (no root aggregate found)"() {
        given:
        def todoId = getRandomTodoId()

        and:
        def command = new MarkCompleteCommand(todoId)

        when:
        commandGateway.sendAndWait(command)

        then:
        0 * listener.handle(_)
        thrown(Exception)
    }
}
