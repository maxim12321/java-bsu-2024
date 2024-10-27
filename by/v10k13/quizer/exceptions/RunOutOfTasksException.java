package by.v10k13.quizer.exceptions;

public class RunOutOfTasksException extends RuntimeException {
    public RunOutOfTasksException() {
        super("No tasks left.");
    }
}
