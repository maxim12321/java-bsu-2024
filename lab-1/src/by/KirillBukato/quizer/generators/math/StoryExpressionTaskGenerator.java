package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.MathOperation;
import by.KirillBukato.quizer.tasks.math.MathStory;
import by.KirillBukato.quizer.tasks.math.StoryExpressionTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class StoryExpressionTaskGenerator extends AbstractExpressionTaskGenerator<StoryExpressionTask> {

    public StoryExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet, EnumSet<MathStory> storySet) throws InvalidGeneratorException {
        super(minNumber, maxNumber, operationSet);
        this.storySet = new ArrayList<>(storySet);
        if (storySet.isEmpty()) {
            throw new InvalidGeneratorException("Story set must not be empty");
        }
    }

    public StoryExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) {
        super(minNumber, maxNumber, operationSet);
        this.storySet = new ArrayList<>(EnumSet.allOf(MathStory.class));
    }

    private MathStory getRandomStory() {
        Random random = new Random();
        return storySet.get(random.nextInt(storySet.size()));
    }

    @Override
    public StoryExpressionTask generateUnvalidated() {
        return new StoryExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber(), getRandomStory());
    }

    private final ArrayList<MathStory> storySet;
}
