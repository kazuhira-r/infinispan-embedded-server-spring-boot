package org.littlewings.infinispan.embedded.server.memcached;

import org.infinispan.server.memcached.MemcachedServer;
import org.infinispan.server.memcached.configuration.MemcachedServerConfiguration;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InfinispanEmbeddedMemcachedServerInitializerConfiguration.class)
@ConditionalOnClass(MemcachedServer.class)
@EnableConfigurationProperties(InfinispanEmbeddedMemcachedServerProperties.class)
public class InfinispanEmbeddedMemcachedServerConfiguration implements InfinispanEmbeddedServerConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public MemcachedServerConfiguration memcachedServerConfiguration(InfinispanEmbeddedMemcachedServerProperties infinispanEmbeddedMemcachedServerProperties) {
        return infinispanEmbeddedMemcachedServerProperties.buildConfiguration();
    }
}
