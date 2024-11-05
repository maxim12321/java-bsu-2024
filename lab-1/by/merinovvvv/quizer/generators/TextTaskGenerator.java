package by.merinovvvv.quizer.generators;
import by.merinovvvv.quizer.TaskGenerator;
import by.merinovvvv.quizer.tasks.TextTask;

public class TextTaskGenerator implements TaskGenerator<TextTask> {

    private final String text;
    private final String answer;

    public TextTaskGenerator (
            String text,
            String answer
    ) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public TextTask generate() {
        return new TextTask(text, answer);
    }
}
