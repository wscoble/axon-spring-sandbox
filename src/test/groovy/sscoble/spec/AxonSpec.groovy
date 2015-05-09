package sscoble.spec
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.DefaultCommandGateway
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventstore.supporting.VolatileEventStore
import org.axonframework.repository.Repository
import spock.lang.Specification
/**
 * Created by sscoble on 5/7/15.
 */
class AxonSpec extends Specification {
    def commandBus = new SimpleCommandBus()
    def eventStore = new VolatileEventStore()
    def eventBus = new SimpleEventBus()
    CommandGateway commandGateway
    Repository repository

    def boot(sut) {
        commandGateway = new DefaultCommandGateway(commandBus)
        repository = new EventSourcingRepository(sut, eventStore)
        repository.setEventBus(eventBus)
        AggregateAnnotationCommandHandler.subscribe(sut, repository, commandBus)
    }
}
