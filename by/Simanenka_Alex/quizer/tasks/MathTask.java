package by.Simanenka_Alex.quizer.tasks;

import by.Simanenka_Alex.quizer.Result;
import by.Simanenka_Alex.quizer.Task;

public interface MathTask extends Task {

    @Override
    String getText();

    @Override
    Result validate(String answer);

    enum Operation {
        ADD("+"), SUB("-"), MUL("*"), DIV("/");

        Operation(String ch) {
            this.ch = ch;
        }

        String GetCh() {
            return ch;
        }

        private String ch;
    }

}
