package by.VadzimKamianetski.Quizer;

import by.VadzimKamianetski.Quizer.TaskGenerators.GeometryTaskGenerator;
import by.VadzimKamianetski.Quizer.TaskGenerators.GroupTaskGenerator;
import by.VadzimKamianetski.Quizer.TaskGenerators.PoolTaskGenerator;
import by.VadzimKamianetski.Quizer.TaskGenerators.Math.EquationTaskGenerator;
import by.VadzimKamianetski.Quizer.TaskGenerators.Math.ExpressionTaskGenerator;
import by.VadzimKamianetski.Quizer.TaskGenerators.Math.MathTaskGenerator;
import by.VadzimKamianetski.Quizer.Tasks.TextTask;
import by.VadzimKamianetski.Quizer.Tasks.Math.EquationTask;
import by.VadzimKamianetski.Quizer.exceptions.QuizNotFinishedException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
        public static void main(String[] args) throws QuizNotFinishedException {
            Map<String, Quiz> quizMap = getQuizMap();
            System.out.println("Список тестов:");
            quizMap.keySet().forEach(System.out::println);
    
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название теста...");
            String testName = scanner.nextLine();
            while (!quizMap.containsKey(testName)) {
                System.out.println("Такого теста не существует. Введите название теста...");
                testName = scanner.nextLine();
            }
            Quiz quiz = quizMap.get(testName);
    
            System.out.println("Начинаем тест.");
            while (!quiz.isFinished()) {
                System.out.println(quiz.nextTask().getText());
                String answer = scanner.nextLine();
                Result result = quiz.provideAnswer(answer);
                System.out.println(switch (result) {
                    case OK -> "Ответ верный!";
                    case WRONG -> "Ответ неверный!";
                    case INCORRECT_INPUT -> "Ввод некорректный. Попробуйте ещё раз!";
                });
            }
            scanner.close();
            System.out.println("Ваша оценка: " + quiz.getMark());
            System.out.println("Количество правильных ответов: " + quiz.getCorrectAnswerNumber());
            System.out.println("Количество неправильных ответов: " + quiz.getWrongAnswerNumber());
        }

        /**
         * @return тесты в {@link Map}, где
         * ключ     - название теста {@link String}
         * значение - сам тест       {@link Quiz}
         */
        @SuppressWarnings("unchecked")
        public static Map<String, Quiz> getQuizMap() {
            HashMap<String, Quiz> mapchikMap = new HashMap<>();
        
             // Simple Expression Test
            EnumSet<MathTaskGenerator.Operation> av1 = EnumSet.of(
                MathTaskGenerator.Operation.GENERATESUM,
                MathTaskGenerator.Operation.GENERATEDIFFERENCE,
                MathTaskGenerator.Operation.GENERATEDIVISION,
                MathTaskGenerator.Operation.GENERATEMULTIPLICATION
            );
                ExpressionTaskGenerator generator1 = new ExpressionTaskGenerator(0, 5, av1);
                Quiz quiz1 = new Quiz(generator1, 5);
                mapchikMap.put("Simple Expression Test", quiz1);
            
        
             // Simple Equation Test
                EquationTaskGenerator generator2 = new EquationTaskGenerator(0, 5, av1);
                Quiz quiz2 = new Quiz(generator2, 5);
                mapchikMap.put("Simple Equation Test", quiz2);
            
        
             // Division By 0 Test
                EnumSet<MathTaskGenerator.Operation> av2 = EnumSet.of(
                    MathTaskGenerator.Operation.GENERATEDIVISION,
                    MathTaskGenerator.Operation.GENERATEMULTIPLICATION
                );
                EnumSet<MathTaskGenerator.Operation> av3 = EnumSet.of(
                MathTaskGenerator.Operation.GENERATEDIVISION
                );
                ExpressionTaskGenerator generator31 = new ExpressionTaskGenerator(0, 1, av3);
                EquationTaskGenerator generator32 = new EquationTaskGenerator(0, 1, av2);
                Quiz quiz3 = new Quiz(new GroupTaskGenerator(generator31, generator32), 5);
                mapchikMap.put("Division By 0 Test", quiz3);
            
        
             // Pull Text Test Duplicated
                TextTask task0 = new TextTask("Если в доме нет воды значит выпили", "жиды");
                TextTask task1 = new TextTask("Введи 1", "1");
                TextTask task2 = new TextTask("Введи 2", "2");
                TextTask task3 = new TextTask("Введи 3", "3");
                PoolTaskGenerator generator4 = new PoolTaskGenerator(true, task0, task1, task2, task3);
                Quiz quiz4 = new Quiz(generator4, 5);
                mapchikMap.put("Pull Text Test Duplicated", quiz4);
            
            
             // Pull Test no Dublicated 
                EquationTask teq = new EquationTask("60+9=?", "69");
                PoolTaskGenerator generator5 = new PoolTaskGenerator(false, teq, task0, task1, task2, task3);
                Quiz quiz5 = new Quiz(generator5, 5);
                mapchikMap.put("Pull Text Test no Duplicated", quiz5);
                
            // Group Task Test no Throw
                PoolTaskGenerator generatorthrow = new PoolTaskGenerator(false);
                GroupTaskGenerator generator6 = new GroupTaskGenerator(generator1, generator5, generatorthrow);
                Quiz quiz6 = new Quiz(generator6, 5);
                mapchikMap.put("Group Task Test no Throw", quiz6);
        
        
            // Group Task Test Throw
                GroupTaskGenerator generator7 = new GroupTaskGenerator(generatorthrow, generatorthrow, generatorthrow);
                Quiz quiz7 = new Quiz(generator7, 5);
                mapchikMap.put("Group Task Test Throw", quiz7);
        
            // Geometry Task Test
                GeometryTaskGenerator generator8 = new GeometryTaskGenerator(2, 8);
                Quiz quiz8 = new Quiz(generator8, 5);
                mapchikMap.put("Geometry Task Test", quiz8);

            return mapchikMap;
        
        }
    
}
