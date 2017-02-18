package org.littlewings.infinispan.embedded.server.memcached.test;

import org.littlewings.infinispan.embedded.server.memcached.EnableInfinispanEmbeddedMemcachedServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableInfinispanEmbeddedMemcachedServer
@SpringBootApplication
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
