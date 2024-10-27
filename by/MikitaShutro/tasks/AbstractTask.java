package by.MikitaShutro.tasks;
import by.MikitaShutro.Task;

public abstract class AbstractTask implements Task {
    private String Statement_ = "";
    public AbstractTask(String statement) {
        Statement_ = statement;
    }
    @Override
    public String getText (){
        return Statement_;
    }
}
