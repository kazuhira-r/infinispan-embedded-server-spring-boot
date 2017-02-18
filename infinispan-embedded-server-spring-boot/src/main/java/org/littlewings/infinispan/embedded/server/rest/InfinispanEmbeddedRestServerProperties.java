package org.littlewings.infinispan.embedded.server.rest;

import java.util.Arrays;
import java.util.HashSet;

import org.infinispan.rest.configuration.ExtendedHeaders;
import org.infinispan.rest.configuration.RestServerConfiguration;
import org.infinispan.rest.configuration.RestServerConfigurationBuilder;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerPropertiesSupport;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.littlewings.infinispan.embedded.server.rest.InfinispanEmbeddedRestServerProperties.INFINISPAN_EMBEDDED_REST_SERVER_PREFIX;

@ConfigurationProperties(prefix = INFINISPAN_EMBEDDED_REST_SERVER_PREFIX)
public class InfinispanEmbeddedRestServerProperties extends InfinispanEmbeddedServerPropertiesSupport<RestServerConfigurationBuilder, RestServerConfiguration> {
    public static final String INFINISPAN_EMBEDDED_REST_SERVER_PREFIX = "infinispan.embedded.server.rest";

    public InfinispanEmbeddedRestServerProperties() {
        setCacheNames(new HashSet<>(Arrays.asList("namedCache")));
    }

    @Override
    protected RestServerConfigurationBuilder newConfigurationBuilder() {
        return new RestServerConfigurationBuilder();
    }

    public void setStartTransport(boolean startTransport) {
        configurationBuilder.startTransport(startTransport);
    }

    public void setExtendedHeaders(ExtendedHeaders extendedHeaders) {
        configurationBuilder.extendedHeaders(extendedHeaders);
    }
}
