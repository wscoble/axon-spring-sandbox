package sscoble.camel
import org.apache.camel.spring.SpringRouteBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Camel route that reports metrics to reporting.endpoint every reporting.timer milliseconds
 */
@Component
class MetricsRoute extends SpringRouteBuilder {

    @Value('${reporting.timer}')
    String period

    @Value('${reporting.endpoint}')
    String reportingEndpoint

    @Override
    void configure() throws Exception {
        from("timer:metrics?period=${period}").beanRef("metricsGetter", "getMetrics").to(reportingEndpoint)
    }
}
