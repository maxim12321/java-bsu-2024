package by.KirillBukato.quizer.tasks.math;

public class StoryExpressionTask extends AbstractExpressionTask {

    public enum Story {
        APPLES,
        ORANGES
    }

    public StoryExpressionTask(int left, MathTask.Operation operator, int right, Story story) {
        super(left, operator, right);
        this.story = story;
    }

    @Override
    public String getText() {
        String text = switch (story) {
            case APPLES -> "У Олега было " + left + " яблок. ";
            case ORANGES -> "У Олега было " + left + " апельсинов. ";
        };
        text += switch (operator) {
            case ADD -> switch (story) {
                case APPLES -> "Пришёл добрый Виктор и дал Олегу ещё " + right + " яблок. ";
                case ORANGES -> "Пришёл добрый Виктор и дал Олегу ещё " + right + " апельсинов. ";
            };
            case SUBTRACT -> switch (story) {
                case APPLES -> "Пришёл злой Виктор и забрал у Олега " + right + " яблок. ";
                case ORANGES -> "Пришёл злой Виктор и забрал у Олега " + right + " апельсинов. ";
            };
            case MULTIPLY -> switch (story) {
                case APPLES ->
                        "Пришёл добрый Виктор, забрал у Олега ящик с яблоками и вернул ему " + right + " таких ящиков. ";
                case ORANGES ->
                        "Пришёл добрый Виктор, забрал у Олега ящик с апельсинами и вернул ему " + right + " таких ящиков. ";
            };
            case DIVIDE -> switch (story) {
                case APPLES ->
                        "Пришёл злой Виктор со своими друзьями. Теперь Олегу нужно разделить свои яблоки поровну (очень-очень поровну) между " + right + " людьми. ";
                case ORANGES ->
                        "Пришёл злой Виктор со своими друзьями. Теперь Олегу нужно разделить свои апельсины поровну (очень-очень поровну) между " + right + " людьми. ";
            };
        };
        text += switch (story) {
            case APPLES -> "Сколько теперь яблок у Олега? ";
            case ORANGES -> "Сколько теперь апельсинов у Олега? ";
        };
        if (operator == Operation.DIVIDE) {
            text += "(Ответ дайте в виде десятичной дроби)";
        }
        return text;
    }

    private final Story story;
}
