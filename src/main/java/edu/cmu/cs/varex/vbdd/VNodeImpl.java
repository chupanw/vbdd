package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.BiConsumerExp;
import edu.cmu.cs.varex.V;
import edu.cmu.cs.varex.fexpr.FeatureExpr;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.function.*;

public class VNodeImpl<T> implements VNode<T> {

    @Nonnull
    private final Symbol symbol;
    @Nullable
    private final VNode<T> low;
    @Nullable
    private final VNode<T> high;

    public VNodeImpl(@Nonnull Symbol symbol, @Nullable VNode<T> low, @Nullable VNode<T> high) {
        this.symbol = symbol;
        this.low = low;
        this.high = high;
    }

    @Override
    public Symbol _symbol() {
        return symbol;
    }


    @Override
    public VNode<T> _low() {
        return low;
    }

    @Override
    public VNode<T> _high() {
        return high;
    }

    @Override
    public boolean _isValue() {
        return false;
    }


    private int hash = 0;
    private boolean initHash = false;

    @Override
    public int hashCode() {
        if (!initHash) {
            hash = symbol.idx + 31 * ((low == null) ? 0 : low.hashCode()) + 27 * ((high == null) ? 0 : high.hashCode());
            initHash = true;
        }
        return hash;
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof VNodeImpl) {
            VNodeImpl t = (VNodeImpl) that;
            return (this.symbol == t.symbol) && (this.low == t.low) && (this.high == t.high);
        }
        return false;
    }

    public VNode<T> union(VNode<T> that) {
        return VBDDFactory.apply((left, right) -> {
            if (left == VBDDFactory.EMPTY) return right;
            if (right == VBDDFactory.EMPTY) return left;
            throw new RuntimeException("attempting union of two overlapping configuration spaces");
        }, this, that);
    }
