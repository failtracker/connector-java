package com.failtracker.connector;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Provides helper functions for work with {@link Throwable} class.
 *
 * @author Ondrej Kvasnovsky
 */
public class ThrowableUtils {

    public static String getStackTrace(final Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
