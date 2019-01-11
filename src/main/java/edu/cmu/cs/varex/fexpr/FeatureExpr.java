package edu.cmu.cs.varex.fexpr;

import edu.cmu.cs.varex.UnimplementedVException;
import edu.cmu.cs.varex.V;

public class FeatureExpr {
    public static V<Boolean> not(V<Boolean> ctx) {
        throw new UnimplementedVException();

    }

    public static V<Boolean> True() {
        throw new UnimplementedVException();

    }

    public static boolean isContradiction(V<Boolean> condition) {
        throw new UnimplementedVException();

    }

    public static boolean isTautology(V<Boolean> condition) {
        throw new UnimplementedVException();

    }

    public static boolean isSatisfiable(V<Boolean> condition) {
        throw new UnimplementedVException();

    }
}
