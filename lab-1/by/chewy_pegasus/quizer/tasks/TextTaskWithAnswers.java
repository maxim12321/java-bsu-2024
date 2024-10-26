package by.chewy_pegasus.quizer.tasks;

import by.chewy_pegasus.quizer.Result;
import by.chewy_pegasus.quizer.tasks.text.TextTask;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TextTaskWithAnswers extends TextTask {
    public ArrayList<String> answers;

    public TextTaskWithAnswers (String text, String answer, ArrayList<String> answers) {
        super(text, answer);
        this.answers = new ArrayList<>(answers);
    }

    public StringBuilder ShowAnswers() {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < answers.size(); ++i) {
            result.append((i + 1)).append(") ").append(answers.get(i)).append("\n");
        }
        return result;
    }

    @Override
    public Result validate(String answer) {
        try {
            int i = Integer.parseInt(answer);
            --i;
            if (i >= this.answers.size() || i < 0) {
                return Result.INCORRECT_INPUT;
            }
            answer = this.answers.get(i);
        } catch (NumberFormatException e) {
            //
        }
        boolean u = false;
        for (int i = 0; i < answers.size(); ++i) {
            u = u || (answer.equals(answers.get(i)));
        }
        if (!u) {
            return Result.INCORRECT_INPUT;
        }
        return answer.equals(this.correctAnswer) ? Result.OK : Result.WRONG;
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder(this.text);
        text.append(ShowAnswers().toString());
        return text.toString();
    }
}
