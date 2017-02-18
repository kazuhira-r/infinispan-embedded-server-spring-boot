package org.littlewings.infinispan.embedded.server.hotrod;

import org.infinispan.server.hotrod.HotRodServer;
import org.infinispan.server.hotrod.configuration.HotRodServerConfiguration;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InfinispanEmbeddedHotRodServerInitializerConfiguration.class)
@ConditionalOnClass(HotRodServer.class)
@EnableConfigurationProperties(InfinispanEmbeddedHotRodServerProperties.class)
public class InfinispanEmbeddedHotRodServerConfiguration implements InfinispanEmbeddedServerConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public HotRodServerConfiguration hotRodServerConfiguration(InfinispanEmbeddedHotRodServerProperties infinispanEmbeddedHotRodServerProperties) {
        return infinispanEmbeddedHotRodServerProperties.buildConfiguration();
    }
}
