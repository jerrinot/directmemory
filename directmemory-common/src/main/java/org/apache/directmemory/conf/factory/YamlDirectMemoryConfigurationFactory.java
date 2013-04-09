package org.apache.directmemory.conf.factory;

import java.io.InputStream;

import org.apache.directmemory.conf.CacheConfiguration;
import org.apache.directmemory.conf.DirectMemoryConfiguration;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;

public class YamlDirectMemoryConfigurationFactory
    implements DirectMemoryConfigurationFactory
{
    private final static String DEFAULT_FILENAME = "direct-memory-cfg.yaml";
    
    private String filename;
    
    public YamlDirectMemoryConfigurationFactory() {
        this(DEFAULT_FILENAME);
    }
    
    public YamlDirectMemoryConfigurationFactory(String filename) {
        this.filename = filename;
    }

    @Override
    public DirectMemoryConfiguration build()
    {
        InputStream configurationResourceStream = this.getClass().getClassLoader().getResourceAsStream( filename );
        if (configurationResourceStream == null) {
            return null;
        }
        Yaml yaml = createYaml();
        return (DirectMemoryConfiguration) yaml.load( configurationResourceStream );
    }

    private Yaml createYaml()
    {
        Constructor constructor = new Constructor( DirectMemoryConfiguration.class );
        TypeDescription cacheConfigurationDescription = new TypeDescription(CacheConfiguration.class);
        cacheConfigurationDescription.putMapPropertyType( "caches", String.class, CacheConfiguration.class);
        constructor.addTypeDescription( cacheConfigurationDescription );
        Yaml yaml = new Yaml( constructor);
        yaml.setBeanAccess( BeanAccess.FIELD );
        return yaml;
    }

}
