package org.littlewings.infinispan.embedded.server.hotrod;

import org.infinispan.server.hotrod.configuration.HotRodServerConfiguration;
import org.infinispan.server.hotrod.configuration.HotRodServerConfigurationBuilder;
import org.littlewings.infinispan.embedded.server.core.InfinispanEmbeddedServerPropertiesSupport;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.littlewings.infinispan.embedded.server.hotrod.InfinispanEmbeddedHotRodServerProperties.INFINISPAN_EMBEDDED_HOTROD_SERVER_PREFIX;

@ConfigurationProperties(prefix = INFINISPAN_EMBEDDED_HOTROD_SERVER_PREFIX)
public class InfinispanEmbeddedHotRodServerProperties extends InfinispanEmbeddedServerPropertiesSupport<HotRodServerConfigurationBuilder, HotRodServerConfiguration> {
    public static final String INFINISPAN_EMBEDDED_HOTROD_SERVER_PREFIX = "infinispan.embedded.server.hotrod";

    // private AuthenticationConfigurationBuilder authentication = new AuthenticationConfigurationBuilder(this); // not supported

    @Override
    protected HotRodServerConfigurationBuilder newConfigurationBuilder() {
        HotRodServerConfigurationBuilder configurationBuilder = new HotRodServerConfigurationBuilder();
        configurationBuilder.defaultCacheName("namedCache");
        return configurationBuilder;
    }

    public void setProxyHost(String proxyHost) {
        configurationBuilder.proxyHost(proxyHost);
    }

    public void setProxyPort(int proxyPort) {
        configurationBuilder.proxyPort(proxyPort);
    }

    public void setTopologyLockTimeout(long topologyLockTimeout) {
        configurationBuilder.topologyLockTimeout(topologyLockTimeout);
    }

    public void setTopologyReplTimeout(long topologyReplTimeout) {
        configurationBuilder.topologyReplTimeout(topologyReplTimeout);
    }

    public void setTopologyAwaitInitialTransfer(boolean topologyAwaitInitialTransfer) {
        configurationBuilder.topologyAwaitInitialTransfer(topologyAwaitInitialTransfer);
    }

    public void setTopologyStateTransfer(boolean topologyStateTransfer) {
        configurationBuilder.topologyStateTransfer(topologyStateTransfer);
    }
}
