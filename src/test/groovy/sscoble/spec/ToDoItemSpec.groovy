package sscoble.spec

import org.axonframework.test.Fixtures
import spock.lang.Specification
import sscoble.domain.todo.aggregate.ToDoItem
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.domain.todo.command.MarkCompleteCommand
import sscoble.domain.todo.event.ToDoItemCompletedEvent
import sscoble.domain.todo.event.ToDoItemCreatedEvent
/**
 * Created by sscoble on 5/7/15.
 */
class ToDoItemSpec extends Specification {
    def sut = ToDoItem.class
    def fixture

    def setup() {
        fixture = Fixtures.newGivenWhenThenFixture(sut)
    }

    void "test create todo item"() {
        expect:
        fixture.given()
                .when(new CreateToDoItemCommand("todo1", "need to implement the aggregate"))
                .expectEvents(new ToDoItemCreatedEvent("todo1", "need to implement the aggregate"))
    }

    void "test mark todo item as completed"() {
        expect:
        fixture.given(new ToDoItemCreatedEvent("todo1", "need to implement the aggregate"))
                .when(new MarkCompleteCommand("todo1"))
                .expectEvents(new ToDoItemCompletedEvent("todo1"))
    }

    void "test mark todo item as completed without creating a todo item"() {
        expect:
        fixture.given()
                .when(new MarkCompleteCommand("todo1"))
                .expectEvents()
    }
}
