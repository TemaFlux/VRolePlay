package me.vahelce.vroleplay.utils;

public class StringUtils {
    public static String concatMessage(int startIndex, String... message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i < message.length; i++) {
            stringBuilder.append(message[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    public static String concatMessage(String... message) {
        return concatMessage(0, message);
    }
}
