package by.MatsveiZorka.quizer.tasks;

import by.MatsveiZorka.quizer.Result;

public abstract class AbstractMathTask extends AbstractTask implements MathTask {
    private final int Answer_;

    public AbstractMathTask(int answer, String text) {
        super(text);
        Answer_ = answer;
    }

    @Override
    public Result validate(String answer) {
        String refinedAnswer = prepareAnswer(answer);
        if (refinedAnswer == null)
            return Result.INCORRECT_INPUT;
        Integer parsed_answer = parseAnswer(refinedAnswer);
        if (parsed_answer == null)
            return  Result.INCORRECT_INPUT;
        return (Answer_ == parsed_answer) ? Result.OK : Result.WRONG;
    }
}
