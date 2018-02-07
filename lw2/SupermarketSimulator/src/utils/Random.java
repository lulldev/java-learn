package utils;

public class Random {
    /**
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    }
}
