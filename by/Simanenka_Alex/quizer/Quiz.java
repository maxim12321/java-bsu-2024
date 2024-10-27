package by.Simanenka_Alex.quizer;

import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        this.quizTaskCount = taskCount;
        this.generator = generator;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (!isPrevCorr) {
            return thisTask;
        }
        thisTask = generator.generate();
        isPrevCorr = true;
        return thisTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result res = thisTask.validate(answer);
        if (Result.INCORRECT_INPUT == res) {
            isPrevCorr = false;
            answerNum[2]++;
        }
        if (res == Result.OK) {
            answerNum[0]++;
            quizTaskCount--;
            isPrevCorr = true;
        }
        if (res == Result.WRONG) {
            answerNum[1]++;
            quizTaskCount--;
            isPrevCorr = true;
        }
        return res;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return quizTaskCount == 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return answerNum[0];
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return answerNum[1];
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return answerNum[2];
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Квиз ещё не закончен");
        }
        return ((double)getCorrectAnswerNumber() / ((double)getCorrectAnswerNumber()+(double)getWrongAnswerNumber()));
    }

    private int[] answerNum = {0, 0, 0};
    private int quizTaskCount;
    private TaskGenerator generator;
    private boolean isPrevCorr = true;
    private Task thisTask;
}