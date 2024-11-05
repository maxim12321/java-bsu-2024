package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.Task;
import by.merinovvvv.quizer.TaskGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


class GroupTaskGeneratorTest {

    @Mock
    private TaskGenerator<Task> mockGenerator1;

    @Mock
    private TaskGenerator<Task> mockGenerator2;

    @Mock
    private Task mockTask;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void constructorVarArgsTest() {
        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(mockGenerator1, mockGenerator2);
        Assertions.assertNotNull(groupTaskGenerator);
    }

    @Test
    void constructorCollectionTest() {

        List<TaskGenerator<Task>> generatorList = new ArrayList<>();
        generatorList.add(mockGenerator1);
        generatorList.add(mockGenerator2);
        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(generatorList);
        Assertions.assertNotNull(groupTaskGenerator);

    }

    @Test
    void generateTest() {
        when(mockGenerator1.generate()).thenReturn(mockTask);

        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(mockGenerator1);
        Task generatedTask = groupTaskGenerator.generate();
        Assertions.assertNotNull(generatedTask);
        Assertions.assertEquals(mockTask, generatedTask);
    }

    @Test
    void generateWithExceptionTest() {
        when(mockGenerator1.generate()).thenThrow(new RuntimeException("Generator failed"));
        when(mockGenerator2.generate()).thenReturn(mockTask);

        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(mockGenerator1, mockGenerator2);
        Task generatedTask = groupTaskGenerator.generate();
        Assertions.assertNotNull(generatedTask);
        Assertions.assertThrows(RuntimeException.class,
                mockGenerator1::generate);
        Assertions.assertEquals(mockTask, generatedTask);
    }

    @Test
    void generateAllGeneratorsFailTest() {
        when(mockGenerator1.generate()).thenThrow(new RuntimeException("Generator 1 failed"));
        when(mockGenerator2.generate()).thenThrow(new RuntimeException("Generator 2 failed"));

        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(mockGenerator1, mockGenerator2);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, groupTaskGenerator::generate);
        Assertions.assertEquals("All generators failed to generate a task", exception.getMessage());
    }

    @Test
    void generateNoGeneratorsTest() {
        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(new ArrayList<>());
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, groupTaskGenerator::generate);
        Assertions.assertEquals("No generators available", exception.getMessage());
    }
}