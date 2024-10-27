package by.v10k13.quizer.tests;

import by.v10k13.quizer.Quiz;
import by.v10k13.quizer.Task;
import by.v10k13.quizer.TaskGenerator;
import by.v10k13.quizer.generators.GroupTaskGenerator;
import by.v10k13.quizer.generators.PoolTaskGenerator;
import by.v10k13.quizer.generators.math.AbstractMathTaskGenerator;
import by.v10k13.quizer.generators.math.EquationTaskGenerator;
import by.v10k13.quizer.generators.math.ExpressionTaskGenerator;
import by.v10k13.quizer.tasks.TextTask;
import by.v10k13.quizer.tasks.math.MathTask;

import java.util.*;

public class SampleMain {

    public static Map<String, Quiz> getQuizMap() {
        var res = new HashMap<String, Quiz>();
        {
            var list = new ArrayList<Task>();
            list.add(new TextTask("How are you?", "OK"));
            list.add(new TextTask("How are you?", "OK"));
            list.add(new TextTask("How are you?", "OK"));
            list.add(new TextTask("How are you?", "OK"));
            list.add(new TextTask("How are you?", "OK"));
            list.add(new TextTask("How are you?", "OK!"));
            var Gen = new PoolTaskGenerator(false, list);
            res.put("PoolTaskGenerator", new Quiz(Gen, 6));
        }
        {
            var gen_list = new ArrayList<TaskGenerator<?>>();
            {
                var list = new ArrayList<Task>();
                list.add(new TextTask("How are you?", "OK"));
                list.add(new TextTask("How are you?", "OK"));
                list.add(new TextTask("How are you?", "OK"));
                list.add(new TextTask("How are you?", "OK"));
                list.add(new TextTask("How are you?", "OK"));
                list.add(new TextTask("How are you?", "OK!"));
                var Gen = new PoolTaskGenerator(false, list);
                gen_list.add(Gen);
            }
            {
                var list = new ArrayList<Task>();
                list.add(new TextTask("How you are not?", "BAD"));
                list.add(new TextTask("How you are not?", "BAD"));
                list.add(new TextTask("How you are not?", "BAD"));
                list.add(new TextTask("How you are not?", "BAD"));
                list.add(new TextTask("How you are not?", "BAD"));
                list.add(new TextTask("How you are not?", "BAD!"));
                var Gen = new PoolTaskGenerator(false, list);
                gen_list.add(Gen);
            }
            var Gen = new GroupTaskGenerator(gen_list);
            res.put("GroupTaskGenerator", new Quiz(Gen, 12));
        }
        {
            var conf = new AbstractMathTaskGenerator.MathTaskGeneratorConfig(1, 10, EnumSet.allOf(MathTask.Operators.class));
            res.put("ExpressionTaskGenerator", new Quiz(new ExpressionTaskGenerator(conf), 6));
        }
        {
            var conf = new AbstractMathTaskGenerator.MathTaskGeneratorConfig(1, 10, EnumSet.allOf(MathTask.Operators.class));
            res.put("EquationTaskGenerator", new Quiz(new EquationTaskGenerator(conf), 6));
        }
        return res;
    }

    public static void main(String[] args) {
        var q =getQuizMap().get(args.length > 0 ? args[0] : "GroupTaskGenerator");
        Scanner scan = new Scanner(System.in);
        while (!q.isFinished()) {
            System.out.println(q.nextTask().getText());
            var ans = scan.next();
            var res = q.provideAnswer(ans);
            if (res == Task.Result.INCORRECT_INPUT)
                System.out.println("Incorrect input.");
        }
        System.out.println(q.getMark());
    }
}
