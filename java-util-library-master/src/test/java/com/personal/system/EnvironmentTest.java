package com.personal.system;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class EnvironmentTest {
    @Test
    public void validationTestProperties() {
        Environment environment = Environment.getInstance();

        assertEquals(true, environment.get("PATH").length() > 3);
    }
}
