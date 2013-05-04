package com.failtracker.connector;

/**
 * Enables to send failures to FailTracker REST API.
 *
 * @author Ondrej Kvasnovsky
 */
public class FT {

    private Connector connector;

    public FT(Connector connector) {
        this.connector = connector;
    }

    public void send(Failure f) {
        try {
            connector.send(f);
        } catch (ConnectorException e) {
            // TODO: maybe callback to error handler would be nice solution?
            e.printStackTrace();
        }
    }
}
