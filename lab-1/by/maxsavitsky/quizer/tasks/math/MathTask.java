package by.maxsavitsky.quizer.tasks.math;

import by.maxsavitsky.quizer.tasks.Task;

public interface MathTask extends Task {

    enum Operation {
        SUM('+'),
        SUBTRACT('-'),
        DIVIDE('/'),
        MULTIPLY('*');

        private final char character;

        Operation(char character) {
            this.character = character;
        }

        public char getCharacter() {
            return character;
        }
    }

}
