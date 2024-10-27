package by.MikhailNaumovich.quizer.tasks.sequence;

public enum PatternTypeEnum {
    FIBONACCI("Fibonacci Sequence"),
    SQUARE("Square Numbers Sequence"),
    TRIANGULAR("Triangular Numbers Sequnce");

    private final String description;

    PatternTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}