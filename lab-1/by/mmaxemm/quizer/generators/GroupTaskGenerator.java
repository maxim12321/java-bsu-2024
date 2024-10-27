package by.mmaxemm.quizer.generators;

import by.mmaxemm.quizer.Task;
import by.mmaxemm.quizer.TaskGenerator;
import by.mmaxemm.quizer.exceptions.TaskGenerationException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class GroupTaskGenerator implements TaskGenerator {
    TaskGenerator[] generators;
    Random random;

    public GroupTaskGenerator(TaskGenerator... generators) {
      this.generators = generators;
      random = new Random();
    }

    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        this.generators = generators.toArray(new TaskGenerator[0]);
        random = new Random();
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() throws TaskGenerationException {
        List<TaskGenerator> genList = new ArrayList<>(Arrays.asList(generators));
        while(!genList.isEmpty()) {
            try {
                return generators[random.nextInt(generators.length)].generate();
            } catch (Exception e) {
                continue;
            }
        }
        throw new TaskGenerationException("All generators failed to generate a task");
    }
}
