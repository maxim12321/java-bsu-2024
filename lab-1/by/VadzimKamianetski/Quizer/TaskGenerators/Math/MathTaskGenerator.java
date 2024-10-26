package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import by.VadzimKamianetski.Quizer.TaskGenerators.Operation;
import by.VadzimKamianetski.Quizer.TaskGenerators.TaskGenerator;
import by.VadzimKamianetski.Quizer.Tasks.Math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    /**
     * @return                       рандомная допустимая операция
     * @param availableOperations    допустимые операции
     */
    Operation getByRandomOperation();

    String Brackets(Integer num);
    
    Integer divisionRandom(Integer num);

    Integer Random();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return 0;
    }

    public int getMinNumber();

    public int getMaxNumber();
}