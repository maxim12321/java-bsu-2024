package by.dilik14.quizer.generators;

import by.dilik14.quizer.tasks.DeadLineTask;

import java.util.Random;

public class DeadLineTaskGenerator implements TaskGenerator<DeadLineTask> {
    /**
     * @param minNumber         минимальное число
     * @param maxNumber         максимальное число
     */
    public DeadLineTaskGenerator(
            int minNumber,
            int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.random = new Random();
    }

    /**
     * return задание типа {@link DeadLineTask}
     */
    public DeadLineTask generate() {
        return new DeadLineTask(Math.max(1, random.nextInt(minNumber, maxNumber)),
                Math.max(1, random.nextInt(minNumber, maxNumber)), random.nextInt(minNumber, maxNumber));
    }

    protected final int minNumber;
    protected final int maxNumber;
    private final Random random;
}
