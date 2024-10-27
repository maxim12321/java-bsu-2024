/**
 * Class, который описывает один тест
 */
package by.MikitaShutro;
class Quiz {
    private final TaskGenerator gen_;
    private final int TaskCount_;
    private int WrAnsNum_ = 0;
    private int RAnsNum_ = 0;
    private int IINum_ = 0;
    private Task currentTask_;
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        TaskCount_ = taskCount;
        gen_ = generator;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (isFinished()) {
            throw new RuntimeException("Тест окончен.");
        } else {
            if (currentTask_ == null) {
                currentTask_ = gen_.generate();
            }
            return currentTask_;
        }
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result Check = currentTask_.validate(answer);
        if (Check == Result.INCORRECT_INPUT) {
            IINum_++;
            return Result.INCORRECT_INPUT;
        }
        if (Check == Result.OK) {
            RAnsNum_++;
            currentTask_ = null;
        }
        if (Check == Result.WRONG) {
            WrAnsNum_++;
            currentTask_ = null;
        }
        return Check;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return RAnsNum_ + WrAnsNum_ == TaskCount_;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return RAnsNum_;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return WrAnsNum_;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return IINum_;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (isFinished()) {
            return ((double) RAnsNum_) / ((double) RAnsNum_ + (double) WrAnsNum_);
        } else {
            throw new RuntimeException("Тест ещё не окончен.");
        }
    }
}