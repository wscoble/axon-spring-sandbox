package sscoble

import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.DefaultCommandGateway
import org.axonframework.contextsupport.spring.AnnotationDriven
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventstore.EventStore
import org.axonframework.eventstore.supporting.VolatileEventStore
import org.axonframework.repository.Repository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sscoble.domain.todo.aggregate.ToDoItem

@Configuration
@AnnotationDriven
class AxonConfiguration {
    @Bean
    CommandGateway commandGateway(CommandBus commandBus) {
        new DefaultCommandGateway(commandBus)
    }

    @Bean
    CommandBus commandBus() {
        new SimpleCommandBus()
    }

    @Bean
    Repository<ToDoItem> toDoItemRepository(EventStore eventStore, EventBus eventBus) {
        def repository = new EventSourcingRepository<ToDoItem>(ToDoItem.class, eventStore)
        repository.eventBus = eventBus
        repository
    }

    @Bean
    AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor(EventBus eventBus) {
        def listener = new AnnotationEventListenerBeanPostProcessor()
        listener.eventBus = eventBus
        listener
    }

    @Bean
    AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor(CommandBus commandBus) {
        def handler = new AnnotationCommandHandlerBeanPostProcessor()
        handler.commandBus = commandBus
        handler
    }

    @Bean
    EventBus eventBus() {
        new SimpleEventBus()
    }

    @Bean
    EventStore eventStore() {
        new VolatileEventStore()
    }

    @Bean
    AggregateAnnotationCommandHandler aggregateAnnotationCommandHandler(Repository<ToDoItem> repository, CommandBus commandBus) {
        new AggregateAnnotationCommandHandler(ToDoItem.class, repository, commandBus)
    }
}
