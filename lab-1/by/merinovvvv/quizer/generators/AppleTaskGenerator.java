package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.TaskGenerator;
import by.merinovvvv.quizer.tasks.AppleTask;

public class AppleTaskGenerator implements TaskGenerator<AppleTask> {

    private final int minNumber;
    private final int maxNumber;

    public AppleTaskGenerator (
            int minNumber,
            int maxNumber
    ) {
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException("minNumber can't be greater than maxNumber.");
        }

        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    @Override
    public AppleTask generate() {
        return new AppleTask(minNumber, maxNumber);
    }
}
