package com.failtracker.connector;

/**
 * {@link JsonEscaper} provides helper methods for escaping of JSON data.
 *
 * @author Ondrej Kvasnovsky
 */
public class JsonEscaper {

    public static String escape(final String json) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        int jsonLenght = json.length();
        for (int i = 0; i < jsonLenght; i++) {
            char ch = json.charAt(i);
            switch (ch) {
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(ch);
                    break;
            }
        }
        sb.append("\"");
        return sb.toString();
    }
}
