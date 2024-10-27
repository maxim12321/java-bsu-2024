package by.thekeenest.quizer.generators;

import by.thekeenest.quizer.tasks.Task;
import by.thekeenest.quizer.Result;

public class TextTaskGenerator implements TaskGenerator<Task> {
    private final String question;
    private final String correctAnswer;

    public TextTaskGenerator(String question, String correctAnswer) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be null or blank");
        }
        if (correctAnswer == null || correctAnswer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be null or blank");
        }
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public Task generate() {
        return new Task() {
            @Override
            public String getText() {
                return question;
            }

            @Override
            public Result validate(String answer) {
                if (answer == null || answer.isBlank()) {
                    return Result.INCORRECT_INPUT;
                }
                return answer.trim().equalsIgnoreCase(correctAnswer.trim())
                        ? Result.OK
                        : Result.WRONG;
            }
        };
    }
}