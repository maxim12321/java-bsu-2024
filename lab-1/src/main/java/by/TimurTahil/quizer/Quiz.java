package by.TimurTahil.quizer;

import by.TimurTahil.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
public class Quiz {

    private final TaskGenerator generator;
    private final int taskCount;
    private int tasksLeft;
    private Task currentTask;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private int incorrectInputs = 0;
    private Boolean PreviousAnswerHadIncorrectInput = false;


    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        this.tasksLeft = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (!PreviousAnswerHadIncorrectInput && !isFinished()) {
            currentTask = generator.generate();
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (currentTask == null) {
            throw new RuntimeException("null task");
        }
        Result result = currentTask.validate(answer);
        switch (result) {
            case OK:
                ++correctAnswers;
                --this.tasksLeft;
                PreviousAnswerHadIncorrectInput = false;
                break;
            case WRONG:
                ++wrongAnswers;
                --this.tasksLeft;
                PreviousAnswerHadIncorrectInput = false;
                break;
            case INCORRECT_INPUT:
                ++incorrectInputs;
                PreviousAnswerHadIncorrectInput = true;
                break;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return tasksLeft == 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswers;
    }

    /**
     * @return количество неправильных ответов{
     * return tasksLeft == 0;
     * }
     */
    int getWrongAnswerNumber() {
        return wrongAnswers;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInputs;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     * Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException();
        }
        return Math.round((double) (correctAnswers) / taskCount * 10);
    }
}
