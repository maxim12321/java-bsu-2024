package by.volkovets.quizer.generators;

import by.volkovets.quizer.TaskGenerator;
import by.volkovets.quizer.tasks.WhoLooseTask;
import by.volkovets.quizer.Task;

public class WhoLooseTaskGenerator implements TaskGenerator<Task>  {

    private final String winner;
    private final String looser;
    
    public WhoLooseTaskGenerator(String First_Player, String Second_Player) {
        this.winner = Second_Player;
        this.looser = First_Player;
    }

    @Override
    public WhoLooseTask generate() {

        WhoLooseTask task = new WhoLooseTask(winner, looser);
        return task;
    }
}
