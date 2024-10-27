package by.PalikarpauMichail.quizer.generators;

import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.EnumSet;
import java.util.List;

import by.PalikarpauMichail.quizer.exceptions.BadMathGeneratorBoundariesException;
import by.PalikarpauMichail.quizer.generators.math.AbstractMathTaskGenerator;
import by.PalikarpauMichail.quizer.tasks.TextMathTask;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;
import by.PalikarpauMichail.quizer.tasks.math.MathTask.Operation;

public class TextMathTaskGenerator extends AbstractMathTaskGenerator<TextMathTask> {
    Map<MathTask.Operation, String> textVarians = 
    Map.of(
        MathTask.Operation.ADDITION,    
        "У Васи было %d ананасов. Петя решил ему отдать свои %d ананасов. Сколько теперь ананасов у Васи?",
        MathTask.Operation.SUBTRACTION,
        "Вася шёл по улице, неся в рюкзаке %d спелых дынь. Однако рюкзак незаметно расстегнулся и " + 
        "из рюкзака выпало %d дынь. Сколько дынь осталось у Васи?",
        MathTask.Operation.MULTIPLICATION,
        "Каждый день Вася выпивает %d чашек чая. Сколько всего чашек чая выпьет Вася в течение %d дней?",
        MathTask.Operation.DIVISON,
        "Cегодня у Васи день рождения. По этому поводу он решил раздать своим друзьям %d конфет. " + 
        "Всего у Васи %d друзей. Сколько конфет получит каждый друг Васи?"
    );

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations             список допустимых операций
     */
    public TextMathTaskGenerator(
        int minNumber,
        int maxNumber,
        EnumSet<MathTask.Operation> operations
    ) throws BadMathGeneratorBoundariesException, IllegalArgumentException {
        super(Integer.max(0, minNumber), maxNumber, operations);
    }

    /**
     * return задание типа {@link TextMathTask}
     */
    @Override
    public TextMathTask generate() {
        SimpleEntry<Operation, List<Integer>> entry = generateArguments();
        var operation = entry.getKey();
        var arguments = entry.getValue();
        return new TextMathTask(textVarians.get(operation), arguments.get(0), arguments.get(1), arguments.get(2), operation);
    }
}
