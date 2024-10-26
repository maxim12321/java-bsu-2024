package by.MikhailNaumovich.quizer.generators.math;

import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;
import by.MikhailNaumovich.quizer.tasks.math.EquationTask;
import by.MikhailNaumovich.quizer.tasks.math.MathEnum;

import java.util.Random;
import java.util.EnumSet;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {


    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathEnum> operations) throws InvalidGeneratorException{
        super(minNumber, maxNumber, operations);
    }

    public void validateGenerator() throws InvalidGeneratorException {
        super.validateGenerator();

        if(hasOnlyDivisionOrMultiplication() && minNumber == 0 && maxNumber == 0){
            throw new InvalidGeneratorException("No equals operation selected");
        }
    }

    @Override
    public EquationTask preValidationGenerator() {
        Random random = new Random();
        return new EquationTask(generateRandomNumber(), generateRandomOperation(), generateRandomNumber(), random.nextBoolean());
    }
}