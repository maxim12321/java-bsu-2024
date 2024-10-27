package by.MikitaShutro.tasks;
import by.MikitaShutro.Result;

public abstract class MathAbstractTask extends AbstractTask {
    private Integer answer_;

    @Override
    public Result validate (String answer) {
        try {
            if (Integer.parseInt(answer) == answer_) {
                return Result.OK;
            } else {
                return Result.WRONG;
            }
        }
        catch(Exception ex) {
            return Result.INCORRECT_INPUT;
        }
    }

    public MathAbstractTask (String statement, Integer answer) {
        super(statement);
        answer_ = answer;
    }
}
