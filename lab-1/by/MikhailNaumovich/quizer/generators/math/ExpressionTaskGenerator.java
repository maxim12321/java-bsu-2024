package by.MikhailNaumovich.quizer.generators.math;

import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;
import by.MikhailNaumovich.quizer.tasks.math.ExpressionTask;
import by.MikhailNaumovich.quizer.tasks.math.MathEnum;


import java.util.EnumSet;


public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    
    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathEnum> operations) throws InvalidGeneratorException{
        super(minNumber, maxNumber, operations);
    }

    public void validateGenerator() throws InvalidGeneratorException {
        super.validateGenerator();

        if(hasOnlyDivision() && minNumber == 0 && maxNumber == 0){
            throw new InvalidGeneratorException("No equals operation selected");
        }
    }

    @Override
    public ExpressionTask preValidationGenerator() {
        return new ExpressionTask(generateRandomNumber(), generateRandomOperation(), generateRandomNumber());
    }
}

