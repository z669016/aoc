package com.putoet.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String hash(String text)  {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            final byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException exc) {
            throw new IllegalArgumentException(exc.getMessage(), exc);
        }
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        final char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
