package com.personal.system;

public class Environment {

    public static Environment getInstance() {
        return new Environment();
    }

    public String get(String key) {
        return System.getenv(key);
    }
}
