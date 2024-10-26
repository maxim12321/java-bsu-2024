package by.chewy_pegasus.quizer.tasks;

import by.chewy_pegasus.quizer.Result;
import by.chewy_pegasus.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {
    private final int left;
    private final int right;
    private final char operation;
    private int answer;

    public ExpressionTask(int l, int r, char op) {
        this.left = l;
        this.right = r;
        this.operation = op;
        switch (this.operation) {
            case '+' :
                this.answer = this.left + this.right;
                break;
            case '-' :
                this.answer = this.left - this.right;
                break;
            case '*' :
                this.answer = this.left * this.right;
                break;
            case '/' :
                this.answer = this.left / this.right;
                break;
        }
    }

    @Override
    public String getText() {
        return String.valueOf(this.left) + this.operation +
                this.right +
                '=';
    }

    @Override
    public Result validate(String answer) {
        return answer.equals(String.valueOf(this.answer)) ? Result.OK : Result.WRONG;
    }
}
