package by.v10k13.quizer;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final int TotalTasksCount_;
    private int RemainingTasks_;
    private int CorrectAnswers_      = 0;
    private int InputMistakes_       = 0;
    private boolean InputMistakeFlag_= false;

    private final TaskGenerator Generator_;
    private Task LastTask_           = null;

    private void GenerateNextTask_() {
        LastTask_ = Generator_.generate();
        RemainingTasks_--;
    }

    private void EvaluateTask_(Task.Result result) {
        if (result == Task.Result.OK)
            CorrectAnswers_++;
    }

    private void MarkTaskAsPassed_(Task.Result result) {
        EvaluateTask_(result);
        LastTask_ = null;
    }

    private boolean InputMistakeOccurred_() {
        return InputMistakeFlag_;
    }

    private void MarkMistakeAsPassed_() {
        InputMistakeFlag_ = false;
    }

    private void MarkMistake_() {
        InputMistakes_++;
        InputMistakeFlag_ = true;
    }


    private Task.Result UpdateTaskForResultOf_(Task.Result result) {
        if (result == Task.Result.INCORRECT_INPUT)
            MarkMistake_();
        else
            MarkTaskAsPassed_(result);
        return result;
    }



    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator generator, int taskCount) {
        Generator_ = generator;
        TotalTasksCount_ = taskCount;
        RemainingTasks_ = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     *
     * @throws RuntimeException если тест уже завершен
     */
    public Task nextTask() {
        if (isFinished())
            throw new RuntimeException("No tasks left on this quiz.");

        if (InputMistakeOccurred_())
            MarkMistakeAsPassed_();
        else
            GenerateNextTask_();

        return LastTask_;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Task.Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     *
     * @throws RuntimeException если не было получено задание с помощью nextTask
     */
    public Task.Result provideAnswer(String answer) {
        if (LastTask_ == null)
            throw new RuntimeException("No tusk where provided.");

        return UpdateTaskForResultOf_(LastTask_.validate(answer));
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return RemainingTasks_ == 0;
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return CorrectAnswers_;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return (TotalTasksCount_ - RemainingTasks_) - CorrectAnswers_;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return InputMistakes_;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     *
     * @throws RuntimeException если тест не завершен на момент вызова функции.
     */
    public double getMark() {
        if (!isFinished())
            throw new RuntimeException("Test not finished yet.");

        double res = CorrectAnswers_;
        res /= TotalTasksCount_;

        return res;
    }
}