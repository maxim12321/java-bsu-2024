package lab1.by.TyapkovArtem.quizer;

public enum Operation
{
    Division('/'){
        @Override
        public int Funciton(int a, int b) {
            return a / b;
        }
    },
    Multiplication('*') {
        @Override
        public int Funciton(int a, int b) {
            return a * b;
        }
    },
    Addition('+') {
        @Override
        public int Funciton(int a, int b) {
            return a + b;
        }
    },
    Subtraction('-') {
        @Override
        public int Funciton(int a, int b) {
            return a - b;
        }
    };

    public final char Symbol;
    public abstract int Funciton(int a,int b);

    Operation(char sym) {
        Symbol = sym;
    }
}