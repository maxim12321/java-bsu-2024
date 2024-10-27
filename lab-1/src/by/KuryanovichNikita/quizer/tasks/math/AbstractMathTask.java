package by.KuryanovichNikita.quizer.tasks.math;

import by.KuryanovichNikita.quizer.Result;

public abstract class AbstractMathTask implements MathTask{

    protected final String text;
    protected final int first;
    protected final int second;
    protected final String operator;

    protected AbstractMathTask(String text, int first, int second, String operator) {
        this.text = text;
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    @Override
    public String getText() {
        return text;
    }
    public double compute(){
        return 0;
    }
    @Override
    public Result validate(String answer) {
        double answD;
        try{
            answD = Double.parseDouble(answer);
        }
        catch(NumberFormatException e){
            return Result.INCORRECT_INPUT;
        }
        if(Math.abs(answD - compute()) < 0.01)
            return Result.OK;
        else
            return Result.WRONG;
    }
}
