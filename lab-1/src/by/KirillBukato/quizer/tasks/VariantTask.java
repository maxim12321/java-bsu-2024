package by.KirillBukato.quizer.tasks;

import by.KirillBukato.quizer.Result;

public interface VariantTask extends Task {

    /**
     * Общий метод для валидации по варианту, который вызывается в метод validate() у классов, реализующих этот интерфейс
     */
    default Result validateVariant(String answer) {
        try {
            TaskVariant variant = TaskVariant.valueOf(answer);
            return variant.equals(getCorrectVariant()) ? Result.OK : Result.WRONG;
        } catch (IllegalArgumentException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    /**
     * Общий метод получения текста для заданий с вариантами ответа.
     */
    default String getTextVariant() {
        return getQuestion() +
                " Варианты ответа: \nA) " +
                getAnswerVariants()[0] + "\nB) " +
                getAnswerVariants()[1] + "\nC) " +
                getAnswerVariants()[2];
    }

    String[] getAnswerVariants();

    String getQuestion();

    TaskVariant getCorrectVariant();
}
