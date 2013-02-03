package org.apache.directmemory.conf.factory;

import static org.junit.Assert.*;

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.DirectMemoryConfiguration;
import org.junit.Test;

public class PropertiesDirectMemoryConfigurationFactoryTest
{

    @Test
    public void testBasicScenarion()
    {
        DirectMemoryConfiguration configuration = new PropertiesDirectMemoryConfigurationFactory().buildConfiguration();
        
        CacheConfiguration cacheConfiguration = configuration.getCacheConfiguration( DirectMemoryConfiguration.DEFAULT_CACHE_NAME );
        
        assertNotNull( "Cache Configuration has not been propertly initialized", cacheConfiguration );
        assertEquals( DirectMemoryConfiguration.DEFAULT_CACHE_NAME, cacheConfiguration.getName() );
        assertEquals( 4, cacheConfiguration.getConcurrencyLevel() );
        assertEquals( 10, cacheConfiguration.getDisposalTime() );
        assertEquals( 100000, cacheConfiguration.getInitialCapacity() );
        assertEquals( 1, cacheConfiguration.getNumberOfBuffers() );
        assertEquals( 1, cacheConfiguration.getRamMegaBytes() );
    }

}
