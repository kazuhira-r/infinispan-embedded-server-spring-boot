package org.littlewings.infinispan.embedded.server.mixed.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import net.spy.memcached.MemcachedClient;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MixedClientTest {
    @Test
    public void mixedClient() throws IOException {
        // Hot Rod
        RemoteCacheManager cacheManager =
                new RemoteCacheManager(
                        new ConfigurationBuilder()
                                .addServer()
                                .host("localhost")
                                .port(11222)
                                .build()
                );

        RemoteCache<String, String> cache = cacheManager.getCache("hotRodCache");

        cache.put("key", "value");
        assertThat(cache.get("key"))
                .isEqualTo("value");

        cache.stop();
        cacheManager.stop();

        // Memcached
        MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("localhost", 11211));

        memcachedClient.set("key", 5, "value");
        assertThat(memcachedClient.get("key"))
                .isEqualTo("value");

        // REST
        Client restClient = ClientBuilder.newClient();

        Response putResponse = restClient
                .target("http://localhost:8080/rest/restCache/key")
                .request()
                .put(Entity.text("value"));
        assertThat(putResponse.getStatus())
                .isEqualTo(Response.Status.OK.getStatusCode());
        putResponse.close();

        String value = restClient
                .target("http://localhost:8080/rest/restCache/key")
                .request()
                .get(String.class);

        assertThat(value)
                .isEqualTo("value");

        restClient.close();
    }
}
