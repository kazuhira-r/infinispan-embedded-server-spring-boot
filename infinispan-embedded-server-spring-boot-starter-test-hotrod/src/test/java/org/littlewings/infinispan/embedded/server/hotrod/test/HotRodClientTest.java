package org.littlewings.infinispan.embedded.server.hotrod.test;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotRodClientTest {
    @Test
    public void simpleHotRodClient() {
        RemoteCacheManager cacheManager =
                new RemoteCacheManager(
                        new ConfigurationBuilder()
                                .addServer()
                                .host("localhost")
                                .port(11222)
                                .build()
                );

        RemoteCache<String, String> cache = cacheManager.getCache("namedCache");

        cache.put("key", "value");
        assertThat(cache.get("key"))
                .isEqualTo("value");

        cache.stop();
        cacheManager.stop();
    }
}
