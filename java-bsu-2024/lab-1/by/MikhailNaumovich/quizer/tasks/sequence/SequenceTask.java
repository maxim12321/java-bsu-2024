package by.MikhailNaumovich.quizer.tasks.sequence;

import by.MikhailNaumovich.quizer.Result;
import by.MikhailNaumovich.quizer.Task;
import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;

public class SequenceTask implements Task {
    private final int[] sequence;
    private final int answer;
    private final PatternTypeEnum patternType;
    private final int length; 

    public SequenceTask(int[] sequence, int answer, PatternTypeEnum patternType, int length) {
        this.sequence = sequence;
        this.answer = answer;
        this.patternType = patternType;
        this.length = length;
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continue the "); 
        sb.append(patternType.getDescription()).append(": ");
        for (int num : sequence) {
            sb.append(num).append(", ");
        }
        return sb.append("__").toString();
    }

    @Override
    public Result validate(String answer) {
        if (answer == null) {
            throw new InvalidArgumentException("Answer is null");
        }
        try {
            int userAnswer = Integer.parseInt(answer);
            return userAnswer == this.answer ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}