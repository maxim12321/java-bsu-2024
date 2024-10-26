package by.chewy_pegasus.quizer.tasks;

import by.chewy_pegasus.quizer.Result;
import by.chewy_pegasus.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {

    private int other;
    private int eq;
    private char operation;
    private int answer;
    private boolean first;

    /**
     *
     * @param first - является ли первый элемент искомым
     * @param other - второй аргумент операции
     * @param eq - число после равно
     * @param op - операция
     */
    public EquationTask(boolean first, int other, int eq, char op) {
        this.operation = op;
        this.eq = eq;
        this.other = other;
        this.first = first;

        switch (this.operation) {
            case '+':
                this.answer = eq - other;
                break;
            case '-':
                if (first) {
                    this.answer = eq + other;
                } else {
                    this.answer = other - eq;
                }
                break;
            case '*':
                this.answer = eq / other;
                break;
            case '/':
                if (first) {
                    this.answer = eq * other;
                } else {
                    this.answer = other / eq;
                }
                break;
        }
    }

    @Override
    public String getText() {
        StringBuilder s = new StringBuilder();
        if (this.first) {
            s.append('X');
        } else {
            s.append(this.other);
        }
        s.append(this.operation);
        if (!this.first) {
            s.append('X');
        } else {
            s.append(this.other);
        }
        s.append("=").append(this.eq);
        return s.toString();
    }

    @Override
    public Result validate(String answer) {
        return String.valueOf(this.answer).equals(answer) ? Result.OK : Result.WRONG;
    }
}
