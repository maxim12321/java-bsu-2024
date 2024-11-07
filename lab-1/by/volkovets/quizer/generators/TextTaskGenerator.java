package by.volkovets.quizer.generators;

import by.volkovets.quizer.Task;
import by.volkovets.quizer.TaskGenerator;
import by.volkovets.quizer.exceptions.MinIsBiggerThanMaxException;
import by.volkovets.quizer.tasks.TextTask;

import java.util.Random;

public class TextTaskGenerator implements TaskGenerator<Task> {

    private int min;
    private int max;

    public TextTaskGenerator(int Min, int Max) {
        this.min = Min;
        this.max = Max;
    }

    @Override
    public TextTask generate() throws MinIsBiggerThanMaxException {
        if (this.min > this.max) {
            throw new MinIsBiggerThanMaxException();
        }

        Random random = new Random();
        int first = random.nextInt(this.max - this.min + 1) + this.min;
        int second = random.nextInt(first - this.min + 1) + this.min;
        int text_ind = random.nextInt(3);
        TextTask task = new TextTask(text_ind, first, second);
        return task;
    }
}
