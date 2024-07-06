package com.putoet.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 hash utility class
 */
public class MD5 {
    /**
     * Hash the given text using the MD5 algorithm
     * @param text The text to hash
     * @return The MD5 hash of the given text
     * @throws IllegalArgumentException If the MD5 algorithm is not available
     */
    public static String hash(String text)  {
        try {
            final var md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            final var digest = md.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException exc) {
            throw new IllegalArgumentException(exc.getMessage(), exc);
        }
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Convert the given byte array to a hexadecimal string
     * @param bytes The byte array to convert
     * @return The hexadecimal string representation of the given byte array
     */
    public static String bytesToHex(byte[] bytes) {
        final var hexChars = new char[bytes.length * 2];
        for (var j = 0; j < bytes.length; j++) {
            var v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
