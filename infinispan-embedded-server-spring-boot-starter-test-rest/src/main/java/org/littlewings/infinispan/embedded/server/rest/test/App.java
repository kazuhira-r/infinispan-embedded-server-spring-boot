package org.littlewings.infinispan.embedded.server.rest.test;

import org.littlewings.infinispan.embedded.server.rest.EnableInfinispanEmbeddedRestServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableInfinispanEmbeddedRestServer
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
