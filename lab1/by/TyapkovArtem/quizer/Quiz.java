package lab1.by.TyapkovArtem.quizer;

import lab1.by.TyapkovArtem.quizer.exceptions.QuizIsFinishedException;
import lab1.by.TyapkovArtem.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final int taskCount_;
    private final TaskGenerator generator_;
    private int answersGot_ = 0;
    private  int correctAnswersGot_ = 0;
    private int incorrectInputs_ = 0;
    private Task currentTask_;
    private boolean incorflag = false;


    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator generator, int taskCount) {
        generator_ = generator;
        taskCount_ = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    public Task nextTask() {
        if (!incorflag) currentTask_ = generator_.generate();
        return currentTask_;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) {
        if (isFinished()) throw new QuizIsFinishedException("The quiz is already finished!");
        Result a = currentTask_.validate(answer);
        if (a == Result.INCORRECT_INPUT) {
            incorflag = true;
            incorrectInputs_++;
        } else {
            incorflag = false;
            answersGot_++;
            if (a == Result.OK) {
                correctAnswersGot_++;
            }
        }
        return a;
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return (answersGot_ == taskCount_);
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return correctAnswersGot_;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return answersGot_ - correctAnswersGot_;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return incorrectInputs_;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    public double getMark() {
        if (isFinished()) {
            return (double)(correctAnswersGot_ / taskCount_);
        } else throw new QuizNotFinishedException("You haven't finish your quiz!");
    }
}