package org.littlewings.infinispan.embedded.server.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public interface InfinispanEmbeddedServerConfiguration {
    @Bean(destroyMethod = "stop")
    @ConditionalOnMissingBean
    default EmbeddedCacheManager embeddedCacheManager(InfinispanEmbeddedServerProperties infinispanEmbeddedServerProperties,
                                                      ObjectProvider<ConfigurationBuilder> defaultConfigurationBuilder) throws IOException {
        if (infinispanEmbeddedServerProperties.getConfig() != null) {
            try (InputStream is = infinispanEmbeddedServerProperties.getConfig().getInputStream()) {
                return new DefaultCacheManager(is);
            }
        } else {
            ConfigurationBuilder configurationBuilder = defaultConfigurationBuilder.getIfAvailable();
            if (configurationBuilder == null) {
                configurationBuilder = new ConfigurationBuilder();
            }

            EmbeddedCacheManager embeddedCacheManager = new DefaultCacheManager(configurationBuilder.build());

            @SuppressWarnings("unchecked")
            Set<String> cacheNames = infinispanEmbeddedServerProperties.getCacheNames();
            if (cacheNames != null) {
                for (String cacheName : cacheNames) {
                    embeddedCacheManager.defineConfiguration(cacheName, configurationBuilder.build());
                }
            }

            return embeddedCacheManager;
        }
    }
}
