package org.apache.directmemory.conf.factory;

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.DirectMemoryConfiguration;

/**
 * This factory creates a {@link DirectMemoryConfiguration} using the defaults configuration.
 * 
 * @author jara
 *
 */
public class DefaultDirectMemoryConfigurationFactory implements DirectMemoryConfigurationFactory
{

    @Override
    public DirectMemoryConfiguration build()
    {
        DirectMemoryConfiguration directMemoryConfiguration = new DirectMemoryConfiguration();
        CacheConfiguration defaultCacheConfiguraiton = createDefaultCacheConfiguraiton();
        directMemoryConfiguration.addCacheConfiguration( defaultCacheConfiguraiton );
        
        return directMemoryConfiguration;
    }
    
    private CacheConfiguration createDefaultCacheConfiguraiton() {
        return new CacheConfiguration();
    }

}
