package sscoble.marshaller

import groovy.json.JsonSlurper
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.stereotype.Component
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.domain.todo.command.MarkCompleteCommand

@Component
class MarkCompleteCommandMarshaller implements HttpMessageConverter<MarkCompleteCommand> {

    @Override
    boolean canRead(Class<?> clazz, MediaType mediaType) {
        clazz == MarkCompleteCommand.class && mediaType == MediaType.APPLICATION_JSON
    }

    @Override
    boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false
    }

    @Override
    List<MediaType> getSupportedMediaTypes() {
        [MediaType.APPLICATION_JSON]
    }

    @Override
    MarkCompleteCommand read(Class<? extends MarkCompleteCommand> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        def slurper = new JsonSlurper()
        def result = slurper.parse(inputMessage.body)

        new CreateToDoItemCommand(result.todoId, result.description)
    }

    @Override
    void write(MarkCompleteCommand createToDoItemCommand, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
