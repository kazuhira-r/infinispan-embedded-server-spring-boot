package org.littlewings.infinispan.embedded.server.hotrod.test;

import org.littlewings.infinispan.embedded.server.hotrod.EnableInfinispanEmbeddedHotRodServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableInfinispanEmbeddedHotRodServer
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
