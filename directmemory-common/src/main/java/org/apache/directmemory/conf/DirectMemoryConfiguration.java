package org.apache.directmemory.conf;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * This class encapsulates a configuration of a Cache Manager. It holds a configuration
 * for all caches.   
 * 
 * @author jara
 *
 */
public class DirectMemoryConfiguration implements Serializable
{
    public static final String DEFAULT_CACHE_NAME = "Default";
    
    private Map<String, CacheConfiguration> caches;
    
    public DirectMemoryConfiguration() {
        caches = Maps.newHashMap();
    }
    
    public void addCacheConfiguration(CacheConfiguration cacheConfiguration) {
        caches.put( cacheConfiguration.getName(), cacheConfiguration );
    }
    
    public CacheConfiguration getCacheConfiguration(String name) {
        return caches.get( name );
    }
    
    public Collection<CacheConfiguration> getCacheConfigurations()
    {
        return Collections.unmodifiableCollection( caches.values() );
    }

}
