package edu.cmu.cs.varex.fexpr;

import edu.cmu.cs.varex.UnimplementedVException;
import edu.cmu.cs.varex.V;
import edu.cmu.cs.varex.vbdd.VBDDFactory;
import edu.cmu.cs.varex.vbdd.VNode;

public class FeatureExpr {
    public static V<Boolean> not(V<Boolean> ctx) {
        return VBDDFactory.not((VNode<Boolean>) ctx);
    }

    public static V<Boolean> True() {
        return VBDDFactory.TRUE;
    }

    public static boolean isContradiction(V<Boolean> condition) {
        return condition == VBDDFactory.FALSE;
    }

    public static boolean isTautology(V<Boolean> condition) {
        return condition == VBDDFactory.TRUE;
    }

    public static boolean isSatisfiable(V<Boolean> condition) {
        return !isContradiction(condition);
    }

    public static V<Boolean> implies(V<Boolean> a, V<Boolean> b) {
        return or(not(a), b);
    }

    public static V<Boolean> or(V<Boolean> a, V<Boolean> b) {
        return VBDDFactory.or((VNode<Boolean>) a, (VNode<Boolean>) b);
    }

    public static V<Boolean> and(V<Boolean> a, V<Boolean> b) {
        return VBDDFactory.and((VNode<Boolean>) a, (VNode<Boolean>) b);
    }


}
