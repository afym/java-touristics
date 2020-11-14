package com.personal.system;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ConfigurationTest {
    @Test
    public void validationTestProperties() {
        Configuration config = Configuration.getInstance("test/variables");

        assertEquals("1", config.get("a"));
        assertEquals("2", config.get("b"));
        assertEquals("43.22", config.get("c"));
        assertEquals("foo", config.get("d"));
    }
}
