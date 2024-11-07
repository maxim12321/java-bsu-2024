package by.pkasila.quizer.common;

public interface MathTask extends Task {
    boolean isInvalid();

    double computeAnswer();
}
