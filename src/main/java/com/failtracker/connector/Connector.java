package com.failtracker.connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connects Java code with FailTracker.com REST service. It enables sending fails to failtracker.com.
 *
 * @author Ondrej Kvasnovsky
 */
public class Connector {

    private static Logger logger = Logger.getLogger(Connector.class.getName());

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private String url;
    private String apiKey;

    private ResponseCallback responseCallback;

    private Level loggingLevel;

    public Connector(final String apiKey) {
        this.url = "http://rest.failtracker.com/fail/insert";
        this.apiKey = apiKey;
    }

    public Connector(final String url, final String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    public void send(final Failure failure, ResponseCallback responseCallback) throws ConnectorException {
        this.responseCallback = responseCallback;
        send(failure);
    }

    public Response send(final Failure failure) throws ConnectorException {
        Response response = new Response(-1, "Response is not filled in, something went wrong. Try to browse the exception from the response.");
        try {
            String data = failure.asJson(new Date(), apiKey);
            Request request = new Request(new URL(url), data);
            if (responseCallback != null) {
                request.setResponseCallback(responseCallback);
            }
            Future<Response> future = executor.submit(request);
            if (responseCallback == null) {
                response = future.get();
            }
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ConnectorException(e);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ConnectorException(e);
        } catch (ExecutionException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ConnectorException(e);
        }
        return response;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Level getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(Level loggingLevel) {
        this.loggingLevel = loggingLevel;
    }
}
