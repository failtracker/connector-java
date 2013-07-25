package com.failtracker.connector;

/**
 * Holds values from HTTP response.
 *
 * @author Ondrej Kvasnovsky
 */
public class Response {

    private int code;
    private String message;
    private Exception exception;

    public Response(Exception e) {
        this.exception = e;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
