package by.TimurTahil.quizer.generators;

import by.TimurTahil.quizer.Task;
import by.TimurTahil.quizer.TaskGenerator;
import by.TimurTahil.quizer.tasks.TextTask;

import java.util.Random;

public class TextTaskGenerator implements TaskGenerator {

    private final int minNumber;
    private final int maxNumber;

    public TextTaskGenerator(int minNumber, int maxNumber) {
        if (minNumber >= maxNumber) {
            throw new IllegalArgumentException();
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public Task generate() {
        Random rand = new Random();
        int random1 = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        int random2 = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        String text = "У студента ШАДа на этой неделе не закрыто " + random1 + " тасок, он планирует закрыть " + random2 + " из них. Вопрос: сколько заданий останется у студента шада после рабочей недели?";
        String answer = String.valueOf(Math.abs(random1 - random2 / 2));  // эмпирическое значение
        return new TextTask(text, answer);
    }
}
