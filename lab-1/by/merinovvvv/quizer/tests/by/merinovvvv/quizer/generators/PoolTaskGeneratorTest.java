package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PoolTaskGeneratorTest {
    @Test
    void costrPoolTaskGeneratorVarargsTest() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        PoolTaskGenerator<Task> poolTaskGenerator = new PoolTaskGenerator<>(true, task1, task2);
        Assertions.assertNotNull(poolTaskGenerator);
    }

    @Test
    void costrPoolTaskGeneratorCollectionTest() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        PoolTaskGenerator<Task> poolTaskGenerator = new PoolTaskGenerator<>(true, taskList);
        Assertions.assertNotNull(poolTaskGenerator);
    }

    @Test
    void emptyTasksGenerateTest() {
        PoolTaskGenerator<Task> poolTaskGenerator = new PoolTaskGenerator<>(true, List.of());
        Assertions.assertThrows(RuntimeException.class,
                poolTaskGenerator::generate);
    }

    @Test
    void noTasksAvailableGenerateTest() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        PoolTaskGenerator<Task> poolTaskGenerator = new PoolTaskGenerator<>(false, taskList);
        poolTaskGenerator.generate();
        poolTaskGenerator.generate();

        Assertions.assertThrows(RuntimeException.class,
                poolTaskGenerator::generate);
    }

    @Test
    void generateTest() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);

        PoolTaskGenerator<Task> poolTaskGenerator = new PoolTaskGenerator<>(true, task1, task2);

        when(task1.getText()).thenReturn("Task1");
        when(task2.getText()).thenReturn("Task2");

        Task generatedTask = poolTaskGenerator.generate();
        Assertions.assertTrue(generatedTask == task1 || generatedTask == task2);
        Assertions.assertTrue(generatedTask.getText().equals("Task1") || generatedTask.getText().equals("Task2"));
    }
}