package sscoble.spec

import org.axonframework.commandhandling.gateway.CommandGateway
import spock.lang.Specification
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.domain.todo.command.MarkCompleteCommand
import sscoble.endpoint.ToDoItemController
import sscoble.helpers.GetRandom

/**
 * Created by sscoble on 5/8/15.
 */
class ToDoItemControllerSpec extends Specification implements GetRandom {
    def commandGateway = Mock(CommandGateway)
    def sut = new ToDoItemController()

    def setup() {
        sut.commandGateway = commandGateway
    }

    void "sending a create todo item command to createToDoItem() sends the command to the command gateway"() {
        given:
        def command = new CreateToDoItemCommand(getRandomTodoId(), getRandomDescription())

        when:
        sut.createToDoItem(command)

        then:
        1 * commandGateway.send(command)
    }
    void "sending a complete todo item command to completeToDoItem() sends the command to the command gateway"() {
        given:
        def command = new MarkCompleteCommand(getRandomTodoId())

        when:
        sut.completeToDoItem(command)

        then:
        1 * commandGateway.send(command)
    }
}
