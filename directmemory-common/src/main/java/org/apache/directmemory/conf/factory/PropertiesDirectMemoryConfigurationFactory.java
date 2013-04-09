package org.apache.directmemory.conf.factory;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.ConfigurationException;
import org.apache.directmemory.conf.DirectMemoryConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @since 0.2
 */
public class PropertiesDirectMemoryConfigurationFactory
    implements DirectMemoryConfigurationFactory
{
    private static final String CACHE_CONFIGURATION_PREFIX = "cache.";

    private final static String DEFAULT_FILENAME = "direct-memory-cfg.properties";

    private String filename;

    public PropertiesDirectMemoryConfigurationFactory()
    {
        this( DEFAULT_FILENAME );
    }

    public PropertiesDirectMemoryConfigurationFactory( String filename )
    {
        this.filename = filename;
    }

    @Override
    public DirectMemoryConfiguration build()
    {
        InputStream configurationResource = this.getClass().getClassLoader().getResourceAsStream( filename );
        if ( configurationResource == null )
        {
            return null;
        }

        Properties properties = loadProperties( configurationResource );
        DirectMemoryConfiguration configuration = new DirectMemoryConfiguration();
        for ( Map.Entry<Object, Object> entry : properties.entrySet() )
        {
            processEntry( entry, configuration );

        }
        return configuration;
    }

    private Properties loadProperties( InputStream configurationResource )
    {
        Properties properties = new Properties();
        try
        {
            properties.load( configurationResource );

        }
        catch ( IOException e )
        {
            throw new ConfigurationException( "Error while reading from configuration property", e );
        }
        return properties;
    }

    private void processEntry( Map.Entry<Object, Object> entry, DirectMemoryConfiguration configuration )
    {
        String key = (String) entry.getKey();
        String value = (String) entry.getValue();

        if ( isCacheConfigurationKey( key ) )
        {
            processCacheConfigurationProperty( key, value, configuration );
        } else {
            throw new ConfigurationException( "Unknown configuration property: "+key );
        }
    }

    private void processCacheConfigurationProperty( String key, String value, DirectMemoryConfiguration configuration )
    {
        String cacheName = getCacheNameFromKey( key );
        CacheConfiguration cache = getOrCreateCache( cacheName, configuration );
        String configProperty = getCachePropertyFromKey( key );

        if ( "name".equals( configProperty ) )
        {
            cache.setName( value );
        }
        else if ( "numberOfBuffers".equals( configProperty ) )
        {
            cache.setNumberOfBuffers( Integer.parseInt( value ) );
        }
        else if ( "initialCapacity".equals( configProperty ) )
        {
            cache.setInitialCapacity( Integer.parseInt( value ) );
        }
        else if ( "ramMegaBytes".equals( configProperty ) )
        {
            cache.setRamMegaBytes( Integer.parseInt( value ) );
        }
        else if ( "concurrencyLevel".equals( configProperty ) )
        {
            cache.setConcurrencyLevel( Integer.parseInt( value ) );
        }
        else if ( "disposalTime".equals( configProperty ) )
        {
            cache.setDisposalTime( Long.parseLong( value ) );
        }
        else
        {
            throw new ConfigurationException( "Invalid configuration property: " + key );
        }
    }

    private String getCachePropertyFromKey( String key )
    {
        return key.substring( key.indexOf( '.', CACHE_CONFIGURATION_PREFIX.length() + 1 ) + 1 );
    }

    private boolean isCacheConfigurationKey( String key )
    {
        return key.startsWith( CACHE_CONFIGURATION_PREFIX );
    }

    private String getCacheNameFromKey( String key )
    {
        String cacheWithProperties = key.substring( CACHE_CONFIGURATION_PREFIX.length() );
        return cacheWithProperties.substring( 0, cacheWithProperties.indexOf( '.' ));
    }

    private CacheConfiguration getOrCreateCache( String name, DirectMemoryConfiguration configuration )
    {
        CacheConfiguration cache = configuration.getCacheConfiguration( name );
        if ( cache == null )
        {
            cache = new CacheConfiguration();
            cache.setName( name );
            configuration.addCacheConfiguration( cache );
        }
        return cache;
    }

}
