package utils;

public class Utilities {
    /**
     * Truncates a string to the specified length.
     *
     * @param input  The input string to be truncated.
     * @param length The maximum length of the truncated string.
     * @return The truncated string.
     */
    public static String truncateString(String input, int length) {
        if (input == null) {
            return "";
        }
        if (input.length() <= length) {
            return input;
        }
        return input.substring(0, length);
    }
}
