package org.littlewings.infinispan.embedded.server.core;

import java.util.Set;

import org.infinispan.server.core.configuration.ProtocolServerConfiguration;
import org.infinispan.server.core.configuration.ProtocolServerConfigurationBuilder;
import org.springframework.core.io.Resource;

public abstract class InfinispanEmbeddedServerPropertiesSupport<B extends ProtocolServerConfigurationBuilder<?, ?>, C extends ProtocolServerConfiguration> implements InfinispanEmbeddedServerProperties<B, C> {
    protected B configurationBuilder;

    private Resource config;

    private Set<String> cacheNames;

    // private SslConfigurationBuilder ssl;  // not support

    public InfinispanEmbeddedServerPropertiesSupport() {
        configurationBuilder = newConfigurationBuilder();
    }

    protected abstract B newConfigurationBuilder();

    @SuppressWarnings("unchecked")
    @Override
    public C buildConfiguration() {
        return (C) configurationBuilder.build();
    }

    @Override
    public Resource getConfig() {
        return config;
    }

    public void setConfig(Resource config) {
        this.config = config;
    }

    @Override
    public Set<String> getCacheNames() {
        return cacheNames;
    }

    public void setCacheNames(Set<String> cacheNames) {
        this.cacheNames = cacheNames;
    }

    public void setDefaultCacheName(String defaultCacheName) {
        configurationBuilder.defaultCacheName(defaultCacheName);
    }

    public void setName(String name) {
        configurationBuilder.name(name);
    }

    public void setHost(String host) {
        configurationBuilder.host(host);
    }

    public void setPort(int port) {
        configurationBuilder.port(port);
    }

    public void setIdleTimeout(int idleTimeout) {
        configurationBuilder.idleTimeout(idleTimeout);
    }

    public void setRecvBufSize(int recvBufSize) {
        configurationBuilder.recvBufSize(recvBufSize);
    }

    public void setSendBufSize(int sendBufSize) {
        configurationBuilder.sendBufSize(sendBufSize);
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        configurationBuilder.tcpNoDelay(tcpNoDelay);
    }

    public void setWorkerThreads(int workerThreads) {
        configurationBuilder.workerThreads(workerThreads);
    }

    public void setIgnoredCaches(Set<String> ignoredCaches) {
        configurationBuilder.ignoredCaches(ignoredCaches);
    }
}
