package by.AlexAgeev.quizer.exceptions;

public class MinIsBiggerThanMaxException extends IllegalArgumentException {
    public MinIsBiggerThanMaxException() {
        super("Min number is bigger than max number");
    }
}
