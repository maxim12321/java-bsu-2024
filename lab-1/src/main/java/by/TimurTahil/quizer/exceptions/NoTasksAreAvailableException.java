package by.TimurTahil.quizer.exceptions;

public class NoTasksAreAvailableException extends IllegalArgumentException {
    public NoTasksAreAvailableException() {
        super("No tasks are available");
    }
}
