package by.VadzimKamianetski.Quizer.TaskGenerators;

import by.VadzimKamianetski.Quizer.Tasks.Math.GeometryTask;

public class GeometryTaskGenerator implements TaskGenerator<GeometryTask> {
    
    protected int minNumber;
    protected int maxNumber;

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param availableOperations    разрешённые операции
     */
    public GeometryTaskGenerator(int minNumber, int maxNumber) {
        if (minNumber >= maxNumber || minNumber <= 0) {
            throw new IllegalArgumentException("Minimum cannot be greater than maximum");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }
    
    @Override
    public GeometryTask generate() {
        Integer firstNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        Integer secondNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        Integer answer = 42;
        String text = "Something went wrong";
        switch ((int) (Math.random() * 4)) {
            case 0:
                text = "Найдите площадь прямоугольника со сторонами " + firstNumber.toString() + " и " + secondNumber.toString();
                answer = firstNumber * secondNumber;
                break;
            case 1:
                text = "Найдите удвоенную площадь треугольника с основанием " + firstNumber.toString() + " и высотой " + secondNumber.toString();
                answer = firstNumber * secondNumber;
                break;
            case 2:
                text = "Найдите площадь круга радиуса " + firstNumber.toString() + ". Pi = 3";
                answer = firstNumber * firstNumber * 3;
                break;        
            case 3:
                Integer thirdNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);      
                text = "Найдите удвоенную площадь трапеции с основаниями " + firstNumber.toString() + " и " + secondNumber.toString() + ". Высота равна " + thirdNumber.toString();
                answer = (firstNumber + secondNumber) * thirdNumber;
                break;
            default:
        }
        return new GeometryTask(text, answer.toString());
    }
    
}
