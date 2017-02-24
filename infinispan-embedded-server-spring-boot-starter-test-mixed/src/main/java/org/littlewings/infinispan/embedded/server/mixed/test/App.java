package org.littlewings.infinispan.embedded.server.mixed.test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.littlewings.infinispan.embedded.server.hotrod.EnableInfinispanEmbeddedHotRodServer;
import org.littlewings.infinispan.embedded.server.hotrod.InfinispanEmbeddedHotRodServerProperties;
import org.littlewings.infinispan.embedded.server.memcached.EnableInfinispanEmbeddedMemcachedServer;
import org.littlewings.infinispan.embedded.server.memcached.InfinispanEmbeddedMemcachedServerProperties;
import org.littlewings.infinispan.embedded.server.rest.EnableInfinispanEmbeddedRestServer;
import org.littlewings.infinispan.embedded.server.rest.InfinispanEmbeddedRestServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableInfinispanEmbeddedHotRodServer
@EnableInfinispanEmbeddedMemcachedServer
@EnableInfinispanEmbeddedRestServer
@SpringBootApplication
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public EmbeddedCacheManager embeddedCacheManager(InfinispanEmbeddedHotRodServerProperties hotRodServerProperties,
                                                     InfinispanEmbeddedMemcachedServerProperties memcachedServerProperties,
                                                     InfinispanEmbeddedRestServerProperties restServerProperties) {
        EmbeddedCacheManager cacheManager = new DefaultCacheManager(new ConfigurationBuilder().build());

        Set<String> cacheNames =
                Arrays
                        .asList(hotRodServerProperties.getCacheNames(),
                                memcachedServerProperties.getCacheNames(),
                                restServerProperties.getCacheNames())
                        .stream()
                        .filter(c -> c != null)
                        .flatMap(c -> c.stream())
                        .collect(Collectors.toSet());

        cacheNames.forEach(cacheName -> cacheManager.defineConfiguration(cacheName, new ConfigurationBuilder().build()));

        return cacheManager;
    }
}
