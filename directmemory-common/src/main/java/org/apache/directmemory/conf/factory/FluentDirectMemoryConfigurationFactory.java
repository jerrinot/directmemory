package org.apache.directmemory.conf.factory;

import org.apache.directmemory.conf.DirectMemoryConfiguration;


/**
 * Implementation of {@link DirectMemoryConfigurationFactory} suitable for a programmatic configuration 
 * 
 * @author jara
 *
 */
public class FluentDirectMemoryConfigurationFactory
    implements DirectMemoryConfigurationFactory
{
	private DirectMemoryConfiguration configuration;
	
	public FluentDirectMemoryConfigurationFactory() {
		this.configuration = new DirectMemoryConfiguration();
	}
	
	public FluentCacheConfigurationFactory newCache(String name) {
		return new FluentCacheConfigurationFactory(configuration, name);
	}
	
	public FluentCacheConfigurationFactory newCache() {
		return new FluentCacheConfigurationFactory(configuration, DirectMemoryConfiguration.DEFAULT_CACHE_NAME);
	}

    @Override
    public DirectMemoryConfiguration build()
    {
        return configuration;
    }

}
