package by.pkasila.quizer.tasks;

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
