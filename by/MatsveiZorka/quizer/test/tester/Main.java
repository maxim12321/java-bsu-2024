package by.MatsveiZorka.quizer.test.tester;


public class Main {

    public static void main(String[] args) {
        Tester.RunGroups(Main.class);
    }

    @TestableGroup(Name="Creating tests.")
    public static void test1(Tester tester) {
        tester.RunTest(tst -> {

        });
    }
}
