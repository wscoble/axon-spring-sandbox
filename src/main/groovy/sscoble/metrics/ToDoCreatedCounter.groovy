package sscoble.metrics

import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.stereotype.Service
import sscoble.domain.todo.event.ToDoItemCreatedEvent

@Service
class ToDoCreatedCounter {
    private final CounterService counterService

    @Autowired
    ToDoCreatedCounter(CounterService counterService) {
        this.counterService = counterService
    }

    @EventHandler
    def on(ToDoItemCreatedEvent event) {
        this.counterService.increment("event.ToDoItemCreated")
    }
}
