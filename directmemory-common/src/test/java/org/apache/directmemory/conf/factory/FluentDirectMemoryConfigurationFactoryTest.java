package org.apache.directmemory.conf.factory;

import static org.junit.Assert.*;

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.DirectMemoryConfiguration;
import org.junit.Test;

public class FluentDirectMemoryConfigurationFactoryTest {

	@Test
	public void testBasicScenarion() {
		DirectMemoryConfiguration configuration = new FluentDirectMemoryConfigurationFactory()
			.newCache()
				.concurrencyLevel(4)
				.disposalTime(10)
				.initialCapacity(5000)
				.numberOfBuffers(5)
				.ramMegaBytes(100)
			.build();
			
		
		assertNotNull(configuration);
		assertEquals(1, configuration.getCacheConfigurations().size());
		
		CacheConfiguration cacheConfiguration = configuration.getCacheConfiguration(DirectMemoryConfiguration.DEFAULT_CACHE_NAME);
		assertNotNull(cacheConfiguration);
		assertEquals(4, cacheConfiguration.getConcurrencyLevel());
		assertEquals(10, cacheConfiguration.getDisposalTime());
		assertEquals(5000, cacheConfiguration.getInitialCapacity());
		assertEquals(5, cacheConfiguration.getNumberOfBuffers());
		assertEquals(100, cacheConfiguration.getRamMegaBytes());
	}
	
	@Test
	public void testTwoCachesScenarion() {
		DirectMemoryConfiguration configuration = new FluentDirectMemoryConfigurationFactory()
			.newCache()
				.concurrencyLevel(4)
				.disposalTime(10)
				.initialCapacity(5000)
				.numberOfBuffers(5)
				.ramMegaBytes(100)
			.newCache("NonDefaultCache")
				.concurrencyLevel(2)
				.numberOfBuffers(10)
			.build();
			
		
		assertNotNull(configuration);
		assertEquals(2, configuration.getCacheConfigurations().size());
		
		CacheConfiguration cacheConfiguration = configuration.getCacheConfiguration(DirectMemoryConfiguration.DEFAULT_CACHE_NAME);
		assertNotNull(cacheConfiguration);
		assertEquals(4, cacheConfiguration.getConcurrencyLevel());
		assertEquals(10, cacheConfiguration.getDisposalTime());
		assertEquals(5000, cacheConfiguration.getInitialCapacity());
		assertEquals(5, cacheConfiguration.getNumberOfBuffers());
		assertEquals(100, cacheConfiguration.getRamMegaBytes());
	}


}
