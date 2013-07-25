package com.failtracker.connector;

/**
 * Enables to send failures to FailTracker REST API.
 *
 * @author Ondrej Kvasnovsky
 */
public class FT {

    private Connector connector;

    public FT(String projectToken) {
        this.connector = new Connector(projectToken);
    }

    public FT(Connector connector) {
        this.connector = connector;
    }

    public Response send(Failure f) {
        try {
            Response send = connector.send(f);
            return send;
        } catch (ConnectorException e) {
            return new Response(e);
        }
    }

    public void setDebug(boolean debug) {
        connector.setDebug(debug);
    }
}
