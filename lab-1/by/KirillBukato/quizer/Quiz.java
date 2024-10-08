package by.KirillBukato.quizer;

import by.KirillBukato.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskLeft = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if(isLastAnswerValid && taskLeft != 0) {
            currentTask = generator.generate();
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        if (result == Result.INCORRECT_INPUT) {
            isLastAnswerValid = false;
            incorrectInputNumber++;
        } else {
            taskLeft--;
            isLastAnswerValid = true;
            if (result == Result.OK) correctAnswerNumber++;
            else if (result == Result.WRONG) wrongAnswerNumber++;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return taskLeft == 0;
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
        return wrongAnswerNumber;
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
    double getMark() {
        if(!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished, you can't get the result yet.");
        }
        if (getCorrectAnswerNumber() + getWrongAnswerNumber() == 0) return 1;
        return ((double) getCorrectAnswerNumber()) / (getCorrectAnswerNumber() + getWrongAnswerNumber());
    }

    private int correctAnswerNumber = 0;
    private int wrongAnswerNumber = 0;
    private int incorrectInputNumber = 0;
    private int taskLeft;
    private Task currentTask;
    private boolean isLastAnswerValid = true;
    private final TaskGenerator<? extends Task> generator;
}