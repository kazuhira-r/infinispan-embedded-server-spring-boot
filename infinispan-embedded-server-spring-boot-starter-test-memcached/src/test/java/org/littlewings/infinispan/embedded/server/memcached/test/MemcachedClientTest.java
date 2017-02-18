package org.littlewings.infinispan.embedded.server.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedClientTest {
    @Test
    public void simpleMemcachedClient() throws IOException {
        MemcachedClient client = new MemcachedClient(new InetSocketAddress("localhost", 11211));

        client.set("key", 5, "value");
        assertThat(client.get("key"))
                .isEqualTo("value");
    }
}
