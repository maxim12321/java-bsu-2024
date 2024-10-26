package by.chewy_pegasus.quizer.tasks.math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMathTask implements MathTask{
    /*public String convertToParseble(String answer, boolean floating) {    answer = answer.strip();
        if (!floating && answer.contains(".")){
            return null;
        }
        Pattern value_pattern = Pattern.compile("^[+-]?[0-9]+(?:\\.[0-9]*)?$");
        Matcher regex_matcher = value_pattern.matcher(answer);
        if (!regex_matcher.matches()) {
            return null;
        }
        return answer;
    }*/

    @Override
    public void confirmAnswerCorrectness(String answer, boolean floating) throws IllegalArgumentException {
        if (!floating && answer.contains(".")){
            throw new IllegalArgumentException("confirmAnswerCorrectness: expected integer value, got float");
        }
        Pattern value_pattern = Pattern.compile("^[+-]?[0-9]+(?:\\.[0-9]*)?$");
        Matcher regex_matcher = value_pattern.matcher(answer);
        if (!regex_matcher.matches()) {
            throw new IllegalArgumentException("confirmAnswerCorrectness: expected number, got string");
        }
    }
}
