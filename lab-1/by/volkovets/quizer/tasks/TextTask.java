package by.volkovets.quizer.tasks;

import by.volkovets.quizer.Task;
import by.volkovets.quizer.Result;

public class TextTask implements Task {

    final private int first;
    final private String Answer;
    final private String Text;
    final private int second;

    public TextTask(int Text_Ind, int first, int second) {
        this.first = first;
        this.second = second;
        String Text1 = "Было " + this.first + " баранов. Волк съел " + this.second + ". Сколько осталось?";
        String Text2 = "У Миши было несколько одноразок. Цена каждой - " + this.first + " рублей. " + "Саша выпарил "
                + this.second + ". Сколько денег Саша должен Мише?";
        String Text3 = "Было " + this.first + " яблок. Купили ещё " + this.second + ". Сколько стало?";

        switch (Text_Ind % 3) {
            case 0:
                this.Text = Text1;
                this.Answer = String.valueOf(first - second);
                break;
            case 1:
                this.Text = Text2;
                this.Answer = String.valueOf(first * second);
                break;
            default:
                this.Text = Text3;
                this.Answer = String.valueOf(first + second);
                break;
        }
    }

    @Override
    public String getText() {
        return Text;
    }

    @Override
    public Result validate(String answer) {
        if (!isDouble(answer)) {
            return Result.INCORRECT_INPUT;
        }
        if (answer.equals(Answer)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    private boolean isDouble(String answer) {
        try {
            Double.parseDouble(answer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
