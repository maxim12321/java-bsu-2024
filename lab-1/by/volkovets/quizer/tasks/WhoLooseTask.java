package by.volkovets.quizer.tasks;

import by.volkovets.quizer.Task;
import by.volkovets.quizer.Result;

public class WhoLooseTask implements Task {

    final private String Text;
    final private String Winner;
    final private String Looser;

    public WhoLooseTask(String first, String second) {
        this.Text = "Игрок по имени " + first + " выиграл у игрока по имени " + second + ". Кто проиграл?";
        this.Winner = first;
        this.Looser = second;
    }

    @Override
    public String getText() {
        return Text;
    }

    @Override
    public Result validate(String answer) {
        if (!answer.equals(this.Looser) && !answer.equals(this.Winner)) {
            return Result.INCORRECT_INPUT;
        }
        if (answer.equals(this.Looser)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
}
