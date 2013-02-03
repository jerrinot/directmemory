package org.apache.directmemory;


import org.apache.directmemory.cache.CacheService;


public interface CacheManager
{
    /**
     * Get the name of this cache manager
     *
     * @return the name of this cache manager
     */
    String getName();
    
    
    /**
     * Looks up a {@link Cache} given it's name.
     *
     * @param cacheName the name of the cache to look for
     * @return the Cache or null if it does exist
     */
    <K, V> CacheService<K, V> getServiceCache(String cacheServiceName);
    
    /**
     * Looks up a default {@link Cache}
     *
     * @return the Cache
     */
    <K, V> CacheService<K, V> getServiceCache();
    
    /**
     * Returns an Iterable over the caches managed by this CacheManager.
     * 
     * @return an Iterable over the managed Caches
     * @throws UnsupportedOperationException if an attempt it made to remove an element
     * 
     */
    Iterable<CacheService<?, ?>> getCaches();
}
