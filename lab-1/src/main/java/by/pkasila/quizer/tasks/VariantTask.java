package by.pkasila.quizer.tasks;

import by.pkasila.quizer.common.Result;
import by.pkasila.quizer.common.Task;

public interface VariantTask extends Task {
    default Result validateVariant(String answer) {
        try {
            TaskVariant variant = TaskVariant.valueOf(answer);
            return variant.equals(getCorrectVariant()) ? Result.OK : Result.WRONG;
        } catch (IllegalArgumentException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    default String getTextVariant() {
        return getQuestion() +
                "\nPossible choices: \nA) " +
                getAnswerVariants()[0] + "\nB) " +
                getAnswerVariants()[1] + "\nC) " +
                getAnswerVariants()[2];
    }

    String[] getAnswerVariants();

    String getQuestion();

    TaskVariant getCorrectVariant();
}
