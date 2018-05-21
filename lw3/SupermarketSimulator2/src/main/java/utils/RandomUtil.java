package utils;

import java.util.Random;


public class RandomUtil {

    public static int getRandomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    }

    public static int getRandomFromArray(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
