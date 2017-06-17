package org.littlewings.infinispan.embedded.server.rest;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.rest.RestServer;
import org.infinispan.rest.configuration.RestServerConfiguration;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerInitializerConfigurationSupport;

public class InfinispanEmbeddedRestServerInitializerConfiguration extends InfinispanEmbeddedServerInitializerConfigurationSupport<RestServerConfiguration, RestServer> {
    public InfinispanEmbeddedRestServerInitializerConfiguration(RestServerConfiguration restServerConfiguration,
                                                                EmbeddedCacheManager embeddedCacheManager) {
        super("REST", restServerConfiguration, embeddedCacheManager);
    }

    @Override
    protected RestServer newProtocolServer() {
        return new RestServer();
    }
}
