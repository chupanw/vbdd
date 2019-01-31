package edu.cmu.cs.varex;


import edu.cmu.cs.varex.fexpr.FeatureExpr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * helper functions for lists with optional entries
 */
public class VList {

    /**
     * conditional fold over all entries in this array
     * <p>
     * feature expression of the op function already includes the current context
     * of the entry
     */
    public static <T, E> V<? extends T> foldRight(Iterator<Opt<E>> list, V<? extends T> init, V<Boolean> ctx, Function4<V<Boolean>, E, T, V<? extends T>> op) {
        V<? extends T> result = init.select(ctx);

        while (list.hasNext()) {
            final Opt<E> current = list.next();

            result = result.flatMap((c, r) ->
                    VFactory.choice(current.getCondition(),
                            op.apply(FeatureExpr.and(c,current.getCondition()), current.getValue(), r),
                            VFactory.one(r)));
        }

        return result;
    }

    /**
     * same conditional fold, but may stop earlier without folding over all results if all values meet a criteria
     */
    public static <T, E> V<? extends T> foldRightUntil(Iterator<Opt<E>> list, V<? extends T> init, V<Boolean> ctx, Function4<V<Boolean>, E, T, V<? extends T>> op, Predicate<T> stopCriteria) {
        V<? extends T> result = init.select(ctx);

        while (list.hasNext()) {
            final Opt<E> current = list.next();

            result = result.flatMap((c, r) ->
                    VFactory.choice(current.getCondition(),
                            op.apply(FeatureExpr.and(c,current.getCondition()), current.getValue(), r),
                            VFactory.one(r)));

            if (FeatureExpr.isTautology(FeatureExpr.implies(ctx,result.when(t -> stopCriteria.test((T) t), false))))
                break;
        }

        return result;
    }


    public static <T> List<Opt<T>> flatten(V<? extends T> v) {
        List<Opt<T>> result = new ArrayList<Opt<T>>();
        v.sforeach(VFactory.TRUE, (f, val) -> result.add(Opt.create(f, val)));
        return result;
    }

}
