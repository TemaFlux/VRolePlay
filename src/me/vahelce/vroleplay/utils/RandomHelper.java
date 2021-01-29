package me.vahelce.vroleplay.utils;

import java.util.Random;

public class RandomHelper {
    private static final Random random = new Random();

    public static int getRandom(int max) {
        return random.nextInt(max);
    }
}
