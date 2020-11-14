package com.personal.crypto;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SHA256Test {
    @Test
    public void validateEncode() throws Exception {
       String s1 = "personal.com";
       String s2 = "otherone.com";
       String s3 = "anotherone.com";
       String s4 = "[1, 2, 3, 4, 5]";
       String s5 = "You don't necessarily need the library";
       String s6 = "5bdf7f30088c7a9c5ee86655f3a2dceed0337";

       assertEquals("d594711514d2436953eedfdf5e5d9f1aa0684102853d54d05c5cd1fa5eb54893", SHA256.encode(s1));
       assertEquals("575cc75bdf7f30088c7a9c5ee86655f3a2dceed0337d813c7212b21767e4f6ec", SHA256.encode(s2));
       assertEquals("d88aa89a23b7b58d13ea0a47cced1937bf7ec1ed6e6634aa243c6155d5f1f351", SHA256.encode(s3));
       assertEquals("0c049903ce2330190375d4c1f2e489888c9ebe39daf75b2564e591e8bc1afe72", SHA256.encode(s4));
       assertEquals("313eb3306e263fafa0f56546a4e5e20530b590522f34aa15faa8f03828b20b5e", SHA256.encode(s5));
       assertEquals("5de40df95211a369a6bb7ce6966cb0c75cb9f85b986c651a92d71911b25c16ec", SHA256.encode(s6));
    }
}
