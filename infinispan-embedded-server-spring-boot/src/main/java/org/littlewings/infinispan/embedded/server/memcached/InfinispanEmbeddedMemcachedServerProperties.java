package org.littlewings.infinispan.embedded.server.memcached;

import org.infinispan.server.memcached.configuration.MemcachedServerConfiguration;
import org.infinispan.server.memcached.configuration.MemcachedServerConfigurationBuilder;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerPropertiesSupport;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.littlewings.infinispan.embedded.server.memcached.InfinispanEmbeddedMemcachedServerProperties.INFINISPAN_EMBEDDED_MEMCACHED_SERVER_PREFIX;

@ConfigurationProperties(prefix = INFINISPAN_EMBEDDED_MEMCACHED_SERVER_PREFIX)
public class InfinispanEmbeddedMemcachedServerProperties extends InfinispanEmbeddedServerPropertiesSupport<MemcachedServerConfigurationBuilder, MemcachedServerConfiguration> {
    public static final String INFINISPAN_EMBEDDED_MEMCACHED_SERVER_PREFIX = "infinispan.embedded.server.memcached";

    @Override
    protected MemcachedServerConfigurationBuilder newConfigurationBuilder() {
        return new MemcachedServerConfigurationBuilder();
    }
}
