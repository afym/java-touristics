package com.personal.crypto;

import java.security.*;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256 {
    private String keyEncryption;
    private SecretKeySpec secret;

    public static AES256 getInstance(String keyEncryption) {
        return new AES256(keyEncryption);
    }

    public AES256(String keyEncryption) {
        this.keyEncryption = keyEncryption;
        this.createKey();
    }

    public String encode(String decrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return Base64.getEncoder().encodeToString(cipher.doFinal(decrypted.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createKey() {
        try {
            byte[] key;
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = this.keyEncryption.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secret = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
