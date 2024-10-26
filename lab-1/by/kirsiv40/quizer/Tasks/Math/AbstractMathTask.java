package by.kirsiv40.quizer.Tasks.Math;

import by.kirsiv40.quizer.Main.Result;

public abstract class AbstractMathTask implements MathTask{
    String text;
    int answer;

    public String getText() {
        return this.text;
    }

    public AbstractMathTask(String text, int answer) {
        this.text = text;
        this.answer = answer;
    }

    public Result validate(String answer) {
        answer = answer.trim();
        try { 
            int a = Integer.parseInt(answer); 
            if (a == this.answer) {
                return Result.OK;
            }
        } catch (NumberFormatException e) { 
            return Result.INCORRECT_INPUT;
        }
        return Result.WRONG;
    }
}
