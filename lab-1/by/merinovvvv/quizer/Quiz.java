package by.merinovvvv.quizer;

import by.merinovvvv.quizer.exceptions.QuizFinishedException;
import by.merinovvvv.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
class Quiz {

    private final TaskGenerator<? extends Task> generator;
    private final int taskCount;
    private int incorrectInputs;
    private int correctAnswers;
    private int wrongAnswers;
    private Task currentTask;
    private int currentTaskIndex;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<? extends Task> generator, int taskCount) {

        if (generator == null) {
            throw new IllegalArgumentException("Generator cannot be null");
        }

        if (taskCount <= 0) {
            throw new IllegalArgumentException("Task count must be positive");
        }

        this.generator = generator;
        this.taskCount = taskCount;
        this.incorrectInputs = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.currentTask = null;
        this.currentTaskIndex = 0;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    Task nextTask() {
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished");
        }
        if (currentTask == null || currentTaskIndex > 0) {
            try {
                currentTask = generator.generate();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            //currentTaskIndex++;
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (isFinished()) {
            throw new IllegalArgumentException("Quiz is finished");
        }
        if (currentTask == null) {
            throw new IllegalArgumentException("No task to answer");
        }
        try {
            Result result = currentTask.validate(answer);
            switch (result) {
                case OK:
                    correctAnswers++;
                    currentTaskIndex++;
                    break;
                case WRONG:
                    wrongAnswers++;
                    currentTaskIndex++;
                    break;
                case INCORRECT_INPUT:
                    incorrectInputs++;
                    break;
            }
            return result;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return currentTaskIndex >= taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswers;
    }

    /**
     * @return количество неправильных ответов
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
     *         Оценка выставляется только в конце!
     */
    double getMark() throws QuizNotFinishedException {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished yet");
        }
        return (double) correctAnswers / taskCount;
    }
}