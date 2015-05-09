package sscoble
import org.apache.camel.spring.javaconfig.CamelConfiguration
import org.springframework.boot.actuate.endpoint.MetricsEndpoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import sscoble.camel.MetricsGetter

@Configuration
@ComponentScan("sscoble.camel")
class MyCamelConfiguration extends CamelConfiguration {
    /**
     * Used to get around ambiguous method exception in Camel when trying to access MetricsEndpoint.invoke()
     * @param metricsEndpoint
     * @return
     */
    @Bean
    MetricsGetter metricsGetter(final MetricsEndpoint metricsEndpoint) {
        new MetricsGetter() {
            @Override
            Map getMetrics() {
                metricsEndpoint.invoke()
            }
        }
    }
}
