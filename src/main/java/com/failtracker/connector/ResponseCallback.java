package com.failtracker.connector;

/**
 * Callback to get response asynchronously.
 *
 * @author Ondrej Kvasnovsky
 * @version 0.1.1
 */
public interface ResponseCallback {

    public void response(Response response);
}
