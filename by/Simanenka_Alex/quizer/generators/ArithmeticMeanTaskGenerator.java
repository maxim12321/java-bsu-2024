package by.Simanenka_Alex.quizer.generators;

import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.TaskGenerator;
import by.Simanenka_Alex.quizer.tasks.ArithmeticMeanTask;
import by.Simanenka_Alex.quizer.tasks.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class ArithmeticMeanTaskGenerator implements TaskGenerator {

    private int minNumber;
    private int maxNumber;

    public ArithmeticMeanTaskGenerator(
            int minNumber,
            int maxNumber)
    {
        try {
            this.minNumber = minNumber;
            this.maxNumber = maxNumber;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }
    }


        @Override
    public ArithmeticMeanTask generate() {
        Random rand = new Random();
        int kolArgs = rand.nextInt(2, 5);
        return new ArithmeticMeanTask(generateArgs(kolArgs));
    }

    public ArrayList<Integer> generateArgs(int kolArgs) {
        Random rand = new Random();
        var list = new ArrayList<Integer>();
        for (int i = 0; i < kolArgs; i++) {
            list.add(getMinNumber() + rand.nextInt((getMaxNumber() - getMinNumber()) + 1));
        }
        return list;
    }


    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
