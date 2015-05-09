package sscoble.endpoint
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import sscoble.domain.todo.command.CreateToDoItemCommand
import sscoble.domain.todo.command.MarkCompleteCommand

import java.util.concurrent.TimeUnit
/**
 * Created by sscoble on 5/8/15.
 */
@RestController
@RequestMapping("/todo")
class ToDoItemController {
    @Autowired
    CommandGateway commandGateway

    @Value('${todo.create.timeout}')
    Integer createTimeout

    @Value('${todo.complete.timeout}')
    Integer completeTimeout

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    def createToDoItem(@RequestBody CreateToDoItemCommand command) {
        commandGateway.sendAndWait(command, createTimeout, TimeUnit.MILLISECONDS)
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    def completeToDoItem(@RequestBody MarkCompleteCommand command) {
        commandGateway.sendAndWait(command, completeTimeout, TimeUnit.MILLISECONDS)
    }
}
