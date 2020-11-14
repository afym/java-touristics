package com.personal.crypto;

import java.util.UUID;

public class SecretKey {
    public static String generate() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return SHA256.encode(uuid);
    }
}
