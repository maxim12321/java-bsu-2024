import core.Result;
import generators.EquationTaskGenerator;
import generators.ExpressionTaskGenerator;
import generators.GroupTaskGenerator;
import generators.PoolTaskGenerator;
import tasks.TextTask;
import tasks.math.MathTask;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}, значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Basic", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );
        return map;
    }

    public static void main(String[] args) {

    }
}