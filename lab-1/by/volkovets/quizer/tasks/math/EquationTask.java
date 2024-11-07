package by.volkovets.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int X_Pos, Operation Operation, int First, int Second) {
        super(X_Pos,  Operation, First, Second);
    }

    @Override
    protected double getAnswer() {
        return super.getAnswer();
    }

    @Override
    public String getText() {
        String Text;
        if (this.x_pos == 0) {
            Text = "x" + this.operation + this.first + " = " + this.second;
        } else {
            Text = this.first + this.operation + "x" + " = " + this.second;
        }
        return Text;
    }
}
