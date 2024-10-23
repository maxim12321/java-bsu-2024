package generators;

import core.GeneratorCreator;
import core.Pair;
import core.Task;
import core.TaskGenerator;
import exceptions.NoSufficientGeneratorsException;

import java.util.*;

public class GroupTaskGenerator extends GeneratorCreator implements TaskGenerator<Task> {
    private final Collection<TaskGenerator<? extends Task>> generators;
    private final List<Pair<Class<? extends TaskGenerator<? extends Task>>, Map<String, Object>>> generatorArgsList;


    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
        this.generatorArgsList = null;
    }

    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        this.generators = generators;
        this.generatorArgsList = null;
    }

    public GroupTaskGenerator(List<Pair<Class<? extends TaskGenerator<? extends Task>>, Map<String, Object>>> generatorArgsList) {
        this.generators = new ArrayList<>();
        this.generatorArgsList = generatorArgsList;
    }

    @Override
    public Task generate() {
        if (generators.isEmpty() && generatorArgsList != null) {
            for (Pair<Class<? extends TaskGenerator<? extends Task>>, Map<String, Object>> pair : generatorArgsList) {
                generators.add(createGenerator(pair.key(), pair.value()));
            }
        }
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
