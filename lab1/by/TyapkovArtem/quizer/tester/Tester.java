package lab1.by.TyapkovArtem.quizer.tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author <a href="https://github.com/10-13">10-13</a>
 */
public class Tester {
    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String YELLOW = "\033[0;33m";
    private static final String RESET = "\033[0m";

    private int Tests_;
    private int Mists_;

    protected void PrintException(Exception ex, String prefix) {
        System.out.println(prefix + ex.getMessage());
        var stack = ex.getStackTrace();
        for (var i : stack)
            if (!i.getClassName().equals(Tester.class.getName()))
                System.out.println(prefix + "    " + i);

    }

    public void RunTest(Test test) {
        Tests_++;
        try {
            test.Execute(this);
        } catch (TestFailureException ex) {
            System.out.println(RED + "  Test " + Tests_ + " failed.");
            PrintException(ex, "    | ");
            System.out.print(RESET);
            Mists_++;
        }
    }

    private void PrintResult_() {
        var cl = (Mists_ == 0) ? GREEN : RED;
        System.out.print(YELLOW + "Group ");
        System.out.print(cl + (cl == GREEN ? "completed!" : "failed!   ")); // FCK WARNINGS
        System.out.print(YELLOW + " [");
        System.out.print(cl + (Tests_ - Mists_));
        System.out.print(YELLOW + "/");
        System.out.print(cl + Tests_);
        System.out.println(YELLOW + "]");
    }

    public void RunTestGroup(TestGroup group, String name) {
        Tests_ = 0;
        Mists_ = 0;
        System.out.println(YELLOW + name + RESET);
        try {
            group.ExecuteGroup(this);
        } catch (RuntimeException ex) {
            System.out.println(RED + "Failed with non test error.");
            PrintException(ex, " ");
            System.out.print(RESET);
        }
        PrintResult_();
    }

    public void Assert(boolean value) {
        if (!value)
            throw new TestAssertionFailureException();
    }

    public void AssertNoExceptions(Runnable code) {
        try {
            code.run();
        } catch (RuntimeException ex) {
            throw new TestBehaviorFailureException(ex);
        }
    }

    /**
     *
     * @param code Code with waited Exception
     * @param waited Exception type (Must be nested from Runtime Exception)
     * @param allow_nested If nested from waited class exceptions allowed
     * @param message Waited message (null for ignore)
     */
    public void AssertException(Runnable code, Class<?> waited, boolean allow_nested, String message) {
        boolean success = false;
        try {
            code.run();
        }
        catch (RuntimeException ex) {
            if (ex.getClass().equals(waited))
                success = true;
            if (allow_nested && waited.isInstance(ex))
                success = true;
            if (message != null)
                success &= ex.getMessage().equals(message);
        }
        if (!success)
            throw new TestBehaviorFailureException();
    }

    public void AssertException(Runnable code, Class<?> waited, String message) {
        AssertException(code, waited, false, message);
    }

    public void AssertException(Runnable code, Class<?> waited, boolean allow_nested) {
        AssertException(code, waited, allow_nested, null);
    }

    public void AssertException(Runnable code, Class<?> waited) {
        AssertException(code, waited, false, null);
    }

    public static void RunGroups(Class<?> some_type) {
        Tester tester = new Tester();
        var m_list = Arrays.stream(some_type.getDeclaredMethods())
                .filter(a-> a.getAnnotation(TestableGroup.class) != null)
                .filter(a->Modifier.isStatic(a.getModifiers()))
                .filter(a->a.getParameterCount() == 1)
                .filter(a->a.getParameterTypes()[0].equals(Tester.class))
                .sorted(Comparator.comparing(Method::getName))
                .toList();

        for (var method : m_list) {
            var gr = method.getAnnotation(TestableGroup.class);
            tester.RunTestGroup(tstr -> {
                        try {
                            method.invoke(null, tstr);
                        } catch (InvocationTargetException | IllegalAccessException _) {}
                    },
                    gr.Name());
        }
    }
}
