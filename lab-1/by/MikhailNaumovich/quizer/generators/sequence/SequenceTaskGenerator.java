package by.MikhailNaumovich.quizer.generators.sequence;

import by.MikhailNaumovich.quizer.TaskGenerator;

import by.MikhailNaumovich.quizer.tasks.sequence.PatternTypeEnum;
import by.MikhailNaumovich.quizer.tasks.sequence.SequenceTask;

import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;
import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class SequenceTaskGenerator implements TaskGenerator<SequenceTask> {

    public SequenceTaskGenerator(int startRange, EnumSet<PatternTypeEnum> allowedPatterns, int minLength, int maxLength) {
        if (minLength > maxLength) {
            throw new InvalidArgumentException("Minlength is bigger than maxlength");
        }
        this.startRange = startRange;
        this.allowedPatterns = allowedPatterns;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.random = new Random();
    }

    @Override
    public SequenceTask generate() {
        PatternTypeEnum selectedPattern = getRandomPattern();
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        int startNumber = random.nextInt(startRange + 1);
        
        int[] sequence = generateSequence(selectedPattern, startNumber, length);
        int answer = calculateNextNumber(selectedPattern, sequence);
        
        return new SequenceTask(sequence, answer, selectedPattern, length);
    }

    private PatternTypeEnum getRandomPattern() {
        if (allowedPatterns.isEmpty()) {
            throw new InvalidGeneratorException("No patterns to choose from");
        }

        if (allowedPatterns.stream().anyMatch(Objects::isNull)) {
            throw new InvalidArgumentException("allowedPatterns contains null elements");
        }

        List<PatternTypeEnum> patterns = new ArrayList<>(allowedPatterns);
        return patterns.get(random.nextInt(patterns.size()));
    }

    private int[] generateSequence(PatternTypeEnum PatternTypeEnum, int startNumber, int length) {
        int[] sequence = new int[length];
        
        switch (PatternTypeEnum) {
            case FIBONACCI:
                sequence[0] = startNumber;
                sequence[1] = startNumber + random.nextInt(5) + 1;
                for (int i = 2; i < length; i++) {
                    sequence[i] = sequence[i-1] + sequence[i-2];
                }
                break;
                
            case SQUARE:
                int sqrtStart = (int)Math.sqrt(startNumber);
                for (int i = 0; i < length; i++) {
                    sequence[i] = (sqrtStart + i) * (sqrtStart + i);
                }
                break;
                
            case TRIANGULAR:
                int triStart = (int)((Math.sqrt(8 * startNumber + 1) - 1) / 2);
                for (int i = 0; i < length; i++) {
                    sequence[i] = (triStart + i) * (triStart + i + 1) / 2;
                }
                break;
        }
        return sequence;
    }

    private int calculateNextNumber(PatternTypeEnum PatternTypeEnum, int[] sequence) {
        int length = sequence.length;

        switch (PatternTypeEnum) {
            case FIBONACCI:
                return sequence[length-1] + sequence[length-2];
                
            case SQUARE:
                int lastSqrt = (int)Math.sqrt(sequence[length-1]);
                return (lastSqrt + 1) * (lastSqrt + 1);
                
            case TRIANGULAR:
                int n = (int)((Math.sqrt(8 * sequence[length-1] + 1) - 1) / 2);
                return (n + 1) * (n + 2) / 2;
                
            default:
                throw new IllegalStateException("Unknown pattern type");
        }
    }

    private final int startRange;
    private final EnumSet<PatternTypeEnum> allowedPatterns;
    private final int minLength;
    private final int maxLength;
    private final Random random;
}