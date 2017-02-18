@Grab('org.infinispan:infinispan-remote:9.0.0.CR1')
import org.infinispan.client.hotrod.configuration.*
import org.infinispan.client.hotrod.*

def cacheManager = new RemoteCacheManager(
      new ConfigurationBuilder()
        .addServer()
        .host("localhost")
        .port(11222)
        .build()
)

def cache = cacheManager.getCache('namedCache')
cache.put("key1", "value1")
println(cache.get("key1"))

cache.stop()
cacheManager.stop()
