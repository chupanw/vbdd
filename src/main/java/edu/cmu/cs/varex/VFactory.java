package edu.cmu.cs.varex;

import edu.cmu.cs.varex.fexpr.FeatureExpr;
import edu.cmu.cs.varex.vbdd.VBDDFactory;
import edu.cmu.cs.varex.vbdd.VNode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class VFactory {



    @Deprecated
    public static <U> V<U> one(@Nullable U v) {
        if (v instanceof V)
            return (V<U>) v;
        return VBDDFactory.createValue(v);
    }

    public static <U> V<U> one(V<Boolean> configSpace, @Nullable U v) {
        if (v instanceof V)
            return (V<U>) v;
        return VBDDFactory.createValue(v).select(configSpace);
    }

    public static <U> V<? extends U> choice(@Nonnull V<Boolean> condition, @Nullable U a, @Nullable U b) {
        assert condition != null;
        if (FeatureExpr.isContradiction(condition))
            return one(b);
        else if (FeatureExpr.isTautology(condition))
            return one(a);
        else
            return VBDDFactory.choice(condition, a, b);
    }

    public static <U> V<? extends U> choice(@Nonnull V<Boolean> condition, Supplier<U> a, Supplier<U> b) {
        assert condition != null;
        if (FeatureExpr.isContradiction(condition))
            return one(b.get());
        else if (FeatureExpr.isTautology(condition))
            return one(a.get());
        else
            return VBDDFactory.choice(condition, a.get(), b.get());
    }

    public static <U> V<? extends U> choice(@Nonnull V<Boolean> condition, @Nonnull V<? extends U> a, @Nonnull V<? extends U> b) {
        assert a != null;
        assert b != null;
        assert condition != null;
        if (!FeatureExpr.isSatisfiable(condition))
            return b;
        else if (FeatureExpr.isTautology(condition))
            return a;
        else
            return VBDDFactory.ite_(condition, (V<U>) a, (V<U>) b);
    }

    public static V<Boolean> feature(String name) {
        return VBDDFactory.feature(name);
    }

    public static final V<Boolean> TRUE = VBDDFactory.TRUE;
    public static final V<Boolean> FALSE = VBDDFactory.FALSE;
    public static final V<?> EMPTY = VBDDFactory.EMPTY;

}
