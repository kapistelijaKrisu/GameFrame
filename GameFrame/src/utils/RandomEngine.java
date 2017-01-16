package utils;

import java.util.Random;

public class RandomEngine {

    private static final Random random = new Random();

    public static int randomNumber(int min, int max) {
        return (int) (random.nextFloat() * (max + 1) + min);
    }
}
