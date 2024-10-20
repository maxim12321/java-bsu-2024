package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import by.VadzimKamianetski.Quizer.TaskGenerators.TaskGenerator;
import by.VadzimKamianetski.Quizer.Tasks.Math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    enum Operation {
        GENERATESUM, // разрешить генерацию с оператором +
        GENERATEDIFFERENCE, // разрешить генерацию с оператором -
        GENERATEMULTIPLICATION, // разрешить генерацию с оператором *
        GENERATEDIVISION // разрешить генерацию с оператором /
    }
    /**
     * @return                       рандомная допустимая операция
     * @param availableOperations    допустимые операции
     */
    Operation getByRandomOperation();
    
    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return 0;
    }

    public int getMinNumber();

    public int getMaxNumber();
}