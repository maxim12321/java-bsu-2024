package by.KuryanovichNikita.quizer.tasks.math;

import by.KuryanovichNikita.quizer.Result;
import by.KuryanovichNikita.quizer.tasks.Task;

public interface MathTask extends Task {
    public String getText();
    public Result validate(String answer);



    enum Operation {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/");

        Operation(String operator) {
            this.operator = operator;
        }

        private final String operator;

        public String getOperator() {
            return operator;
        }
    }


}
