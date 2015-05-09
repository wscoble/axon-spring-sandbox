package sscoble.marshaller

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.stereotype.Component
import sscoble.domain.todo.command.CreateToDoItemCommand

@Component
class CreateToDoItemCommandMarshaller implements HttpMessageConverter<CreateToDoItemCommand> {
    @Autowired
    JsonSlurper jsonSlurper

    @Override
    boolean canRead(Class<?> clazz, MediaType mediaType) {
        clazz == CreateToDoItemCommand.class && mediaType == MediaType.APPLICATION_JSON
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
    CreateToDoItemCommand read(Class<? extends CreateToDoItemCommand> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        def result = jsonSlurper.parse((InputStream) inputMessage.body)

        new CreateToDoItemCommand(result.todoId, result.description)
    }

    @Override
    void write(CreateToDoItemCommand createToDoItemCommand, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
