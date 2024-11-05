package by.mmaxemm.quizer;

import by.mmaxemm.quizer.exceptions.QuizNotFinishedException;
import by.mmaxemm.quizer.exceptions.TaskGenerationException;

import java.util.Map;

/**
 * Class, который описывает один тест
 */
public class Quiz {

    int taskCount;
    int correctAnswerNumber;
    int wrongAnswerNumber;
    int incorrectInputNumber;

    TaskGenerator generator;
    boolean isIncorrectInput;
    Task currentTask;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        this.correctAnswerNumber = 0;
        isIncorrectInput = false;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if(isFinished()) {
            return null;
        }
        try {
            if(!isIncorrectInput || currentTask == null) {
                this.currentTask = generator.generate();
            }
        } catch (TaskGenerationException e) {
            System.out.println(e.getMessage());
        }
        return this.currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        if(result == Result.OK) {
            isIncorrectInput = false;
            correctAnswerNumber++;
        } else if(result == Result.WRONG) {
            isIncorrectInput = false;
            wrongAnswerNumber++;
        } else {
            isIncorrectInput = true;
            incorrectInputNumber++;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return correctAnswerNumber + wrongAnswerNumber == taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return taskCount - correctAnswerNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() throws QuizNotFinishedException {
        if(!isFinished()) {
            throw new QuizNotFinishedException("Quiz isn't finished yet!");
        }
        return (double) correctAnswerNumber / taskCount;
    }
}