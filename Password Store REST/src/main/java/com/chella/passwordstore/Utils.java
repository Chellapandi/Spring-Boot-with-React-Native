package com.chella.passwordstore;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String getHashString(String input) {
        MessageDigest digest = null;
        String hashValue = "";
        try {
            digest = MessageDigest.getInstance(Constants.SHA_256);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (null != digest) {
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            hashValue = new String(hash);
        }
        return hashValue;
    }
}
