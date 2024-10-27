package by.mmaxemm.quizer.tasks.math;
import by.mmaxemm.quizer.*;

public interface MathTask extends Task {
    /**
     * @return ожидаемый ответ на задание
     */

    public double calculateRightAnswer();

    public static enum Operation {
        ADDITION("+"),
        SUBSTRACTION("-"),
        MULTIPLICATION("/"),
        DIVISION("*");

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

    }
}
