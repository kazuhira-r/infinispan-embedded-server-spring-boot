package org.littlewings.infinispan.embedded.server.hotrod;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.server.hotrod.HotRodServer;
import org.infinispan.server.hotrod.configuration.HotRodServerConfiguration;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerInitializerConfigurationSupport;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfinispanEmbeddedHotRodServerInitializerConfiguration extends InfinispanEmbeddedServerInitializerConfigurationSupport<HotRodServerConfiguration, HotRodServer> {
    public InfinispanEmbeddedHotRodServerInitializerConfiguration(HotRodServerConfiguration hotRodServerConfiguration,
                                                                  EmbeddedCacheManager embeddedCacheManager) {
        super("HotRod", hotRodServerConfiguration, embeddedCacheManager);
    }

    @Override
    protected HotRodServer newProtocolServer() {
        return new HotRodServer();
    }
}
