package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.MathTask;
import by.KirillBukato.quizer.tasks.math.StoryExpressionTask;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;

public class StoryExpressionTaskGenerator extends AbstractExpressionTaskGenerator<StoryExpressionTask> {

    public StoryExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operationSet, EnumSet<StoryExpressionTask.Story> storySet) throws InvalidGeneratorException {
        super(minNumber, maxNumber, operationSet);
        this.storySet = storySet;
    }

    public StoryExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operationSet) {
        super(minNumber, maxNumber, operationSet);
        this.storySet = EnumSet.allOf(StoryExpressionTask.Story.class);
    }

    private StoryExpressionTask.Story getRandomStory() {
        Random random = new Random();
        int index = random.nextInt(storySet.size());
        var iter = storySet.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }

    @Override
    public Optional<InvalidGeneratorException> validateGenerator() {
        return super.validateGenerator().or(() -> {
            if (storySet.isEmpty()) return Optional.of(new InvalidGeneratorException("Story set is empty"));
            return Optional.empty();
        });
    }

    @Override
    public StoryExpressionTask generateUnvalidated() {
        return new StoryExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber(), getRandomStory());
    }

    private final EnumSet<StoryExpressionTask.Story> storySet;
}
