package by.v10k13.quizer.tasks;

public class TextTask extends AbstractTask {
    private final String Answer_;

    public TextTask(String text, String answer) {
        super(text);
        Answer_ = answer;
    }

    @Override
    public Result validate(String answer) {
        if (answer == null)
            return Result.INCORRECT_INPUT;
        return (answer.equals(Answer_) ? Result.OK : Result.WRONG);
    }
}
