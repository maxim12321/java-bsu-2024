package by.Alesia.quizer.exceptions;

import java.util.concurrent.CompletionException;

public class GenerationError extends RuntimeException {
    public GenerationError(String str) {
        super(str);
    }
}