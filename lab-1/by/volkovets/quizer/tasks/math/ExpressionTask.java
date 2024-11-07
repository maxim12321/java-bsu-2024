package by.volkovets.quizer.tasks.math;

public class ExpressionTask extends AbstractMathTask {
    public  ExpressionTask(Operation Operaion, int First, int Second) {
        super(2, Operaion, First, Second);
        
    }

    @Override
    protected double getAnswer() {
        return super.getAnswer();
    }

    @Override
    public String getText() {
        String Text = this.first + this.operation + this.second + " = " + "?";
        return Text;
    }
}
