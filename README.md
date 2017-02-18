# Infinispan Embedded Server Spring Boot

Infinispan Embedded Server for Spring Boot.

## [Infinispan](http://infinispan.org/)
Open Source In Memory Data Grid.

## [Spring Boot](https://projects.spring.io/spring-boot/)
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

### Supported Protocol
* Hot Rod
* Memcached
* Rest

### Unsupported Protocol
* WebSocket

## Usage
### Build & Install
```bash
$ git clone https://github.com/kazuhira-r/infinispan-embedded-server-spring-boot.git
$ cd infinispan-embedded-server-spring-boot
$ mvn install -DskipTests=true
```

### import bom
```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.littlewings</groupId>
                <artifactId>infinispan-embedded-server-spring-boot-dependencies</artifactId>
                <version>${infinispan.embedded.server.spring.boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

### as Embedded HotRod Server
add dependency.
```xml
        <dependency>
            <groupId>org.littlewings</groupId>
            <artifactId>infinispan-embedded-server-spring-boot-starter-hotrod</artifactId>
        </dependency>
```

add `@EnableInfinispanEmbeddedHotRodServer` annotation.
```java
import org.littlewings.infinispan.embedded.server.hotrod.EnableInfinispanEmbeddedHotRodServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableInfinispanEmbeddedHotRodServer
@SpringBootApplication
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
```

#### configuration
application.properties
```properties
infinispan.embedded.server.hotrod.default-cache-name=
infinispan.embedded.server.hotrod.port=
...
```

### as Memcached Server
add dependency.
```xml
    <dependencies>
        <dependency>
            <groupId>org.littlewings</groupId>
            <artifactId>infinispan-embedded-server-spring-boot-starter-memcached</artifactId>
        </dependency>
    </dependencies>
```

add `@EnableInfinispanEmbeddedMemcachedServer` annotation.
```java
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
```

#### configuration
application.properties
```properties
infinispan.embedded.server.memcached.default-cache-name=
infinispan.embedded.server.memcached.port=
...
```

### as Rest Server
add dependency.
```xml
        <dependency>
            <groupId>org.littlewings</groupId>
            <artifactId>infinispan-embedded-server-spring-boot-starter-rest</artifactId>
        </dependency>
```

add `@EnableInfinispanEmbeddedRestServer` annotation.
```java
import org.littlewings.infinispan.embedded.server.rest.EnableInfinispanEmbeddedRestServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableInfinispanEmbeddedRestServer
@SpringBootApplication
public class App {
    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
```

#### configuration
```properties
infinispan.embedded.server.rest.cache-names=
infinispan.embedded.server.rest.port=
...
```

### as HotRod/Memcached/Rest Mixed Server
add dependency.
```xml

```

add `@EnableInfinispanEmbeddedHotRodServer`, `@EnableInfinispanEmbeddedMemcachedServer`, `@EnableInfinispanEmbeddedRestServer` annotations.

and provide your EmbeddedCacheManager Bean.
```java
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

        Set<String> cacheNames = new HashSet<>();
        cacheNames.addAll(hotRodServerProperties.getCacheNames() != null ? hotRodServerProperties.getCacheNames() : Collections.emptySet());
        cacheNames.addAll(memcachedServerProperties.getCacheNames() != null ? memcachedServerProperties.getCacheNames() : Collections.emptySet());
        cacheNames.addAll(restServerProperties.getCacheNames() != null ? restServerProperties.getCacheNames() : Collections.emptySet());

        cacheNames.forEach(cacheName -> cacheManager.defineConfiguration(cacheName, new ConfigurationBuilder().build()));

        return cacheManager;
    }
}
```

#### configuration
see, HotRod, Memcached, Rest

### Reference
[Infinispan Spring Boot](https://github.com/infinispan/infinispan-spring-boot)

[Spring Boot](https://github.com/spring-projects/spring-boot)

[Spring Cloud Netflix](https://github.com/spring-cloud/spring-cloud-netflix)

[Doma Spring Boot](https://github.com/domaframework/doma-spring-boot)
