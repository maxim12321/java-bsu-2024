package by.VadzimKamianetski.Quizer.TaskGenerators;

import java.util.Random;

import by.VadzimKamianetski.Quizer.Tasks.Math.GeometryTask;

public class GeometryTaskGenerator implements TaskGenerator<GeometryTask> {
    
    protected int minNumber;
    protected int maxNumber;

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
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
        Random rand = new Random();
        Integer firstNumber = rand.nextInt(minNumber, maxNumber + 1);
        Integer secondNumber = rand.nextInt(minNumber, maxNumber + 1);
        Integer answer = 42;
        String text = "Something went wrong";
        switch (rand.nextInt(4)) {
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
                Integer thirdNumber = rand.nextInt(minNumber, maxNumber + 1);      
                text = "Найдите удвоенную площадь трапеции с основаниями " + firstNumber.toString() + " и " + secondNumber.toString() + ". Высота равна " + thirdNumber.toString();
                answer = (firstNumber + secondNumber) * thirdNumber;
                break;
            default:
        }
        return new GeometryTask(text, answer.toString());
    }
    
}
