package by.MatsveiZorka.quizer;

import static by.MatsveiZorka.quizer.Result.WRONG;

public class Quiz {
    private final TaskGenerator generator_;
    private final int taskCount_; // количество задач
    private Task currentTask_; // номер текущей задачи с нуля
    private int counterOfTasks_ = -1; // счётчик сгенерированных задач
    private int counterOfCorrectTasks_ = 0; // счётчик верно решённых задач
    private int counterOfIncorrectInputs_ = 0; // счётчик некорректных вводов
    private int counterOfWrongAnswers_ = 0; // счётчик неверно решённых задач
    private boolean isOK = true;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator generator, int taskCount) {
        generator_ = generator;
        taskCount_ = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    public Task nextTask() {
        if (isFinished()) {
            throw new RuntimeException("The quiz is already finished.");
        }
        if (currentTask_ == null) {
            counterOfTasks_++;
            currentTask_ = generator_.generate();
        }
        return currentTask_;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) {
        Result res = currentTask_.validate(answer);
        switch (res) {
            case WRONG -> {
                counterOfWrongAnswers_++;
                currentTask_ = null;
            }
            case OK -> {
                counterOfCorrectTasks_++;
                currentTask_ = null;
            }
            case INCORRECT_INPUT -> {
                counterOfIncorrectInputs_++;
            }
        }
        return res;
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return (taskCount_ == counterOfCorrectTasks_);
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return counterOfCorrectTasks_;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return counterOfWrongAnswers_;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return counterOfIncorrectInputs_;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    public double getMark() {
        if (!isFinished()) {
            throw new RuntimeException("The quiz is not finished yet.");
        }
        return (1. * counterOfCorrectTasks_ / taskCount_);
    }
}