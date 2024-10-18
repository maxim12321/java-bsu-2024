package by.VeranikaFiliptsova.quizer;

public enum Operation {
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
