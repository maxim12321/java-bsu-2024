package by.MikitaShutro;

public enum Operations {
    SumOp('+') {
        @Override
        public Integer Function(Integer a, Integer b) {
            return a + b;
        }

        @Override
        public Integer InverseAFunction(Integer b, Integer answer) {
            return answer - b;
        }

        @Override
        public Integer InverseBFunction(Integer a, Integer answer) {
            return answer - a;
        }
    },
    SubOp('-') {
        @Override
        public Integer Function(Integer a, Integer b) {
            return a - b;
        }

        @Override
        public Integer InverseAFunction(Integer b, Integer answer) {
            return answer + b;
        }

        @Override
        public Integer InverseBFunction(Integer a, Integer answer) {
            return a - answer;
        }
    },
    MulOp('*') {
        @Override
        public Integer Function(Integer a, Integer b) {
            return a * b;
        }

        @Override
        public Integer InverseAFunction(Integer b, Integer answer) {
            return answer / b;
        }

        @Override
        public Integer InverseBFunction(Integer a, Integer answer) {
            return answer / a;
        }
    },
    DevOp('/') {
        @Override
        public Integer Function(Integer a, Integer b) {
            return a / b;
        }

        @Override
        public Integer InverseAFunction(Integer b, Integer answer) {
            return answer * b;
        }

        @Override
        public Integer InverseBFunction(Integer a, Integer answer) {
            return a / answer;
        }
    };
    public final char Symbol;
    Operations(char Symbol) { this.Symbol = Symbol; }
    public abstract Integer Function(Integer a, Integer b);
    public abstract Integer InverseAFunction(Integer b, Integer answer);
    public abstract Integer InverseBFunction(Integer a, Integer answer);

    @Override
    public String toString() {
        return Character.toString(Symbol);
    }
}

