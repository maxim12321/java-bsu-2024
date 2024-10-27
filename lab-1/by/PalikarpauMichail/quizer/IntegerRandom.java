package by.PalikarpauMichail.quizer;

import java.util.Random;

public class IntegerRandom {
    static public int get(int minNumber, int maxNumber) {
        return minNumber + generator.nextInt(maxNumber - minNumber + 1);
    }
    static private Random generator = new Random();
}
