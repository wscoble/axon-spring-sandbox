package sscoble.spec
import groovy.json.JsonSlurper
import org.springframework.http.HttpInputMessage
import org.springframework.http.MediaType
import spock.lang.Specification
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.marshaller.CreateToDoItemCommandMarshaller

class CreateToDoItemCommandMarshallerSpec extends Specification {
    def sut = new CreateToDoItemCommandMarshaller()
    def jsonSlurper = Mock(JsonSlurper)
    def inputMessage = Mock(HttpInputMessage)

    def setup() {
        sut.jsonSlurper = jsonSlurper
    }

    void "accepts application/json and CreateToDoItemCommand"() {
        expect:
        sut.supportedMediaTypes.contains(MediaType.APPLICATION_JSON)
        sut.canRead(CreateToDoItemCommand.class, MediaType.APPLICATION_JSON)
    }

    void "reading json string for CreateToDoItemCommand returns a correct CreateToDoItemCommand"() {
        when:
        def output = sut.read(CreateToDoItemCommand.class, inputMessage)

        then:
        "todo1" == output.todoId
        "todo description" == output.description

        and:
        1 * jsonSlurper.parse(_) >> [todoId: 'todo1', description: 'todo description']
    }
}
