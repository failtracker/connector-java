package com.failtracker.connector;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the request that is send to the FailTracker REST API.
 *
 * @author Ondrej Kvasnovsky
 */
public class Request implements Callable<Response> {

    private static Logger logger = Logger.getLogger(Request.class.getName());

    private URL url;
    private String data;

    public Request(URL url, String data) {
        this.url = url;
        this.data = data;
    }

    @Override
    public Response call() throws Exception {

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Sending failure to " + url);
            logger.info(" JSON: " + data);
        }

        Response response;
        HttpURLConnection conn = null;
        OutputStream os = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            String responseMessage = conn.getResponseMessage();
            int responseCode = conn.getResponseCode();

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.valueOf(responseCode));
                logger.info(responseMessage);
            }

            response = new Response(responseCode, responseMessage);

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (os != null) {
                os.close();
            }
        }
        return response;
    }
}
