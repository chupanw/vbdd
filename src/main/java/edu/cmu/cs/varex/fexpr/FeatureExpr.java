package edu.cmu.cs.varex.fexpr;

import edu.cmu.cs.varex.V;
import edu.cmu.cs.varex.vbdd.VBDDFactory;
import edu.cmu.cs.varex.vbdd.VNode;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    public static String toString(V<Boolean> expr) {
        if (expr == VBDDFactory.TRUE) return "TRUE";
        if (expr == VBDDFactory.FALSE) return "FALSE";
        List<List<String>> p = pathsToTrue((VNode<Boolean>) expr);
        Iterator<List<String>> pathIterator = p.iterator();

        if (!pathIterator.hasNext())
            return "FALSE";

        StringBuffer b = new StringBuffer();
        b.append("(");
        while (pathIterator.hasNext()) {
            Iterator<String> conjIt = pathIterator.next().iterator();
            while (conjIt.hasNext()) {
                b.append(conjIt.next());
                if (conjIt.hasNext())
                    b.append(" && ");
            }

            if (pathIterator.hasNext())
                b.append(") || (");
        }
        b.append(")");
        return b.toString();
    }

    private static List<List<String>> pathsToTrue(VNode<Boolean> node) {
        if (node == VBDDFactory.TRUE) return Collections.singletonList(new LinkedList<>());//successful path
        if (node._isValue()) return Collections.EMPTY_LIST;//no successful path

        List<List<String>> result = new LinkedList<>();
        List<List<String>> highPaths = pathsToTrue(node._high());
        for (List<String> path : highPaths)
            path.add(0, node._symbol().getName());
        List<List<String>> lowPaths = pathsToTrue(node._low());
        for (List<String> path : lowPaths)
            path.add(0, "!" + node._symbol().getName());
        result.addAll(highPaths);
        result.addAll(lowPaths);
        return result;
    }

}
