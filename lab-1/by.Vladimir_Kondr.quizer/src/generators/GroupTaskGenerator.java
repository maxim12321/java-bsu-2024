package generators;

import core.Task;
import core.TaskGenerator;
import exceptions.NoSufficientGeneratorsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    private final Collection<TaskGenerator<? extends Task>> generators;

    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }

    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        this.generators = generators;
    }

    @Override
    public Task generate() {
        Collection<TaskGenerator<? extends Task>> removed = new ArrayList<>();
        Random random = new Random();
        while (!generators.isEmpty()) {
            int index = random.nextInt(generators.size());
            var iterator = generators.iterator();
            TaskGenerator<? extends Task> generator = iterator.next();
            while (iterator.hasNext() && --index > 0) {
                generator = iterator.next();
            }
            try {
                Task task = generator.generate();
                generators.addAll(removed);
                removed.clear();
                return task;
            } catch (RuntimeException e) {
                removed.add(generator);
                iterator.remove();
            }
        }
        generators.addAll(removed);
        removed.clear();
        throw new NoSufficientGeneratorsException("All of the generators have failed while trying to generate tasks");
    }
}
