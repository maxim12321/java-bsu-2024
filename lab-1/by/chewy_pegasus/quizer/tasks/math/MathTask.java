package by.chewy_pegasus.quizer.tasks.math;

import by.chewy_pegasus.quizer.Task;

public interface MathTask extends Task {
    //String convertToParseble(String answer, boolean floating);

    public void confirmAnswerCorrectness(String answer, boolean floating) throws IllegalArgumentException;

    enum Operation {
        SUM,
        DIFF,
        DIV,
        MULT
    }
}
