package org.littlewings.infinispan.embedded.server.rest;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.rest.NettyRestServer;
import org.infinispan.rest.configuration.RestServerConfiguration;
import org.jboss.logging.Logger;
import org.springframework.context.SmartLifecycle;

public class InfinispanEmbeddedRestServerInitializerConfiguration implements SmartLifecycle {
    Logger logger = Logger.getLogger(InfinispanEmbeddedRestServerInitializerConfiguration.class);

    RestServerConfiguration restServerConfiguration;
    EmbeddedCacheManager embeddedCacheManager;

    NettyRestServer restServer;

    String protocolName;
    boolean running = false;

    public InfinispanEmbeddedRestServerInitializerConfiguration(RestServerConfiguration restServerConfiguration,
                                                                EmbeddedCacheManager embeddedCacheManager) {
        this.restServerConfiguration = restServerConfiguration;
        this.embeddedCacheManager = embeddedCacheManager;
        this.protocolName = "Rest";
    }


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

        restServer.stop();
        running = false;
        logger.infof("stopped, Infinispan Embedded %s Server", protocolName);

    }

    @Override
    public void start() {
        restServer = NettyRestServer.createServer(restServerConfiguration, embeddedCacheManager);

        logger.infof("starting, Infinispan Embedded %s Server", protocolName);
        logger.infof("%s Server configuration = %s", protocolName, restServerConfiguration);
        restServer.start();
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
