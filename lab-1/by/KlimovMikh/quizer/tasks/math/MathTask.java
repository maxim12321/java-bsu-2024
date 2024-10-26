package KlimovMikh.quizer.tasks.math;

import KlimovMikh.quizer.Task;

public interface MathTask extends Task {
    enum Operation {
        PLUS {
            @Override
            public String toString() {
                return "+";
            }
            @Override
            public int apply(boolean reverse, int x, int y) {
                if (reverse) {
                    return y - x;
                }
                return x + y;
            }
        },
        MINUS {
            @Override
            public String toString() {
                return "-";
            }
            @Override
            public int apply(boolean reverse, int x, int y) {
                if (reverse) {
                    return x + y;
                }
                return x - y;
            }
//            public int reverse(int x, int y) {
//                return x + y;
//            }
        },
        MULTIPLY {
            @Override
            public String toString() {
                return "*";
            }
            @Override
            public int apply(boolean reverse, int x, int y) {
                if (reverse) {
                    return y / x;
                }
                return x * y;
            }
        },
        DIVIDE {
            @Override
            public String toString() {
                return "/";
            }
            @Override
            public int apply(boolean reverse, int x, int y) {
                if (reverse) {
                    return x * y;
                }
                return x / y;
            }
        };

        public abstract int apply(boolean reverse, int x, int y);
    }
}
