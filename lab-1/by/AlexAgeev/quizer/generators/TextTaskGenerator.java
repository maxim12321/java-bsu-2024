package by.AlexAgeev.quizer.generators;

import by.AlexAgeev.quizer.TaskGenerator;
import by.AlexAgeev.quizer.exceptions.MinIsBiggerThanMaxException;
import by.AlexAgeev.quizer.tasks.TextTask;
import by.AlexAgeev.quizer.Task;


import java.util.Random;

public class TextTaskGenerator  implements TaskGenerator<TextTask> {
    public TextTaskGenerator(int mi, int ma) {
        this.min = mi;
        this.max = ma;
    }

    public TextTask generate() {
        if (this.min> this.max) {
            throw new MinIsBiggerThanMaxException();
        }
        Random rand = new Random();
        int rand1 = rand.nextInt(max - min + 1) + min;
        int rand2 = rand.nextInt(max - min + 1) + min;
        String text = "В классе было " + rand1 + " мальчиков и " + rand2 + " девочек, однополые пары запрещены, сколько детей останется без пары?";
        String answer = String.valueOf(Math.abs(rand1 - rand2));
        return new TextTask(text, answer);
    };

    private final int min;
    private final int max;
}
