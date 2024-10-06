package by.KirillBukato.quizer.tasks;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.Task;

public interface VariantTask extends Task {
    enum Variants {
        A,
        B,
        C
    }

    default Result validateVariant(String answer) {
        switch (answer) {
            case "A" -> {
                if (getVariant() == VariantTask.Variants.A) return Result.OK; else return Result.WRONG;
            }
            case "B" -> {
                if (getVariant() == VariantTask.Variants.B) return Result.OK; else return Result.WRONG;
            }
            case "C" -> {
                if (getVariant() == VariantTask.Variants.C) return Result.OK; else return Result.WRONG;
            }
            default -> {
                return Result.INCORRECT_INPUT;
            }
        }
    }

    default String getTextVariant() {
        return getQuestion() +
                " Варианты ответа: \nA) " + getA() + "\nB) " + getB() + "\nC) " + getC();
    }

    String getA();
    String getB();
    String getC();

    String getQuestion();

    Variants getVariant();
}
