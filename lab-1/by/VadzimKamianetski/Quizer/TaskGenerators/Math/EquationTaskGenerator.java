package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.EnumSet;

import by.VadzimKamianetski.Quizer.Tasks.Math.EquationTask;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    enum Position {
        LEFT,
        RIGHT
    }

    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> availableOperations) {
        super(minNumber, maxNumber, availableOperations);
    }

    @Override
    public EquationTask generate() {
        Integer firstNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        Integer secondNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        MathTaskGenerator.Operation operation = getByRandomOperation();
        Integer answer = 42;
        String text = "Something went wrong";
        Position position = switch((int) Math.random() * 2) {
            case 0 -> Position.LEFT;        
            default -> Position.RIGHT;  
        }; 
        switch (operation) {
            case MathTaskGenerator.Operation.GENERATESUM:
                answer = -firstNumber + secondNumber; 
                if (position == Position.LEFT) {
                    text = "x" + "+" + firstNumber.toString() + "=" + secondNumber.toString();
                } else {
                    text = firstNumber.toString() + "+" + "x" + "=" + secondNumber.toString();
                }
                break;
            case MathTaskGenerator.Operation.GENERATEDIFFERENCE: 
                if (position == Position.LEFT) {
                    answer = firstNumber + secondNumber;
                    text = "x" + "-" + firstNumber.toString() + "=" + secondNumber.toString();
                } else {
                    answer = firstNumber - secondNumber;
                    text = firstNumber.toString() + "-" + "x" + "=" + secondNumber.toString();
                }
                break;
            case MathTaskGenerator.Operation.GENERATEMULTIPLICATION:
                while (firstNumber == 0) {
                    firstNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
                }
                answer = secondNumber / firstNumber;
                if (position == Position.LEFT) {
                    text = "x" + "*" + firstNumber.toString() + "=" + secondNumber.toString();
                } else {
                    text = firstNumber.toString() + "*" + "x" + "=" + secondNumber.toString();
                }
                break;
            case MathTaskGenerator.Operation.GENERATEDIVISION: 
                while (firstNumber == 0) {
                    firstNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
                }
                if (position == Position.LEFT) {
                    answer = firstNumber * secondNumber;
                    text = "x" + "/" + firstNumber.toString() + "=" + secondNumber.toString();
                } else {
                    while (secondNumber == 0) {
                        secondNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
                    }
                    answer = firstNumber / secondNumber;
                    text = firstNumber.toString() + "/" + "x" + "=" + secondNumber.toString();
                }
                break;         
            default:
        }
        return new EquationTask(text, answer.toString());
    }
    
}
// /**
    //  * return задание типа {@link EquationTask}
    //  */
    // public EquationTask generate() {
    //     int firstNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //     int secondNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //     .Position position = EquationTask.Position.LEFT;
    //     Boolean flag = false;
    //     Character operation = '+';
    //     if (maxNumber == minNumber && minNumber == 0) {
    //         generateDivision = false;
    //     }
    //     while (!flag) {
    //         switch ((int) Math.random() * (8)) {
    //             case 0: 
    //                 flag = generateSum;
    //                 position = EquationTask.Position.LEFT;
    //                 operation = '+';
    //                 break;
    //             case 1: 
    //                 flag = generateDifference;
    //                 position = EquationTask.Position.LEFT;
    //                 operation = '-';
    //                 break;
    //             case 2: 
    //                 flag = generateMultiplication;
    //                 position = EquationTask.Position.LEFT;
    //                 operation = '*';
    //                 while (generateMultiplication && firstNumber == 0) {
    //                     firstNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //                 }
    //                 break;
    //             case 3: 
    //                 flag = generateDivision;
    //                 position = EquationTask.Position.LEFT;
    //                 operation = '/';
    //                 while (generateDivision && secondNumber == 0) {
    //                    secondNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //                 }
    //                 break;
    //             case 4: 
    //                 flag = generateSum;
    //                 position = EquationTask.Position.RIGHT;
    //                 operation = '+';
    //                 break;
    //             case 5: 
    //                 flag = generateDifference;
    //                 position = EquationTask.Position.RIGHT;
    //                 operation = '-';
    //                 break;
    //             case 6: 
    //                 flag = generateMultiplication;
    //                 position = EquationTask.Position.RIGHT;
    //                 operation = '*';
    //                 while (generateMultiplication && firstNumber == 0) {
    //                     firstNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //                 }
    //                 break;
    //             case 7: 
    //                 flag = generateDivision;
    //                 position = EquationTask.Position.RIGHT;
    //                 operation = '/';
    //                 while (generateDivision && firstNumber == 0) {
    //                    firstNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //                 }
    //                 while (generateDivision && secondNumber == 0) {
    //                     secondNumber = (int) Math.random() * (maxNumber - minNumber + 1) + minNumber;
    //                  }
    //                 break;        
    //             default:
    //         }
    //     }
    //     return new EquationTask(operation, firstNumber, secondNumber, position);
    // }