package org.apache.directmemory.conf.factory;

import java.util.List;

import org.apache.directmemory.conf.DirectMemoryConfiguration;

import com.google.common.collect.Lists;

public class DelegatingDirectMemoryConfigurationFactory
    implements DirectMemoryConfigurationFactory
{
    private List<? extends DirectMemoryConfigurationFactory> factories;
    
    public DelegatingDirectMemoryConfigurationFactory(List<? extends DirectMemoryConfigurationFactory> factories) {
        this.factories = factories;
    }
    
    public DelegatingDirectMemoryConfigurationFactory() {
        this.factories = Lists.newArrayList(
                                       new DefaultDirectMemoryConfigurationFactory()
                                     );
    }

    @Override
    public DirectMemoryConfiguration buildConfiguration()
    {
        for (DirectMemoryConfigurationFactory configurationFactory : factories) {
            DirectMemoryConfiguration configuration = configurationFactory.buildConfiguration();
            if (configuration != null) {
                return configuration;
            }
        }
        throw new IllegalStateException( "No suitable DirectMemoryConfiguration found" );
    }

}
