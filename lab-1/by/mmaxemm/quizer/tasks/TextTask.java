package by.mmaxemm.quizer.tasks;

import by.mmaxemm.quizer.Result;
import by.mmaxemm.quizer.Task;
public class TextTask implements Task {
    String text;
    String answer;

    public TextTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        return answer.trim().toLowerCase() == this.answer.trim().toLowerCase()
                ? Result.OK : Result.WRONG;
    }
}
