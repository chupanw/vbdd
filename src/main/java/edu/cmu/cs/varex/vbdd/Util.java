package edu.cmu.cs.varex.vbdd;

public class Util {

    public static class Pair<A, B> {
        public final A a;
        public final B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public int hashCode() {
            return a.hashCode() + b.hashCode();
        }

        public boolean equals(Object t) {
            if (t instanceof Pair) {
                Pair<A, B> that = (Pair<A, B>) t;
                return this.a == that.a && this.b == that.b;
            }
            return false;
        }
    }

    public static class Triple<A, B, C> {
        private final A a;
        private final B b;
        private final C c;

        public Triple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int hashCode() {
            return a.hashCode() + b.hashCode() + c.hashCode();
        }

        public boolean equals(Object t) {
            if (t instanceof Triple) {
                Triple<A, B, C> that = (Triple<A, B, C>) t;
                return this.a == that.a && this.b == that.b && this.c == that.c;
            }
            return false;
        }
    }
}
