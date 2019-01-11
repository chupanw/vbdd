package edu.cmu.cs.varex.vbdd;

import javax.annotation.Nonnull;

public class VValue<T> extends VNodeImpl<T> {

    private final T value;

    public VValue(@Nonnull T value) {
        super(VBDDFactory.VALUEFEATURE, null, null);
        this.value=value;
        assert(value!=null);
    }

    public T _value() {
        return value;
    }

    @Override
    public T getOne() {
        return _value();
    }

    @Override
    public boolean _isValue() {
        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof VValue)
            return this.value.equals(((VValue<T>)that).value);
        return super.equals(that);
    }

}
