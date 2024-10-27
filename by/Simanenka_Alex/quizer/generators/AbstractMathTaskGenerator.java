package by.Simanenka_Alex.quizer.generators;

import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.tasks.MathTask;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {


    public AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> generateOperation)
//            boolean generateSum,
//            boolean generateDifference,
//            boolean generateMultiplication,
//            boolean generateDivision)
    {
        try {
            this.minNumber = minNumber;
            this.maxNumber = maxNumber;
            this.generateOperation = generateOperation;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        }

//        this.generateSum = generateSum;
//        this.generateDifference = generateDifference;
//        this.generateMultiplication = generateMultiplication;
//        this.generateDivision = generateDivision;

    }


    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    public int generateArg() {
        Random rand = new Random();
        return getMinNumber() + rand.nextInt(getDiffNumber() + 1);
    }

    public MathTask.Operation generateOp() {
        Random rand = new Random();
        Iterator<MathTask.Operation> iter = generateOperation.iterator();
        int numOfOp = rand.nextInt(generateOperation.size());
        while (numOfOp > 0) {
            //System.out.println(numOfOp + " " + iter.next());
            iter.next();
            numOfOp--;
        }
        return iter.next();
    }

    protected int minNumber;
    protected int maxNumber;
//    protected boolean generateSum;
//    protected boolean generateDifference;
//    protected boolean generateMultiplication;
//    protected boolean generateDivision;
    protected EnumSet<MathTask.Operation> generateOperation;

}
