package by.volkovets.quizer.exceptions;

public class MinIsBiggerThanMaxException extends IllegalArgumentException {
    public MinIsBiggerThanMaxException() {
        super("Wrong input. Min > Max");
    }
}
