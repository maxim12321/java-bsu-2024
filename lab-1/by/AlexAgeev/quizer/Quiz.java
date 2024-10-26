package by.AlexAgeev.quizer;

import by.AlexAgeev.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        this.tasks_left = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        /*if (!isFinished()) {
            throw new RuntimeException("This task isn't finished, you can not take new task!");
        }*/
        if (!isFinished() && !PrevAnswerWasIncorrectInput) {
            currTask = generator.generate();
        }
        return currTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (isFinished()) {
            throw new RuntimeException("Quiz is finished");
        }
        if (currTask == null) {
            throw new RuntimeException("There are no task to provide answer");
        }
        Result result = currTask.validate(answer);
        switch (result) {
            case OK:
                correctAnswer++;
                this.tasks_left--;
                PrevAnswerWasIncorrectInput = false;
                break;
            case WRONG:
                wrongAnswer++;
                this.tasks_left--;
                PrevAnswerWasIncorrectInput = false;
                break;
            case INCORRECT_INPUT:
                incorrectInput++;
                PrevAnswerWasIncorrectInput = true;
                break;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return tasks_left == 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswer;
    }

    /**
     * @return количество неправильных ответов{
        return tasks_left == 0;
    }
     */
    int getWrongAnswerNumber() {
        return wrongAnswer;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInput;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException();
        }
        return Math.round((double) (correctAnswer) / taskCount * 10);
    }

    private final int taskCount;
    private int tasks_left;
    private Boolean PrevAnswerWasIncorrectInput = false;
    private final TaskGenerator<Task> generator;
    private int correctAnswer = 0;
    private int wrongAnswer = 0;
    private int incorrectInput = 0;
    private Task currTask;
}