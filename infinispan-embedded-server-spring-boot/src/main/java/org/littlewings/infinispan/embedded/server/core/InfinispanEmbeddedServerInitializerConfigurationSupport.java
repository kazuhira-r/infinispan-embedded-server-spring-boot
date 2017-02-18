package org.littlewings.infinispan.embedded.server.core;

import java.util.Optional;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.server.core.ProtocolServer;
import org.infinispan.server.core.configuration.ProtocolServerConfiguration;
import org.jboss.logging.Logger;
import org.springframework.context.SmartLifecycle;

public abstract class InfinispanEmbeddedServerInitializerConfigurationSupport<C extends ProtocolServerConfiguration, S extends ProtocolServer<C>>  implements SmartLifecycle {
    Logger logger = Logger.getLogger(
            Optional.of(getClass()).map(c -> {
                if (c.getName().contains("EnhancerBySpring")) {
                    return c.getSuperclass();
                } else {
                    return c;
                }
            }).get());

    ProtocolServerConfiguration protocolServerConfiguration;
    EmbeddedCacheManager embeddedCacheManager;

    String protocolName;

    S protocolServer;
    boolean running = false;

    public InfinispanEmbeddedServerInitializerConfigurationSupport(String protocolName,
                                                                   ProtocolServerConfiguration protocolServerConfiguration,
                                                                   EmbeddedCacheManager embeddedCacheManager) {
        this.protocolName = protocolName;
        this.protocolServerConfiguration = protocolServerConfiguration;
        this.embeddedCacheManager = embeddedCacheManager;
        protocolServer = newProtocolServer();
    }

    protected abstract S newProtocolServer();

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        if (!running) {
            return;
        }

        logger.infof("stopping, Infinispan Embedded %s Server", protocolName);

        if (callback != null) {
            callback.run();
        }

        protocolServer.stop();
        running = false;
        logger.infof("stopped, Infinispan Embedded %s Server", protocolName);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void start() {
        if (running) {
            return;
        }

        logger.infof("starting, Infinispan Embedded %s Server", protocolName);
        logger.infof("%s Server configuration = %s", protocolName, protocolServerConfiguration);
        protocolServer.start((C) protocolServerConfiguration, embeddedCacheManager);
        running = true;
        logger.infof("started, Infinispan Embedded %s Server", protocolName);
    }

    @Override
    public void stop() {
        stop(null);
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
