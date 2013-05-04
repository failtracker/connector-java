package com.failtracker.connector;

/**
 * {@link ConnectorException} represents exception for this connector.
 *
 * @author Ondrej Kvasnovsky
 */
public class ConnectorException extends Exception {

    public ConnectorException(Throwable cause) {
        super(cause);
    }
}
