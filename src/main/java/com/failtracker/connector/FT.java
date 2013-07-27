package com.failtracker.connector;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enables to send failures to FailTracker REST API.
 *
 * @author Ondrej Kvasnovsky
 */
public class FT {

    private static Logger logger = Logger.getLogger(FT.class.getName());

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

    public void send(Failure f, ResponseCallback responseCallback) {
        try {
            connector.send(f, responseCallback);
        } catch (ConnectorException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            Response response = new Response(e);
            responseCallback.response(response);
        }
    }

    public Connector getConnector() {
        return this.connector;
    }

    public void setLoggingLevel(Level level) {
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(level);
    }
}
