package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.BiConsumerExp;
import edu.cmu.cs.varex.V;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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


//    def select(ctx: VNode[Boolean]): VNode[T] = apply[Boolean, T, T](
//            (ctx, v) => if (ctx.value) v else NOVALUE.asInstanceOf[VValue[T]]
//            , ctx, this)
//
//    def union[U >: T](that: VNode[U]): VNode[U] = apply[T, U, U](
//            (left, right) => if (left eq NOVALUE) right else if (right eq NOVALUE) left else throw new VNodeException("attempting union of two overlapping configuration spaces")
//      , this, that)
//
//
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
        return null;
    }

    @Override
    public <U> V<? extends U> flatMap(@Nonnull Function<? super T, V<? extends U>> fun) {
        return null;
    }

    @Override
    public <U> V<? extends U> flatMap(@Nonnull BiFunction<V<Boolean>, ? super T, V<? extends U>> fun) {
        return null;
    }

    @Override
    public void foreach(@Nonnull Consumer<T> fun) {

    }

    @Override
    public void foreach(@Nonnull BiConsumer<V<Boolean>, T> fun) {

    }

    @Override
    public void foreachExp(@Nonnull BiConsumerExp<V<Boolean>, T> fun) throws Throwable {

    }

    @Override
    public V<Boolean> when(@Nonnull Predicate<T> condition, boolean filterNull) {
        return null;
    }

    @Override
    public V<T> select(@Nonnull V<Boolean> configSpace) {
        return null;
    }

    @Override
    public V<T> reduce(@Nonnull V<Boolean> reducedConfigSpace) {
        return null;
    }

    @Override
    public V<Boolean> getConfigSpace() {
        return null;
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
