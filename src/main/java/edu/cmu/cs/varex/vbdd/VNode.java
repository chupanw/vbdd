package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.V;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface VNode<T> extends V<T> {

    @Nonnull
    Symbol _symbol();

    @Nullable
    VNode<T> _low();

    @Nullable
    VNode<T> _high();

    boolean _isValue();

    String toDot();

    VNode<T> union(VNode<T> that);


//    //exception or NonNull
//    @Nonnull
//    T _value();
}

