package edu.cmu.cs.varex;

/**
 * Created by ckaestne on 11/28/2015.
 */
public class OptImpl<T> implements Opt<T> {
    private final V<Boolean> condition;
    private final T value;

    public OptImpl(V<Boolean> condition, T value) {
        this.condition = condition;
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public V<Boolean> getCondition() {
        return condition;
    }
}
