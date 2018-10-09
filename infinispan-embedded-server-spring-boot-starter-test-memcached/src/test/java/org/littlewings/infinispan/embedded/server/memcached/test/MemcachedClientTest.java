package org.littlewings.infinispan.embedded.server.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
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
