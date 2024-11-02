package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.tasks.math.AbstractMathTask;

public class VelocityTask extends AbstractMathTask {
    public VelocityTask(int velocity1, int velocity2, int distance) {
        super(
                generateTaskText(velocity1, velocity2, distance),
                generateAnswer(velocity1, velocity2, distance)
        );
    }

    private static String generateTaskText(int velocity1, int velocity2, int distance) {
        return "Из города А в город Б выехал автомобиль со скоростью " + velocity1 + "км/ч,\n" +
                "а из города Б ему навстречу выехал мопед со скоростью " + velocity2 + "км/ч.\n" +
                "Через сколько часов они встретятся, если расстояние между городами равно" + distance + "км?";
    }

    private static int generateAnswer(int velocity1, int velocity2, int distance) {
        if (velocity1 <= 0 || velocity2 <= 0 || distance <= 0)
            throw new IllegalArgumentException("Negative physical variables");
        if (distance % (velocity1 + velocity2) != 0)
            throw new IllegalArgumentException("Not integer division");

        return distance / (velocity1 + velocity2);
    }

}

