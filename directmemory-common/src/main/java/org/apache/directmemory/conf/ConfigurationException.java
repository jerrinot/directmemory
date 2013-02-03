package org.apache.directmemory.conf;

/**
 * Indicates a problem in configuration
 * 
 * TODO: This should probably be a subclass of DirectMemoryException 
 * 
 * @author jara
 *
 */
public class ConfigurationException extends RuntimeException 
{
    public ConfigurationException(String message) {
        super(message);
    }
    
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ConfigurationException(Throwable cause) {
        super(cause);
    }
}
