package com.personal.crypto;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SecretKeyTest {
    @Test
    public void validateGenerate() throws Exception {

        assertEquals(64, SecretKey.generate().length());
        assertEquals(64, SecretKey.generate().length());
        assertEquals(64, SecretKey.generate().length());
        assertEquals(64, SecretKey.generate().length());
        assertEquals(64, SecretKey.generate().length());
    }
}
