package edu.cmu.cs.varex;


import edu.cmu.cs.varex.fexpr.FeatureExpr;
import edu.cmu.cs.varex.vbdd.VBDDFactory;

import javax.annotation.Nonnull;
import java.util.function.*;

/**
 * central abstraction for conditional/variational values
 *
 * @param <T>
 */
@Nonnull
public interface V<T> {

    @Deprecated
    T getOne();

    @Deprecated
    default T getOne(@Nonnull V<Boolean> ctx) {
        assert ctx != null;
        return select(ctx).getOne();
    }


    /**
     * maps over a V describing a (possibly partial) configuration space.
     * all entries have satisfiable conditions.
     * result is another V describing the same (partial) configuration space
     * <p>
     * overloaded to access the condition of each entry if desired
     * <p>
     * fun may return null.
     */
    <U> V<? extends U> map(@Nonnull Function<? super T, ? extends U> fun);

    <U> V<? extends U> map(@Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> fun);

    /**
     * select map: shorthand for x.select(ctx).map(fun)
     * <p>
     * restricts the configuration space by ctx before applying map. removes all
     * entries that are not valid within ctx. result is at most defined in
     * configuration space ctx.
     * <p>
     * fun may return null.
     */
    default <U> V<? extends U> smap(@Nonnull V<Boolean> ctx, @Nonnull Function<? super T, ? extends U> fun) {
        assert ctx != null;
        assert fun != null;
        return this.select(ctx).map(fun);
    }

    default <U> V<? extends U> smap(@Nonnull V<Boolean> ctx, @Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> fun) {
        assert ctx != null;
        assert fun != null;
        return this.select(ctx).map(fun);
    }

    default <U> V<? extends U> smap(@Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> fun, @Nonnull V<Boolean> ctx) {
        return smap(ctx, fun);
    }

    default <U> V<? extends U> smap(@Nonnull Function<? super T, ? extends U> fun, @Nonnull V<Boolean> ctx) {
        return smap(ctx, fun);
    }

    /**
     * partially map: apply function fun to all values inside a restricted configuration space and apply function altFun
     * to all values outside the restricted configuration space. Overloaded for the common case where
     * altFun is the identify function.
     */
    default <U> V<? extends U> pmap(@Nonnull V<Boolean> ctx, @Nonnull Function<? super T, ? extends U> fun, @Nonnull Function<? super T, ? extends U> altFun) {
        assert ctx != null;
        assert fun != null;
        assert altFun != null;
        return VFactory.choice(ctx, this.select(ctx).map(fun), this.select(FeatureExpr.not(ctx)).map(altFun));
    }

    default <U> V<? extends U> pmap(@Nonnull V<Boolean> ctx, @Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> fun, @Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> altFun) {
        assert ctx != null;
        assert fun != null;
        assert altFun != null;
        return VFactory.choice(ctx, this.select(ctx).map(fun), this.select(FeatureExpr.not(ctx)).map(altFun));
    }

    default <U> V<? extends U> pmap(@Nonnull V<Boolean> ctx, @Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> fun, @Nonnull Function<? super T, ? extends U> altFun) {
        assert ctx != null;
        assert fun != null;
        assert altFun != null;
        return VFactory.choice(ctx, this.select(ctx).map(fun), this.select(FeatureExpr.not(ctx)).map(altFun));
    }

    /**
     * @param fun may not return null, but One(null)
     */
    <U> V<? extends U> flatMap(@Nonnull Function<? super T, V<? extends U>> fun);
    <U> V<? extends U> flatMapNew(@Nonnull Function<? super T, V<? extends U>> fun);

    <U> V<? extends U> flatMap(@Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun);

    /**
     * see smap
     */
    default <U> V<? extends U> sflatMap(@Nonnull V<Boolean> ctx, @Nonnull Function<? super T, V<? extends U>> fun) {
        assert ctx != null;
        assert fun != null;
        return this.select(ctx).flatMap(fun);
    }

    default <U> V<? extends U> sflatMap(@Nonnull V<Boolean> ctx, @Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun) {
        assert ctx != null;
        assert fun != null;
        return this.select(ctx).flatMap(fun);
    }

    /**
     * alternative parameter order to simplify lifting
     */
    default <U> V<? extends U> sflatMap(@Nonnull Function<? super T, V<? extends U>> fun, @Nonnull V<Boolean> ctx) {
        return sflatMap(ctx, fun);
    }

    default <U> V<? extends U> sflatMap(@Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun, @Nonnull V<Boolean> ctx) {
        return sflatMap(ctx, fun);
    }

    /**
     * see pmap
     */
    default <U> V<? extends U> pflatMap(@Nonnull V<Boolean> ctx, @Nonnull Function<? super T, V<? extends U>> fun, @Nonnull Function<? super T, V<? extends U>> altFun) {
        assert ctx != null;
        assert fun != null;
        assert altFun != null;
        return VFactory.choice(ctx, this.select(ctx).flatMap(fun), this.select(FeatureExpr.not(ctx)).flatMap(altFun));
    }

