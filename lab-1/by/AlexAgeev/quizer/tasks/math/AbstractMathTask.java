package by.AlexAgeev.quizer.tasks.math;

import by.AlexAgeev.quizer.Result;

public abstract class AbstractMathTask implements MathTask {

    public AbstractMathTask(int x_pos, int first_num, int second_num, Operators operation) {
        this.x_pos = x_pos;
        this.first_num = first_num;
        this.second_num = second_num;
        this.operator = operation;
    }

    protected double Answer() {
        String operator = OperatorToString(this.operator);
        double answer;
        if (x_pos == 0) {
            switch (operator) {
                case "+" -> answer = second_num - first_num;
                case "-" -> answer = second_num + first_num;
                case "/" -> answer = second_num * first_num;
                default -> answer = (double) second_num / first_num;
            }
        } else if (x_pos == 1) {
            switch (operator) {
                case "+" -> answer = second_num - first_num;
                case "-" -> answer = first_num - second_num;
                case "/" -> answer = (double) first_num / second_num;
                default -> answer = (double) second_num / first_num;
                }
        } else {
            switch (operator) {
                case "+" -> answer = second_num + first_num;
                case "-" -> answer = first_num - second_num;
                case "/" -> answer = (double) first_num / second_num;
                default -> answer = second_num * first_num;
            }
        }
        return answer;
    };



    protected final int x_pos;
    protected final int first_num;
    protected final int second_num;
    protected final Operators operator;
}
