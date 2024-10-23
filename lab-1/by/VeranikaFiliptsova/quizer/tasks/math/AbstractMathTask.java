package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Result;

abstract public class AbstractMathTask implements MathTask{
    int num1;
    int num2;
    Operation operation;
    int rightAnswer;

    public AbstractMathTask(int n1, Operation op, int n2) {
        num1 = n1;
        num2 = n2;
        operation = op;
        //rightAnswer = calculate();
    }

    @Override
    abstract public int calculate();

    @Override
    abstract public String getText();

    @Override
    public Result validate(String answer) {
        rightAnswer = calculate();
        int answerInt;
        try {
            answerInt = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
            return Result.INCORRECT_INPUT;
        }
        if (answerInt == rightAnswer) {
            return Result.OK;
        }
        //System.out.println("правильный ответ был" + rightAnswer +";  n1 = "+num1+";  n2 = "+num2 );
        return Result.WRONG;
    }

    public String myValueOf(int a) {
        if (a < 0) {
            return "(" + a + ")";
        }
        return String.valueOf(a);
    }
}
