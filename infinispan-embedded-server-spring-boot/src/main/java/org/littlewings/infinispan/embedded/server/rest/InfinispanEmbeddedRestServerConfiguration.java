package org.littlewings.infinispan.embedded.server.rest;

import org.infinispan.rest.RestServer;
import org.infinispan.rest.configuration.RestServerConfiguration;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InfinispanEmbeddedRestServerInitializerConfiguration.class)
@ConditionalOnClass(RestServer.class)
@EnableConfigurationProperties(InfinispanEmbeddedRestServerProperties.class)
public class InfinispanEmbeddedRestServerConfiguration implements InfinispanEmbeddedServerConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RestServerConfiguration restServerConfiguration(InfinispanEmbeddedRestServerProperties infinispanEmbeddedRestServerProperties) {
        return infinispanEmbeddedRestServerProperties.buildConfiguration();
    }
}
