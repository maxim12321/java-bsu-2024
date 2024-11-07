package by.volkovets.quizer.tasks.math;

import by.volkovets.quizer.Result;

public abstract class AbstractMathTask implements MathTask {

    protected final int x_pos;
    protected final String operation;
    protected final int first;
    protected final int second;

    protected String Converte(Operation operation) {
        if (operation == Operation.Sum) {
            return " + ";
        } else if (operation == Operation.Difference) {
            return " - ";
        } else if (operation == Operation.Multiplication) {
            return " * ";
        }
        return " / ";
    }

    public AbstractMathTask(int X_Pos, Operation Operation, int Min, int Max) {
        this.x_pos = X_Pos;
        this.operation = Converte(Operation);

        this.first = Min;
        this.second = Max;
    }

    protected double getAnswer() {
        double answer;
        if (this.x_pos == 0) {
            if (this.operation == " - ") {
                answer = (this.first + this.second);
            } else if (this.operation == " / ") {
                answer = (this.first * this.second);
            } else if (this.operation == " * ") {
                answer = ((double) this.second / this.first);
            } else {
                answer = (this.second - this.first);
            }
        } else if (this.x_pos == 1) {
            if (this.operation == " - ") {
                answer = (this.first - this.second);
            } else if (this.operation == " / ") {
                answer = ((double) this.first / this.second);
            } else if (this.operation == " * ") {
                answer = ((double) this.second / this.first);
            } else {
                answer = (this.second - this.first);
            }
        } else {
            if (this.operation == " - ") {
                answer = (this.first - this.second);
            } else if (this.operation == " / ") {
                answer = ((double) this.first / this.second);
            } else if (this.operation == " * ") {
                answer = (this.second * this.first);
            } else {
                answer = (this.second + this.first);
            }
        }
        return answer;
    }


    @Override
    public Result validate(String answer) {

        if (!isDouble(answer)) {
            return Result.INCORRECT_INPUT;
        }
        double students_answer = Double.parseDouble(answer);

        if (Math.abs(students_answer - getAnswer()) <= 0.01) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

}