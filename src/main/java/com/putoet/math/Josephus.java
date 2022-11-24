package com.putoet.math;

public class Josephus {
    public static int winner(int teamSize) {
        final var binary = Integer.toBinaryString(teamSize);
        final var winner = binary.substring(1) + "1";
        return Integer.valueOf(winner,2);
    }
}
