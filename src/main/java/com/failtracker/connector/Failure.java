package com.failtracker.connector;

import java.util.Date;

/**
 * {@link Failure} represents failure, error or crash which can be send to FailTracker.com.
 *
 * @author Ondrej Kvasnovsky
 */
public class Failure {

    private String title;
    private String content;

    public Failure() {
    }

    public Failure(final String title) {
        this.title = title;
    }

    public Failure(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public Failure(final Throwable e) {
        this.title = e.getMessage();
        this.content = ThrowableUtils.getStackTrace(e);
    }

    public Failure(final String title, final Throwable e) {
        this.title = title;
        this.content = ThrowableUtils.getStackTrace(e);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String asJson(Date now, String apiKey) {
        content = JsonEscaper.escape(title);
        if (title.length() >= 256) {
            String substring = title.substring(0, 256);
            title = JsonEscaper.escape(substring);
        } else {
            title = JsonEscaper.escape(title);
        }
        String json = "{" +
                "\"title\":" + title + "," +
                "\"content\":" + content + "," +
                "\"token\":\"" + apiKey + "\"," +
                "\"date\":\"" + now.getTime() +
                "\"}";
        return json;
    }
}
