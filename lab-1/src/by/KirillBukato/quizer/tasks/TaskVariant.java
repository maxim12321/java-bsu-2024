package by.KirillBukato.quizer.tasks;

/**
 * Enum, который описывает возможные варианты ответа.
 */
public enum TaskVariant {
    A("A"),
    B("B"),
    C("C");

    TaskVariant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private final String name;
}
