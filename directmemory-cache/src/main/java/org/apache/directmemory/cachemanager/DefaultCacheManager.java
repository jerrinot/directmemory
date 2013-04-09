package org.apache.directmemory.cachemanager;

import java.util.Collections;
import java.util.Map;

import org.apache.directmemory.CacheManager;
import org.apache.directmemory.DirectMemory;
import org.apache.directmemory.cache.CacheService;
import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.ConfigurationException;
import org.apache.directmemory.conf.DirectMemoryConfiguration;
import org.apache.directmemory.conf.factory.DelegatingDirectMemoryConfigurationFactory;

import com.google.common.collect.Maps;

/**
 * 
 * 
 * @author jara
 *
 */
public class DefaultCacheManager
    implements CacheManager
{
    private static final String DEFAULT_NAME = "Default Cache Manager";
    
    private String name;
    private Map<String, CacheService<?, ?>> caches;
    
    
    public DefaultCacheManager() {
        this(null, DEFAULT_NAME);
    }
    
    public DefaultCacheManager(String name) {
        this(null, name);
    }

    public DefaultCacheManager(DirectMemoryConfiguration directMemoryConfiguration) {
        this(directMemoryConfiguration, DEFAULT_NAME);
    }
    
    public DefaultCacheManager(DirectMemoryConfiguration directMemoryConfiguration, String name) {
        this.caches = Maps.newHashMap();
        this.name = name;
        initCaches(directMemoryConfiguration);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public <K, V> CacheService<K, V> getServiceCache( String cacheServiceName )
    {
        @SuppressWarnings("unchecked")
        final CacheService<K, V> cache =  (CacheService<K, V>) caches.get( cacheServiceName );
        return cache;
    }
    
    @Override
    public <K, V> CacheService<K, V> getServiceCache()
    {
        @SuppressWarnings( "unchecked" )
        final CacheService<K, V> cache =  (CacheService<K, V>) caches.get( DirectMemoryConfiguration.DEFAULT_CACHE_NAME );
        return cache;
    }
    
    @Override
    public Iterable<CacheService<?, ?>> getCaches()
    {
        return Collections.unmodifiableCollection( caches.values() );
    }
    
    private void initCaches( DirectMemoryConfiguration directMemoryConfiguration )
    {
        if (directMemoryConfiguration == null) {
            directMemoryConfiguration = loadDirectMemoryConfiguration();
        }
        if (directMemoryConfiguration.getCacheConfiguration( DirectMemoryConfiguration.DEFAULT_CACHE_NAME ) == null) {
            throw new ConfigurationException("Configuration has to contains default cache configuration.");
        }
        for (CacheConfiguration cacheConfiguration : directMemoryConfiguration.getCacheConfigurations()) {
            @SuppressWarnings( "rawtypes" )
            CacheService<?, ?> cacheService = new DirectMemory( cacheConfiguration ).newCacheService();
            caches.put( cacheConfiguration.getName(), cacheService );
        }
    }

    private DirectMemoryConfiguration loadDirectMemoryConfiguration()
    {
        return new DelegatingDirectMemoryConfigurationFactory().build();
    }

}
