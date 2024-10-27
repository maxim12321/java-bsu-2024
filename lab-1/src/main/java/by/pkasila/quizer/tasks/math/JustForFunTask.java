package by.pkasila.quizer.tasks.math;

import by.pkasila.quizer.common.MathOperation;

public class JustForFunTask extends AbstractExpressionTask {

    private final JustForFun fun;

    public JustForFunTask(int left, MathOperation operator, int right, JustForFun fun) {
        super(left, operator, right);
        this.fun = fun;
    }

    @Override
    public String getText() {
        String text = switch (fun) {
            case JETBRAINS -> "У разработчиков JetBrains было " + left + " фич. ";
            case GOOGLE -> "У разработчиков Google было " + left + " фич. ";
        };
        text += switch (operator) {
            case SUM -> switch (fun) {
                case JETBRAINS -> "Пришел злой PM JetBrains и поставил им разработать ещё " + right + " фич. ";
                case GOOGLE -> "Пришел злой PM Google и поставил им разработать ещё " + right + " фич. ";
            };
            case DIFFERENCE -> switch (fun) {
                case JETBRAINS, GOOGLE -> "Пришел добрый разраб из другого проекта и закрыл им " + right + " фич. ";
            };
            case MULTIPLICATION -> switch (fun) {
                case JETBRAINS ->
                        "Пришел злой PM JetBrains и разбил задачи на " + right + " более мелких. ";
                case GOOGLE ->
                        "Пришел злой PM Google и разбил задачи на " + right + " более мелких. ";
            };
            case DIVISION -> switch (fun) {
                case JETBRAINS ->
                        "Пришёл добрый PM JetBrains и сократил количество задач в " + right + " раза. ";
                case GOOGLE ->
                        "Пришёл добрый PM Google и сократил количество задач в " + right + " раза. ";
            };
        };
        text += switch (fun) {
            case JETBRAINS -> "Сколько теперь задач у разрабов JetBrains?";
            case GOOGLE -> "Сколько теперь задач у разрабов Google?";
        };
        if (operator == MathOperation.DIVISION) {
            text += " (ответ - десятичная дробь)";
        }
        return text;
    }
}
