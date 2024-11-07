package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Task;

public interface MathTask extends Task {
    enum Operation {
        SUM,
        DIFF,
        MUL,
        DIV;

        public static String myValueOf(Operation op) {
            return switch (op) {
                case SUM-> "+";
                case DIFF-> "-";
                case MUL->"*";
                case DIV->"/";
            };
        }
    }
    public String myValueOf(int a);
    /**
     @return правильный ответ
     */
    int calculate();
}
