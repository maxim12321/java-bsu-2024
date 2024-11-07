package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Result;

public class TextExpressionMathTask extends ExpressionMathTask{

    public TextExpressionMathTask(ExpressionMathTask task) {
        super(task.num1, task.operation, task.num2);
    }

    public int calculate() {
        return super.calculate();
    }

    public String getText() {
        if (operation.equals(Operation.SUM)) {
            return "В домашнем задании по джаве Даник реализовал " + num1 + " классов, а Вера - "+ num2 + ". Сколько всего классов они реализовали?";
        } else if (operation.equals(Operation.DIFF)) {
            return "На прошлой неделе на улице было " + num1 + " градусов, но она опустилась на "+ num2 + " градусов. Какая температура сейчас?";
        } else if (operation.equals(Operation.DIV)) {
            return "В каждом домашнем задании было " + num2 +
                    " задач. Аня забила на учебу на какое-то время и теперь у нее долг из " + num1 +
                    " задач. Сколько раз Аня не выполнила домашнее задание?";
        } else {
            return "Летом Леня решил "+ num1 + " задач по математике, а Игорь - в " + num2 +
                    " раз больше. Сколько задач решил Игорь?";
        }
    }

    public Result validate(String answer) {
        return super.validate(answer);
    }
}
