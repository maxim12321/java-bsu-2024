package KlimovMikh.quizer;
import KlimovMikh.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private int taskCount;
    private final TaskGenerator<? extends Task> generator;
    private int CorrectAnswerNumber;
    private int IncorrectAnswerNumber;
    private int IncorrectInputNumber;
    private Task currentTask;
    private boolean respawn;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        this.respawn = false;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    public Task nextTask() {
        if (isFinished()) {
            return null;
        }
        if (!respawn) {
            try {
                this.currentTask = this.generator.generate();
            } catch (IllegalStateException e) {
                System.out.println("No more KlimovMikh.quizer.tasks in generator of quiz. Ending the quiz.");
                taskCount = 0;
                throw e;
            }
        } else {
            respawn = false;
        }
        --this.taskCount;
        return this.currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        if(result == Result.OK) {
            CorrectAnswerNumber++;
        } else if (result == Result.WRONG) {
            IncorrectAnswerNumber++;
        } else {
            IncorrectInputNumber++;
            respawn = true;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return this.taskCount <= 0;
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return CorrectAnswerNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return IncorrectAnswerNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return IncorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    public double getMark() throws QuizNotFinishedException {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished yet");
        }
        return (double) CorrectAnswerNumber / (CorrectAnswerNumber + IncorrectAnswerNumber + IncorrectInputNumber);
    }
}
