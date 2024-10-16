package tasks;

import core.Result;
import core.Task;

public class EndOfPoolTask implements Task {
    @Override
    public String getText() {
        return "This is the end of the pool";
    }

    @Override
    public Result validate(String answer) {
        return Result.OK;
    }
}
