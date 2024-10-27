package by.SanchukS.quizer;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        Quiz quiz = quizMap.get(getQuizName());
        while (quiz == null) {
            System.out.println("Please try again");
            quiz = quizMap.get(getQuizName());
        }

        try {
            System.out.println("Ready?");
            Thread.sleep(1000);
            System.out.println("Steady!");
            Thread.sleep(1000);
            System.out.println("Go!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        runQuiz(quiz);
        double mark = quiz.getMark();

        System.out.println("Mark: " + mark);
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        throw new UnsupportedOperationException("Not supported yet.");
        /**
         * return Map.of(
         *                 "Quiz1", new Quiz(),
         *                 "Quiz2", new Quiz(),
         *                 "Quiz3", new Quiz()
         *         )
         */
    }

    static String getQuizName() {
        String quizName;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter quiz name: ");
        quizName = scanner.nextLine();
        return quizName;
    }

    static void runQuiz(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        while (!quiz.isFinished()) {
            Task currentTask = quiz.nextTask();
            System.out.println(currentTask.getText());
            String answer = scanner.nextLine();
            System.out.println(quiz.provideAnswer(answer).name());
        }
    }
}
