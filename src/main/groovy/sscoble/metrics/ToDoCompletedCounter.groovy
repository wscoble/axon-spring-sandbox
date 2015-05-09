package sscoble.metrics
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.stereotype.Service
import sscoble.domain.todo.event.ToDoItemCompletedEvent

@Service
class ToDoCompletedCounter {
    private final CounterService counterService

    @Autowired
    ToDoCompletedCounter(CounterService counterService) {
        this.counterService = counterService
    }

    @EventHandler
    def on(ToDoItemCompletedEvent event) {
        this.counterService.increment("event.ToDoItemCompleted")
    }
}
