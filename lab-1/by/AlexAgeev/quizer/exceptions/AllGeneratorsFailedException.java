package by.AlexAgeev.quizer.exceptions;

public class AllGeneratorsFailedException extends RuntimeException {
    public AllGeneratorsFailedException() {
        super("All generators failed(");
    }
}
