package com.personal.crypto;

import java.security.MessageDigest;

public class SHA256 {
    public static String encode(String key) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(key.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int index = 0; index < hash.length; index++) {
                String hex = Integer.toHexString(0xff & hash[index]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
