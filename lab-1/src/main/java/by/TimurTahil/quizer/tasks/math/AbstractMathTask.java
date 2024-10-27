package by.TimurTahil.quizer.tasks.math;

import by.TimurTahil.quizer.Result;

public abstract class AbstractMathTask implements MathTask {

    protected final int positionOfX;
    protected final int firstNumber;
    protected final int secondNumber;
    protected final Operators operator;

    public AbstractMathTask(int positionOfX, int firstNumber, int secondNumber, Operators operation) {
        this.positionOfX = positionOfX;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operation;
    }

    public abstract Result validate(String answer);
    public abstract String getText();

    protected double GetAnswer() {
        double answer = -1;
        if (positionOfX == 0) {
            switch (this.operator) {
                case Operators.SUM -> answer = secondNumber - firstNumber;
                case Operators.SUBTRACTION -> answer = secondNumber + firstNumber;
                case Operators.DIVISION -> answer = secondNumber * firstNumber;
                case Operators.MULTIPLICATION -> answer = (double) secondNumber / firstNumber;
            }
        } else if (positionOfX == 1) {
            switch (this.operator) {
                case Operators.SUM -> answer = secondNumber - firstNumber;
                case Operators.SUBTRACTION -> answer = firstNumber - secondNumber;
                case Operators.DIVISION -> answer = (double) firstNumber / secondNumber;
                case Operators.MULTIPLICATION -> answer = (double) secondNumber / firstNumber;
            }
        } else if (positionOfX == -1) {
            switch (operator) {
                case Operators.SUM -> answer = secondNumber + firstNumber;
                case Operators.SUBTRACTION -> answer = firstNumber - secondNumber;
                case Operators.DIVISION -> answer = (double) firstNumber / secondNumber;
                case Operators.MULTIPLICATION -> answer = secondNumber * firstNumber;
            }
        } else {
            throw new IllegalArgumentException("wrong value of positionOfX: " + this.positionOfX);
        }
        return answer;
    }
}