package by.pkasila.quizer.generators;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.generators.math.AbstractExpressionTaskGenerator;
import by.pkasila.quizer.tasks.math.JustForFun;
import by.pkasila.quizer.tasks.math.JustForFunTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class JustForFunTaskGenerator extends AbstractExpressionTaskGenerator<JustForFunTask> {

    private final ArrayList<JustForFun> storySet;

    public JustForFunTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet, EnumSet<JustForFun> storySet) throws BadGeneratorException {
        super(minNumber, maxNumber, operationSet);
        this.storySet = new ArrayList<>(storySet);
        if (storySet.isEmpty()) {
            throw new BadGeneratorException("fun must not be empty");
        }
    }

    public JustForFunTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) {
        super(minNumber, maxNumber, operationSet);
        this.storySet = new ArrayList<>(EnumSet.allOf(JustForFun.class));
    }

    private JustForFun getRandomStory() {
        Random random = new Random();
        return storySet.get(random.nextInt(storySet.size()));
    }

    @Override
    public JustForFunTask generateUnvalidated() {
        return new JustForFunTask(getRandomNumber(), getRandomOperation(), getRandomNumber(), getRandomStory());
    }
}
