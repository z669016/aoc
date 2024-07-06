package com.putoet.math;

/**
 * The Josephus problem is a theoretical problem related to a certain counting-out game.
 * The problem is named after Flavius Josephus, a Jewish historian living in the 1st century.
 * According to Josephus' account of the siege of Yodfat, he and his 40 soldiers were trapped in a cave by Roman soldiers
 */
public class Josephus {
    /**
     * Determine the winner of the Josephus problem for a given team size.
     * @param teamSize The size of the team
     * @return The winner of the Josephus problem
     */
    public static int winner(int teamSize) {
        final var binary = Integer.toBinaryString(teamSize);
        final var winner = binary.substring(1) + "1";
        return Integer.valueOf(winner,2);
    }
}
