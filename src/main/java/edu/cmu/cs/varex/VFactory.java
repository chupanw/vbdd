package edu.cmu.cs.varex;

import edu.cmu.cs.varex.fexpr.FeatureExpr;
import edu.cmu.cs.varex.vbdd.VBDDFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class VFactory {


    @Deprecated
    static <U> V<U> one(@Nullable U v) {
        return one(FeatureExpr.True(), v);
    }

    static <U> V<U> one(V<Boolean> configSpace, @Nullable U v) {
        throw new UnimplementedVException();

//        if (v instanceof V) {
//            return (V<U>) v;
//        } else {
//            return VBDDFactory.value() new One(configSpace, v);
//        }
    }

    static <U> V<? extends U> choice(@Nonnull V<Boolean> condition, @Nullable U a, @Nullable U b) {
        assert condition != null;
        if (FeatureExpr.isContradiction(condition))
            return one(b);
        else if (FeatureExpr.isTautology(condition))
            return one(a);
        else
            return VBDDFactory.choice(condition, a, b);
    }

    static <U> V<? extends U> choice(@Nonnull V<Boolean> condition, Supplier<U> a, Supplier<U> b) {
        assert condition != null;
        if (FeatureExpr.isContradiction(condition))
            return one(b.get());
        else if (FeatureExpr.isTautology(condition))
            return one(a.get());
        else
            return VBDDFactory.choice(condition, a.get(), b.get());
    }

    static <U> V<? extends U> choice(@Nonnull V<Boolean> condition, @Nonnull V<? extends U> a, @Nonnull V<? extends U> b) {
        throw new UnimplementedVException();

//        assert a != null;
//        //TODO should not accept null values here. requires clean initialization of variational variables with One(null) instead of null
//        if (b == null)
//            b = one(null);
//        assert condition != null;
//        if (!FeatureExpr.isSatisfiable(condition))
//            return b;
//        else if (FeatureExpr.isTautology(condition))
//            return a;
//        else
//            return VBDDFactory.choice(condition, a, b);
    }
}
