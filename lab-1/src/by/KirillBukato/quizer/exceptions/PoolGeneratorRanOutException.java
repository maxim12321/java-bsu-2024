package by.KirillBukato.quizer.exceptions;

public class PoolGeneratorRanOutException extends RuntimeException {
    public PoolGeneratorRanOutException() {
        super("Pool Task Generator has no tasks left.");
    }
}
