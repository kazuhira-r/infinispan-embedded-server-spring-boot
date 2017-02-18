package org.littlewings.infinispan.embedded.server.core;

import java.util.Set;

import org.infinispan.server.core.configuration.ProtocolServerConfiguration;
import org.infinispan.server.core.configuration.ProtocolServerConfigurationBuilder;
import org.springframework.core.io.Resource;

public interface InfinispanEmbeddedServerProperties<B extends ProtocolServerConfigurationBuilder<?, ?>, C extends ProtocolServerConfiguration> {
    C buildConfiguration();

    Resource getConfig();

    Set<String> getCacheNames();
}
