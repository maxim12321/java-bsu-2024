package by.Simanenka_Alex.quizer.tasks;

import by.Simanenka_Alex.quizer.Result;
import by.Simanenka_Alex.quizer.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class ArithmeticMeanTask implements Task {

    public ArithmeticMeanTask(Integer... args) {
        this.args = new ArrayList<>(Arrays.asList(args));
    }

    public ArithmeticMeanTask(ArrayList<Integer> args) {
        this.args = args;
    }

    @Override
    public String getText() {
        StringBuilder str = new StringBuilder("Give an arithmetic mean of numbers: ");
        for (var arg : args) {
            str.append(arg).append(" ");
        }
        return str.toString();
    }

    @Override
    public Result validate(String answer) {
        double myAns = calcAns();
        if (!isValid(answer)) {
            return Result.INCORRECT_INPUT;
        }
        double studAns = Double.parseDouble(answer);
        if (Math.abs(myAns - studAns) < 0.0001) {
            return Result.OK;
        }
        return Result.WRONG;
    }

    private double calcAns() {
        int res = 0;
        for (var arg : args) {
            res += arg;
        }
        return (double) res / args.size();
    }

    protected boolean isValid(String answer) {
        try {
            double a = Double.parseDouble(answer);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    ArrayList<Integer> args;
}
