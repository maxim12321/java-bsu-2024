package by.thekeenest.quizer.tasks.math;
import by.thekeenest.quizer.Result;

public class ExpressionMathTask extends AbstractMathTask {
    public ExpressionMathTask(int firstNumber, int secondNumber, Operation operation) {
        super(firstNumber, secondNumber, operation);
    }

    @Override
    public String getText() {
        return String.format("Решите пример: %s", getExpression());
    }

    @Override
    public String getExpression() {
        return String.format("%d %s %d", firstNumber, operation.getSymbol(), secondNumber);
    }

    @Override
    public int getCorrectAnswer() {
        return operation.apply(firstNumber, secondNumber);
    }

}