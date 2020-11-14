package com.personal.crypto;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AES256Test {
    private static String KEY = "072180d3e67033ec2a1675efcb37ec4c5f6ae8d9563c030a8d8ada7b020efa55";
    private static String KEY_LOGIN = "ec4c5f6ae8d9563c030a8d8ada7b020efa55072180d3e67033ec2a1675efcb37";

    @Test
    public void validateEncode() throws Exception {
        AES256 aes256 = AES256.getInstance(KEY);

        String m1 = aes256.encode("personal.com");
        String m2 = aes256.encode("otherone.com");
        String m3 = aes256.encode("google.com");

        assertEquals("7zYMw8mKLokrel4lkwDRrI9PawwYwfX55UHiYIYO0+w=", m1);
        assertEquals("HdDvgBYG7tscLAo9VUaD+Q==", m2);
        assertEquals("1o+pIViN89FpCsBgCJ2wvQ==", m3);
    }

    @Test
    public void validateDecode() throws Exception {
        AES256 aes256 = AES256.getInstance(KEY);

        String m1 = aes256.decode("7zYMw8mKLokrel4lkwDRrI9PawwYwfX55UHiYIYO0+w=");
        String m2 = aes256.decode("HdDvgBYG7tscLAo9VUaD+Q==");
        String m3 = aes256.decode("1o+pIViN89FpCsBgCJ2wvQ==");

        assertEquals("personal.com", m1);
        assertEquals("otherone.com", m2);
        assertEquals("google.com", m3);
    }

    @Test
    public void validateUserPassword() throws Exception {
        AES256 aes256 = AES256.getInstance(KEY_LOGIN);

        String salt = "14a014";
        String user = "adminitrador@personal.com";
        String password = "55UHiYIYO0+w=";

        StringBuilder message = new StringBuilder();
        message.append(salt)
                .append("(")
                .append(user)
                .append(";")
                .append(password)
                .append(")");

        String m1 = aes256.encode(message.toString());
        String m2 = aes256.decode(m1);

        String response = m2.replace(salt, "")
                .replace("(", "")
                .replace(")", "");

        String[] responseParts = response.split(";");

        assertEquals("H2ZGxcCTrPVMWmq21KauM4fTfWGTBr1szXr/aOw4JRsDZWsaYXq8odFRNbX8h6dHzjawYhZyZVMQGnio7tk0dA==", m1);
        assertEquals(user, responseParts[0]);
        assertEquals(password, responseParts[1]);
    }
}
