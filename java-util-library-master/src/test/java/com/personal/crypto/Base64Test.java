package com.personal.crypto;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Base64Test {

    @Test
    public void validateEncode() throws Exception {
        String string1 = "personal.com";
        String string2 = "base64encode";

        String string3 = "otherone.com";
        String string4 = "limaperu";

        assertEquals("YcGVyc29uYWwuY29t", Base64.encode(string1));
        assertEquals("YmFzZTY0ZW5jb2Rl", Base64.encode(string2));
        assertEquals("b3RoZXJvbmUuY29t", Base64.encode(string3));
        assertEquals("bGltYXBlcnU=", Base64.encode(string4));
    }

    @Test
    public void validateDecode() throws Exception {
        String string1 = "aW5mbGVjdHJh";
        String string2 = "dWRlbXkgbGFyZ2UgW10gc3RyaW5n";

        String string3 = "W3siYSIgOiAicyJ9XQ==";
        String string4 = "WzEsIDIsIDMsIDQsIDVd";

        assertEquals("inflectra", Base64.decode(string1));
        assertEquals("udemy large [] string", Base64.decode(string2));
        assertEquals("[{\"a\" : \"s\"}]", Base64.decode(string3));
        assertEquals("[1, 2, 3, 4, 5]", Base64.decode(string4));
    }
}