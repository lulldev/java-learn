package utils;

public class RandomUtil {

    public static int getRandomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    }
}
