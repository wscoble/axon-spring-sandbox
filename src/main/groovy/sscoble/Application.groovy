package sscoble

import groovy.json.JsonSlurper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Bean
    JsonSlurper jsonSlurper() {
        new JsonSlurper()
    }
}
