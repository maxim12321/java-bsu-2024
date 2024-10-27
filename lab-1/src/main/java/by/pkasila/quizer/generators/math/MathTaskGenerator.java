package by.pkasila.quizer.generators.math;

public interface MathTaskGenerator {
    int getMinNumber(); // получить минимальное число
    int getMaxNumber(); // получить максимальное число

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return this.getMaxNumber() - this.getMinNumber();
    }
}