    default <U> V<? extends U> pflatMap(@Nonnull V<Boolean> ctx, @Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun, @Nonnull Function<? super T, ? extends U> altFun) {
        assert ctx != null;
        assert fun != null;
        assert altFun != null;
        return VFactory.choice(ctx, this.select(ctx).flatMap(fun), this.select(FeatureExpr.not(ctx)).map(altFun));
    }

    default <U> V<? extends U> pflatMap(@Nonnull V<Boolean> ctx, @Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun, @Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> altFun) {
        assert ctx != null;
        assert fun != null;
        assert altFun != null;
        return VFactory.choice(ctx, this.select(ctx).flatMap(fun), this.select(FeatureExpr.not(ctx)).flatMap(altFun));
    }

    void foreach(@Nonnull Consumer<T> fun);

    void foreach(@Nonnull BiConsumer<V<Boolean>, T> fun);

    void foreachExp(@Nonnull BiConsumerExp<V<Boolean>, T> fun) throws Throwable;

    default void sforeach(@Nonnull V<Boolean> ctx, @Nonnull Consumer<T> fun) {
        assert ctx != null;
        assert fun != null;
        this.select(ctx).foreach(fun);
    }

    default void sforeach(@Nonnull V<Boolean> ctx, @Nonnull BiConsumer<V<Boolean>, T> fun) {
        assert ctx != null;
        assert fun != null;
        this.select(ctx).foreach(fun);
    }

    /**
     * alternative parameter order to simplify lifting
     */
    default void sforeach(@Nonnull Consumer<T> fun, @Nonnull V<Boolean> ctx) {
        sforeach(ctx, fun);
    }

    default void sforeach(@Nonnull BiConsumer<V<Boolean>, T> fun, @Nonnull V<Boolean> ctx) {
        sforeach(ctx, fun);
    }

    V<Boolean> when(@Nonnull Predicate<T> condition, boolean filterNull);

    /**
     * select a subconfiguration space of V
     *
     * @param configSpace must be the same or smaller than the configuration
     *                    space provided by this V
     */
    V<T> select(@Nonnull V<Boolean> configSpace);

    /**
     * reduces the configuration space of V. Resulting V covers at most the
     * provided configuration space. If it was original configuration space
     * was smaller, the smaller space remains
     */
    V<T> reduce(@Nonnull V<Boolean> reducedConfigSpace);

    V<Boolean> getConfigSpace();


    /**
     * Compares equality of the wrapped value.
     *
     * @param o V to be compared.
     * @return If the wrapped values are equal.
     */
//    boolean equalValue(Object o);

//    boolean hasThrowable();

//    V<T> simplified();
//
//    V<T> restrictInteractionDegree();
//
//    static boolean isDegreeTooHigh(V<Boolean> fe) {
//        return false;
////        List sats = ((BDDV<Boolean>) fe).bdd().allsat();
////        if (sats.size() == 0) throw new RuntimeException("Not satisfiable: " + fe);
////        for (Object sat : sats) {
////            int current = 0;
////            byte[] bytes = (byte[]) sat;
////            for (int i = 0; i < bytes.length; i++) {
////                if (bytes[i] > 0) current++;
////            }
////            if (current <= GlobalConfig.maxInteractionDegree())
////                return false;
////        }
////        return true;
//    }
//
//    /**
//     * We need a special version of V<Boolean>Lib to access the names of features
//     *
//     * Ideally we should get the minimal solution from BDD directly
//     */
//    static String getOneLowDegreeSolution(V<Boolean> fe) {
//        List sats = ((BDDV<Boolean>) fe).bdd().allsat();
//        List<String> enabled = new LinkedList<>();
//        for (Object sat : sats) {
//            int current = 0;
//            byte[] bytes = (byte[]) sat;
//            for (int i = 0; i < bytes.length; i++) {
//                if (bytes[i] > 0) current++;
//            }
//            if (current <= GlobalConfig.maxInteractionDegree()) {
//                for (int i = 0; i < bytes.length; i++) {
//                    if (bytes[i] > 0) enabled.add(FExprBuilder.lookupFeatureName(i));
//                }
//                break;
//            }
//        }
//        return enabled.toString();
//    }
//
//    static String getAllLowDegreeSolutions(V<Boolean> fe) {
//        List sats = ((BDDV<Boolean>) fe).bdd().allsat();
//        List<String> enabled = new LinkedList<>();
//        StringBuilder sb = new StringBuilder();
//        for (Object sat : sats) {
//            enabled.clear();
//            int current = 0;
//            byte[] bytes = (byte[]) sat;
//            for (int i = 0; i < bytes.length; i++) {
//                if (bytes[i] > 0) current++;
//            }
//            if (current <= GlobalConfig.maxInteractionDegree()) {
//                for (int i = 0; i < bytes.length; i++) {
//                    if (bytes[i] > 0) enabled.add(FExprBuilder.lookupFeatureName(i));
//                }
//                if (sb.toString().equals("")) sb.append(enabled.toString());
//                else sb.append(" or " + enabled.toString());
//            }
//        }
//        return sb.toString();
//    }
}
