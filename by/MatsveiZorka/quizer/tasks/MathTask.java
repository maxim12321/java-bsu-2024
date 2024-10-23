package by.MatsveiZorka.quizer.tasks;

import by.MatsveiZorka.quizer.Task;

public interface MathTask extends Task {
    default String prepareAnswer(String answer) {
        String res = answer.trim(); // deletes spaces
        if (res.chars().anyMatch(a->(a < '0' || a > '9') && (a != '-')))
            return null;
        return res;
    }

    default Integer parseAnswer(String answer) {
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
