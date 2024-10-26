package by.v10k13.quizer.tasks.math;

import by.v10k13.quizer.tasks.AbstractTask;

public abstract class AbstractMathTask extends AbstractTask implements MathTask {
    private final double Value;
    private final double Precision;
    private final boolean IsFloating;

    public record AMTaskConfig(double Value, double Precision, boolean IsFloating) {
        public AMTaskConfig(double Value) {
            this(Value, 0.001, true);
        }
        public AMTaskConfig(double Value, double Precision) {
            this(Value, Precision, true);
        }
        public AMTaskConfig(double Value, boolean IsFloating) {
            this(Value, 0.001, IsFloating);
        }

        public static AMTaskConfig CreateFromValue(Number number, double relativePrecision) {
            double a = number.doubleValue();
            if (a == 0)
                return new AMTaskConfig(0, 1E-300, true);
            if (a % 1 == 0)
                return new AMTaskConfig(a, 0.3, false);
            return new AMTaskConfig(a, Math.pow(10, Math.ceil(Math.log10(a))) * relativePrecision, true);
        }
    }

    public AbstractMathTask(AMTaskConfig config, String text) {
        super(text);
        Value = config.Value;
        Precision = config.Precision;
        IsFloating = config.IsFloating;
    }

    @Override
    public Result validate(String answer) {
        String val = convertToParseble(answer, IsFloating);
        if (val == null)
            return Result.INCORRECT_INPUT;

        try {
            double parsed_value = Double.parseDouble(val);

            if (Math.abs(Value - parsed_value) < Precision)
                return Result.OK;

            return Result.WRONG;
        }
        catch (NumberFormatException a) {
            return  Result.INCORRECT_INPUT;
        }
    }

    public double Precision() {
        return Precision;
    }

    public double Answer() {
        return Value;
    }
}
