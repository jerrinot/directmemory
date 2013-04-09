package org.apache.directmemory.conf.factory;

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.DirectMemoryConfiguration;

public class FluentCacheConfigurationFactory extends FluentDirectMemoryConfigurationFactory {
	private CacheConfiguration cacheConfiguration;
	private DirectMemoryConfiguration directMemoryConfiguration;
	
	FluentCacheConfigurationFactory(DirectMemoryConfiguration configuration, String name) {
		this.cacheConfiguration = new CacheConfiguration();
		this.directMemoryConfiguration = configuration; 
		cacheConfiguration.setName(name);
		
		configuration.addCacheConfiguration(cacheConfiguration);
	}
	
	@Override
    public DirectMemoryConfiguration build()
    {
        return directMemoryConfiguration;
    }
	
	public FluentCacheConfigurationFactory numberOfBuffers(int numberOfBuffers) {
		cacheConfiguration.setNumberOfBuffers(numberOfBuffers);
		return this;
	}
	
	public FluentCacheConfigurationFactory initialCapacity(int initialCapacity) {
		cacheConfiguration.setInitialCapacity(initialCapacity);
		return this;
	}
	
	public FluentCacheConfigurationFactory ramMegaBytes(int ramMegaBytes) {
		cacheConfiguration.setRamMegaBytes(ramMegaBytes);
		return this;
	}
	
	public FluentCacheConfigurationFactory concurrencyLevel(int concurrencyLevel) {
		cacheConfiguration.setConcurrencyLevel(concurrencyLevel);
		return this;
	}
	
	public FluentCacheConfigurationFactory disposalTime(long disposalTime) {
		cacheConfiguration.setDisposalTime(disposalTime);
		return this;
	}
}
