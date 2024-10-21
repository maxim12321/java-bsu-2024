package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Operation;
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

        public Operation oppositeOperation() {
            return switch (this) {
                case SUM->DIFF;
                case DIFF->SUM;
                case MUL->DIV;
                case DIV->MUL;
            };
        }
    }

    /**
     @return правильный ответ
     */
    int calculate();
}
