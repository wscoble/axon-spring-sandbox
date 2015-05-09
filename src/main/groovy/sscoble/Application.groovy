package sscoble

import groovy.json.JsonSlurper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("sscoble")
class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    /**
     * Used with marshallers
     * @return
     */
    @Bean
    JsonSlurper jsonSlurper() {
        new JsonSlurper()
    }
}
