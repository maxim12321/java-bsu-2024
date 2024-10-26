package by.Alesia.quizer.generators.math;

import by.Alesia.quizer.tasks.ExpressionTask;
import by.Alesia.quizer.tasks.math.MathTask;

import java.util.Vector;

import static by.Alesia.quizer.tasks.math.MathTask.Operation.*;
import static by.Alesia.quizer.tasks.math.MathTask.Operation.DIV;

abstract public class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    protected Vector<MathTask.Operation> operations = new Vector<MathTask.Operation>(4);
    protected final int minNum;
    protected final int maxNum;

    protected AbstractMathTaskGenerator(boolean add, boolean sub, boolean mul, boolean div, int minNum1, int maxNum1) {
        if (add) {
            operations.addElement(ADD);
        }
        if (sub) {
            operations.addElement(SUB);
        }
        if (mul) {
            operations.addElement(MUL);
        }
        if (div) {
            operations.addElement(DIV);
        }
        this.minNum = minNum1;
        this.maxNum = maxNum1;
    }

    public Integer genNum() {
        return minNum + (int) ( Math.random() * (maxNum - minNum) );
    }

    public MathTask.Operation genOperation() {
        return  operations.get((int) ( Math.random() * operations.size() - 1));
    }

    @Override
    public int minNumber() {
        return minNum;
    }

    @Override
    public int maxNumber() {
        return maxNum;
    }

}