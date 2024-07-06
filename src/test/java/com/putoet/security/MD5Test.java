package com.putoet.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5Test {

    @Test
    void hash() {
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", MD5.hash(""));
        assertEquals("098F6BCD4621D373CADE4E832627B4F6", MD5.hash("test"));
        assertEquals("5EB63BBBE01EEED093CB22BB8F5ACDC3", MD5.hash("hello world"));
    }

    @Test
    void bytesToHex() {
        assertEquals("000102030405060708090A0B0C0D0E0F",
                MD5.bytesToHex(new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));
        assertEquals("000102030405060708090A0B0C0D0E0F1011",
                MD5.bytesToHex(new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17}));
    }
}