//    def map[U](f: T => U): VNode[U] = VFactory.this.map(this, f)
//
//
//    def flatMap[U](f: T => VNode[U]): VNode[U] = VFactory.this.flatMap(this, f)
//
//    //update a value in a context
//    def set[U >: T](ctx: VNode[Boolean], value: U): VNode[U] = overwrite(createValue[U](value).select(ctx))
//
//    //overwrites the current value with the given value in the (partial) configuration space in which value is defined
//    def overwrite[U >: T](value: VNode[U]): VNode[U] = apply[T, U, U](
//            (oldV, newV) => if (newV == NOVALUE) oldV else newV
//      , this, value)
//
//
//    def isValue = false
//
//    def isNoValue = false
//
//    override def configSpace() = mapValue[T, Boolean](this, x => createValue(x != NOVALUE))

    @Override
    public T getOne() {
        assert false : "getOne called on Choice: " + this;
        return high.getOne();
    }

    @Override
    public <U> V<? extends U> map(@Nonnull Function<? super T, ? extends U> fun) {
        return VBDDFactory.map(this, (x) -> fun.apply(x));
    }

    @Override
    public <U> V<? extends U> map(@Nonnull BiFunction<V<Boolean>, ? super T, ? extends U> fun) {
        return VBDDFactory.mapValue(this, (x) -> {
            V<Boolean> ctx = this.whenV((a) -> x == a);
            return VBDDFactory.createValue(fun.apply(ctx, x._value()));
        });
    }

    @Override
    public <U> V<? extends U> flatMap(@Nonnull Function<? super T, V<? extends U>> fun) {
        //need to deal with ctxs because of the union trick; there might be a better implementation
        return flatMap((ctx, v) -> fun.apply(v));
    }

    @Override
    public <U> V<? extends U> flatMapNew(@Nonnull Function<? super T, V<? extends U>> fun) {
        HashMap<VValue<T>, VNode<U>> replacements = new HashMap<>();
        for (VNode<T> n : new VNodeIterator<T>(this))
            if (n._isValue() && n != VBDDFactory.EMPTY) {
                V<? extends U> r = fun.apply(((VValue<T>) n)._value());
                replacements.put((VValue<T>) n, (VNode<U>) r);
            }
        return VBDDFactory.nITE(this, replacements, new HashMap<>());
    }


    @Override
    public <U> V<? extends U> flatMap(@Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun) {
        //TODO there might be potential for optimization by avoiding splitting all values and combining them through union
        VNode<U> result = (VNode<U>) VBDDFactory.EMPTY;
        for (VNode<T> n : new VNodeIterator<T>(this))
            if (n._isValue() && n != VBDDFactory.EMPTY) {
                V<Boolean> ctx = this.whenV((x) -> x == n);
                V<? extends U> r = fun.apply(ctx, ((VValue<T>) n)._value());
                result = result.union((VNode<U>) r.select(ctx));
            }
        return result;
    }

    @Override
    public void foreach(@Nonnull Consumer<T> fun) {
        for (VNode<T> n : new VNodeIterator<T>(this))
            if (n._isValue() && n != VBDDFactory.EMPTY)
                fun.accept(((VValue<T>) n)._value());
    }

    @Override
    public void foreach(@Nonnull BiConsumer<V<Boolean>, T> fun) {
        for (VNode<T> n : new VNodeIterator<T>(this))
            if (n._isValue() && n != VBDDFactory.EMPTY) {
                V<Boolean> ctx = this.whenV((x) -> x == n);
                fun.accept(ctx, ((VValue<T>) n)._value());
            }
    }

    @Override
    public void foreachExp(@Nonnull BiConsumerExp<V<Boolean>, T> fun) throws Throwable {
        for (VNode<T> n : new VNodeIterator<T>(this))
            if (n._isValue() && n != VBDDFactory.EMPTY) {
                V<Boolean> ctx = this.whenV((x) -> x == n);
                fun.accept(ctx, ((VValue<T>) n)._value());
            }
    }

    @Override
    public V<Boolean> when(@Nonnull Predicate<T> condition, boolean filterNull) {
        return (V<Boolean>) this.<Boolean>map((x) -> x == null && filterNull ? false : condition.test(x));

    }

    V<Boolean> whenV(@Nonnull Predicate<VValue<T>> condition) {
        return (V<Boolean>) VBDDFactory.mapValueNoEmpty(this, (x) -> (condition.test(x) ? VBDDFactory.TRUE : VBDDFactory.FALSE));

    }

    @Override
    public V<T> select(@Nonnull V<Boolean> configSpace) {
        assert configSpace != null;
        assert FeatureExpr.isTautology(FeatureExpr.implies(configSpace, getConfigSpace())) :
                "selecting under broader condition (" + FeatureExpr.toString(configSpace) + ") than the configuration space described by One (" + FeatureExpr.toString(getConfigSpace()) + ")";

        return reduce(configSpace);
    }

    @Override
    public V<T> reduce(@Nonnull V<Boolean> reducedConfigSpace) {
        return VBDDFactory.<Boolean, T, T>apply(
                (ctx, v) -> ctx._value() ? v : (VValue<T>) VBDDFactory.EMPTY,
                (VNode<Boolean>) reducedConfigSpace, this
        );
    }

    @Override
    public V<Boolean> getConfigSpace() {
        return VBDDFactory.mapValue(this, (x) -> x != VBDDFactory.EMPTY ? VBDDFactory.TRUE : VBDDFactory.FALSE);

    }

    public String toDot() {

        StringBuffer result = new StringBuffer();
        result.append("digraph G {");
        for (VNode<T> b : new VNodeIterator<T>(this)) {
            if (b._isValue())
                result.append(Math.abs(b.hashCode()) + " [shape=box, label=\"" + ((VValue<T>) b)._value() + "\", style=filled, shape=box, height=0.3];");
            else
                result.append(Math.abs(b.hashCode()) + " [label=\"" + b._symbol().getName() + "\"];");
            if (b._low() != null) {
                result.append(Math.abs(b.hashCode()) + " -> " + Math.abs(b._low().hashCode()) + " [style=dotted];");
            }
            if (b._high() != null) {
                result.append(Math.abs(b.hashCode()) + " -> " + Math.abs(b._high().hashCode()) + " [style=filled];");
            }
        }
        result.append("}");
        return result.toString();
    }

}
