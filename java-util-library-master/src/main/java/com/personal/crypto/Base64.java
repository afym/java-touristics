package com.personal.crypto;

public class Base64 {
    public static String encode(String string) {
        return new String(java.util.Base64.getEncoder().encode(string.getBytes()));
    }

    public static String decode(String string) {
        return new String(java.util.Base64.getDecoder().decode(string.getBytes()));
    }
}
