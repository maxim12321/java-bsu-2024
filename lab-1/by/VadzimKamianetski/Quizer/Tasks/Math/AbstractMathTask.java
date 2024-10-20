package by.VadzimKamianetski.Quizer.Tasks.Math;

import by.VadzimKamianetski.Quizer.Result;

public class AbstractMathTask implements MathTask{

    String text;
    String answer;

    public AbstractMathTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (!answer.matches("-?\\d+")) {
            return Result.INCORRECT_INPUT;
        }
        return answer.equals(this.answer) ? Result.OK : Result.WRONG;
    }
    
}
