package org.apache.directmemory.conf.factory;

import static org.junit.Assert.*;

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.DirectMemoryConfiguration;
import org.junit.Test;


public class YamlDirectMemoryConfigurationFactoryTest
{
    private static final String NON_DEFAULT_CONFIGURATION_FILENAME = "non-default-direct-memory-cfg.yaml";
    
    @Test
    public void testBasicScenarion()
    {
        YamlDirectMemoryConfigurationFactory yamlDirectMemoryConfigurationFactory = new YamlDirectMemoryConfigurationFactory();
        DirectMemoryConfiguration configuration = yamlDirectMemoryConfigurationFactory.buildConfiguration();
        
        assertEquals(  1, configuration.getCacheConfigurations().size() );
        CacheConfiguration cacheConfiguration = configuration.getCacheConfigurations().iterator().next();
        
        assertEquals( "Default", cacheConfiguration.getName() );
        assertEquals( 1, cacheConfiguration.getNumberOfBuffers() );
        assertEquals( 1, cacheConfiguration.getRamMegaBytes() );
        assertEquals( 100000, cacheConfiguration.getInitialCapacity() );
        assertEquals( 4, cacheConfiguration.getConcurrencyLevel() );
        assertEquals( 10, cacheConfiguration.getDisposalTime() );
    }
    
    @Test
    public void testNonDefaultConfigurationFile() {
        DirectMemoryConfiguration configuration = new YamlDirectMemoryConfigurationFactory(NON_DEFAULT_CONFIGURATION_FILENAME).buildConfiguration();
        assertEquals( 2,  configuration.getCacheConfigurations().size() );
    }
    
    @Test
    public void testNonExistingConfigurationFile() {
        DirectMemoryConfiguration configuration = new YamlDirectMemoryConfigurationFactory("shouldNotExist").buildConfiguration();
        assertNull( configuration );
    }

}
