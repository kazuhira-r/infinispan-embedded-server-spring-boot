package org.littlewings.infinispan.embedded.server.memcached;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.server.memcached.MemcachedServer;
import org.infinispan.server.memcached.configuration.MemcachedServerConfiguration;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerInitializerConfigurationSupport;

public class InfinispanEmbeddedMemcachedServerInitializerConfiguration extends InfinispanEmbeddedServerInitializerConfigurationSupport<MemcachedServerConfiguration, MemcachedServer> {
    public InfinispanEmbeddedMemcachedServerInitializerConfiguration(MemcachedServerConfiguration memcachedServerConfiguration,
                                                                     EmbeddedCacheManager embeddedCacheManager) {
        super("Memcached", memcachedServerConfiguration, embeddedCacheManager);
    }

    @Override
    protected MemcachedServer newProtocolServer() {
        return new MemcachedServer();
    }
}
