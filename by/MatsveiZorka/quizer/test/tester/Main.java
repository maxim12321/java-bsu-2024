package by.MatsveiZorka.quizer.test.tester;

import by.MatsveiZorka.quizer.Quiz;
import java.util.Map;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {
        Tester.RunGroups(Main.class);
    }

    public static Map<String, Quiz> getQuizMap() {
        return null;
    }

    @TestableGroup(Name="Creating tests.")
    public static void test1(Tester tester) {
        tester.RunTest(tst -> {

        });
    }
}
