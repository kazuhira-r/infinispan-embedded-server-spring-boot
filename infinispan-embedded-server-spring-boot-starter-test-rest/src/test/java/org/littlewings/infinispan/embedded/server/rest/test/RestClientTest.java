package org.littlewings.infinispan.embedded.server.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestClientTest {
    @Test
    public void simpleRestClient() {
        Client client = ClientBuilder.newClient();

        Response putResponse = client
                .target("http://localhost:8080/rest/namedCache/key")
                .request()
                .put(Entity.text("value"));
        assertThat(putResponse.getStatus())
                .isEqualTo(Response.Status.OK.getStatusCode());
        putResponse.close();

        String value = client
                .target("http://localhost:8080/rest/namedCache/key")
                .request()
                .get(String.class);

        assertThat(value)
                .isEqualTo("value");

        client.close();
    }
}
