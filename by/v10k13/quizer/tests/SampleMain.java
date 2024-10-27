package by.v10k13.quizer.tests;

import by.v10k13.quizer.Quiz;
import by.v10k13.quizer.Task;
import by.v10k13.quizer.TaskGenerator;
import by.v10k13.quizer.generators.GroupTaskGenerator;
import by.v10k13.quizer.generators.PoolTaskGenerator;
import by.v10k13.quizer.tasks.TextTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            res.put("PoolGenerator", new Quiz(Gen, 6));
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
            res.put("PoolGenerator", new Quiz(Gen, 6));
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
