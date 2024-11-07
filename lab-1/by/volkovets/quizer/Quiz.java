package by.volkovets.quizer;

import by.volkovets.quizer.exceptions.AllGeneratorsAreDefective;
import by.volkovets.quizer.exceptions.QuizNotFinishedException;
import by.volkovets.quizer.generators.PoolTaskGenerator;

/**
 * Class, который описывает один тест
 */
class Quiz {

    private int CorrectAnswerNumber = 0;
    private int WrongAnswerNumber = 0;
    private int IncorrectInputNumber = 0;
    private boolean IncorrectInput = false;
    private int left;
    private Task currentTask;
    private final TaskGenerator<? extends Task> generator;
    private Boolean TaskCountIsBiggerThanTasksInPool = false;

    Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.left = taskCount;

        if (generator instanceof PoolTaskGenerator) {
            Boolean check1 = ((PoolTaskGenerator) generator).DuplicatesAvailable();
            Boolean check2 = ((PoolTaskGenerator) generator).TasksInPool() < taskCount;

            if (!check1 && check2) {
                TaskCountIsBiggerThanTasksInPool = true;
                this.left = ((PoolTaskGenerator) generator).TasksInPool();
            }
        }
    }

    Task nextTask() {
        if (!IncorrectInput && this.left != 0) {
            try {
                currentTask = generator.generate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new AllGeneratorsAreDefective();
            }
        }
        return currentTask;
    }

    Result provideAnswer(String answer) {
        Result res = this.currentTask.validate(answer);
        if (res == Result.OK) {
            this.left--;
            this.IncorrectInput = false;
            this.CorrectAnswerNumber++;
        } else if (res == Result.WRONG) {
            this.left--;
            this.IncorrectInput = false;
            this.WrongAnswerNumber++;
        } else {
            this.IncorrectInputNumber++;
            this.IncorrectInput = true;
        }
        return res;
    }

    boolean isFinished() {
        return this.left == 0;
    }

    int getCorrectAnswerNumber() {
        return this.CorrectAnswerNumber;
    }

    int getWrongAnswerNumber() {
        return this.WrongAnswerNumber;
    }

    int getIncorrectInputNumber() {
        return this.IncorrectInputNumber;
    }

    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException();
        }
        if (TaskCountIsBiggerThanTasksInPool) {
            System.out
                    .println("Amount of tasks in your Pool is less then amount you want to do \nSo this is the end");
        }
        double tmp = ((double) this.CorrectAnswerNumber / (this.CorrectAnswerNumber + this.WrongAnswerNumber));
        int mark = (int)(tmp * 100.0);
        return mark / 10.0;
    }
}