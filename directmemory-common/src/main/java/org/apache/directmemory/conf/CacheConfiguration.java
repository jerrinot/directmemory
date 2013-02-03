package org.apache.directmemory.conf;

import java.io.Serializable;

/**
 * Configuration of a single cache region. 
 * 
 * TODO: Should this be an immutable calss?
 * 
 * @author jara
 *
 */
public class CacheConfiguration
    implements Serializable
{
    private String name = DirectMemoryConfiguration.DEFAULT_CACHE_NAME;

    private int numberOfBuffers = 1;

    private int initialCapacity = 100000;

    private int ramMegaBytes = 1;

    private int concurrencyLevel = 4;

    private long disposalTime = 10L;
    
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public int getNumberOfBuffers()
    {
        return numberOfBuffers;
    }

    public void setNumberOfBuffers( int numberOfBuffers )
    {
        this.numberOfBuffers = numberOfBuffers;
    }

    public int getInitialCapacity()
    {
        return initialCapacity;
    }

    public void setInitialCapacity( int initialCapacity )
    {
        this.initialCapacity = initialCapacity;
    }

    public int getRamMegaBytes()
    {
        return ramMegaBytes;
    }

    public void setRamMegaBytes( int ramMegaBytes )
    {
        this.ramMegaBytes = ramMegaBytes;
    }

    public int getConcurrencyLevel()
    {
        return concurrencyLevel;
    }

    public void setConcurrencyLevel( int concurrencyLevel )
    {
        this.concurrencyLevel = concurrencyLevel;
    }

    public long getDisposalTime()
    {
        return disposalTime;
    }

    public void setDisposalTime( long disposalTime )
    {
        this.disposalTime = disposalTime;
    }

}
