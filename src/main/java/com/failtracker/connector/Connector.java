package com.failtracker.connector;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Connects Java code with FailTracker.com REST service. It enables sending fails to failtracker.com.
 *
 * @author Ondrej Kvasnovsky
 */
public class Connector {

    private String url;
    private String apiKey;

    private boolean debug = false;

    public Connector(final String apiKey) {
        this.url = "http://failtracker-rest.herokuapp.com/fail/insert";
        this.apiKey = apiKey;
    }

    public Connector(final String url, final String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    public Response send(final Failure failure) throws ConnectorException {
        Response res;
        String input = failure.asJson(new Date(), apiKey);
        try {
            if (debug) {
                System.out.println("Sending failure to " + url);
                System.out.println(" JSON: " + input);
            }
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            String responseMessage = conn.getResponseMessage();
            int responseCode = conn.getResponseCode();
            res = new Response(responseCode, responseMessage);
            if (debug) {
                System.out.println(responseCode);
                System.out.println(responseMessage);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            throw new ConnectorException(e);
        } catch (IOException e) {
            throw new ConnectorException(e);
        }
        return res;
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

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
