package by.volkovets.quizer.generators;

import by.volkovets.quizer.Task;
import by.volkovets.quizer.TaskGenerator;
import by.volkovets.quizer.exceptions.AllGeneratorsAreDefective;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class GroupTaskGenerator implements TaskGenerator<Task> {

    private final ArrayList<TaskGenerator<? extends Task>> generators;
    private ArrayList<Boolean> generators_with_exceptions;
    private int cnt_deffected = 0;

    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... Generators) {
        this.generators = new ArrayList<>();
        for (TaskGenerator<? extends Task> generator : Generators) {
            generators.add(generator);
        }
        this.generators_with_exceptions = new ArrayList<>(Collections.nCopies(this.generators.size(), true));
    }

    GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> Generators) {
        generators = new ArrayList<>();
        for (TaskGenerator<? extends Task> generator : Generators) {
            generators.add(generator);
        }
        this.generators_with_exceptions = new ArrayList<>(Collections.nCopies(this.generators.size(), true));
    }

    @Override
    public Task generate() {
        int size = this.generators.size();
        Random random = new Random();
        Task task = null;
        boolean success = false;

        if (size == 0) {
            throw new AllGeneratorsAreDefective();
        }

        while (!success) {
            if (cnt_deffected == size) {
                throw new AllGeneratorsAreDefective();
            }

            int index = random.nextInt(size);
            int left = index;
            int right = index;

            // Ищем ближайший доступный генератор
            while (left >= 0 && !generators_with_exceptions.get(left)) {
                left--;
            }

            while (right < size && !generators_with_exceptions.get(right)) {
                right++;
            }

            
            if (left >= 0) {
                index = left;
            } else if (right < size) {
                index = right;
            } else {
                throw new AllGeneratorsAreDefective();
            }

            try {
                task = this.generators.get(index).generate();
                success = true;
            } catch (Exception e) {
                this.generators_with_exceptions.set(index, false);
                cnt_deffected++;
            }
        }

        return task;
    }
}