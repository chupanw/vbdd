package edu.cmu.cs.varex;

/**
 * central abstraction for conditional/variational values
 *
 * @param <T>
 */
public interface Opt<T> {
    T getValue();

    V<Boolean> getCondition();

    static <U> Opt<U> create(V<Boolean> expr, U v) {
        return new OptImpl(expr, v);
    }

    static <U> Opt<U> create(U v) {
        return new OptImpl(VFactory.TRUE, v);
    }
}
