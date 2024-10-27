package by.thekeenest.quizer.tasks.math;
import by.thekeenest.quizer.tasks.Task;
import java.util.Set;


public interface MathTask extends Task {
    enum Operation {
        PLUS("+") {
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        },
        MINUS("-") {
            @Override
            public int apply(int a, int b) {
                return a - b;
            }
        },
        PRODUCT("*") {
            @Override
            public int apply(int a, int b) {
                return a * b;
            }
        };

        private final String symbol;

        Operation(String s) {
            this.symbol = s;
        }

        public String getSymbol() {
            return symbol;
        }

        public abstract int apply(int a, int b);
    }

    int getCorrectAnswer();
    String getExpression();
    Set<Operation> getOperations();
}
