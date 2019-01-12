package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.UnimplementedVException;
import edu.cmu.cs.varex.V;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class VBDDFactory {
    public static <U> V<? extends U> choice(V<Boolean> condition, U a, U b) {
        throw new UnimplementedVException();
    }

    static final Symbol VALUEFEATURE = new Symbol(Integer.MAX_VALUE, "<value>");

    final static VNode<?> EMPTY = new VValue<Object>("<EMPTY>") {
        @Override
        public int hashCode() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean equals(Object that) {
            return that==this;
        }

//        @Override
//        public V select(@Nonnull V configSpace) {
//            return EMPTY;
//        }


        //        override def isValue = true
//
//        override def isNoValue = true
//
//        override def select(ctx: VNode[Boolean]): VNode[Nothing] = NOVALUE
//
//        override def flatMap[U](f: (Nothing) => VNode[U]): VNode[U] = NOVALUE
//
//        override def set[U >: Nothing](ctx: VNode[Boolean], value: U) = createValue(value).select(ctx)
//
//        override def overwrite[U >: Nothing](value: VNode[U]) = value
//
//        override def union[U >: Nothing](that: VNode[U]) = that
//
//        override def map[U](f: (Nothing) => U): VNode[U] = NOVALUE
//
//        override def configSpace() = FALSE
    };



    private static final WeakHashMap<Object, WeakReference<VValue<Object>>> valueCache = new WeakHashMap<>();

    public static <T> VValue<T> createValue(T x) {
        return (VValue<T>) VBDDFactory.lookupCache(valueCache, x, () -> new VValue((Object) x));
    }

    public static <T> VNode<T> createChoice(VNode<Boolean> ctx, VNode<T> a, VNode<T> b) {
        return ite(ctx, a,b);
    }


//    def flatMap[T, U](node: VNode[T], f: T => VNode[U]): VNode[U] = flatMap[T, U](node, (_: VNode[Boolean], x: T) => f(x))
//
//    def flatMap[T, U](node: VNode[T], f: (VNode[Boolean], T) => VNode[U]): VNode[U] = {
//        val oldValueNodes = valueNodeIterator(node)
//        var result: VNode[U] = NOVALUE
//
//        for (oldValueNode <- oldValueNodes; if oldValueNode != NOVALUE) {
//            val ctx = node.when(_ == oldValueNode.value)
//            val newValue = f(ctx, oldValueNode.value)
//
//            result = result union newValue.select(ctx)
//        }
//        result
//    }
//
//    public static <T,U> VNode<U> flatMap(VNode<T> node,  Function<T, VNode<U>> f)

//
//
    private static final WeakHashMap<VNode<?>, WeakReference<VNode<?>>> bddTable = new WeakHashMap<>();


    private static <K, V> V lookupCache(WeakHashMap<K, WeakReference<V>> cache, K k, Supplier<V> gen) {
        WeakReference<V> entry = cache.get(k);
        if (entry != null) {
            V ref = entry.get();
            if (ref != null)
                return ref;
        }
        V x = gen.get();
        cache.put(k, new WeakReference(x));
        return x;
    }


    public static <T> VNode<T> mk(Symbol symbol, VNode<T> low, VNode<T> high) {
        if (low == high) return low;

        VNode<?> newNode = new VNodeImpl<>(symbol, low, high);
        return (VNode<T>) lookupCache(bddTable, newNode, () -> newNode);
    }

//    private val boolOpCache: mutable.WeakHashMap[(Boolean, VNode[Boolean], VNode[Boolean]), WeakReference[VNode[Boolean]]] = new mutable.WeakHashMap()


    private static final WeakHashMap<VNode<Boolean>, WeakReference<VNode<Boolean>>> notCache = new WeakHashMap<>();
    private static final WeakHashMap<Util.Pair<VNode<Boolean>, VNode<Boolean>>, WeakReference<VNode<Boolean>>> andCache = new WeakHashMap<>();
    private static final WeakHashMap<Util.Pair<VNode<Boolean>, VNode<Boolean>>, WeakReference<VNode<Boolean>>> orCache = new WeakHashMap<>();

    public static VNode<Boolean> not(VNode<Boolean> bdd) {
        return lookupCache(notCache, bdd, () -> map(bdd, (v) -> !v));
    }

    public static VNode<Boolean> or(VNode<Boolean> a, VNode<Boolean> b) {
        return lookupCache(orCache, new Util.Pair<VNode<Boolean>, VNode<Boolean>>(a, b), () -> applyV((aa, bb) -> aa || bb, a, b));
    }

    public static VNode<Boolean> and(VNode<Boolean> a, VNode<Boolean> b) {
        return lookupCache(andCache, new Util.Pair<VNode<Boolean>, VNode<Boolean>>(a, b), () -> applyV((aa, bb) -> aa && bb, a, b));
    }



    public static <U, T> VNode<U> map(VNode<T> bdd, Function<T, U> f) {
        return mapValue(bdd, (x) -> (x == EMPTY) ? (VValue<U>) EMPTY : createValue(f.apply((T) x._value())));
    }

    static <T, U> VNode<U> mapValue(VNode<T> vbdd, Function<VValue<T>, VValue<U>> f) {
        return _mapValue(vbdd, f, new HashMap<VNode<T>, VNode<U>>());
    }

    private static <T, U> VNode<U> _mapValue(VNode<T> vbdd, Function<VValue<T>, VValue<U>> f, Map<VNode<T>, VNode<U>> rewritten) {
        if (rewritten.containsKey(vbdd)) return rewritten.get(vbdd);
        VNode<U> newNode =
                vbdd._isValue() ?
                        f.apply((VValue<T>) vbdd) :
                        VBDDFactory.mk(vbdd._symbol(), _mapValue(vbdd._low(), f, rewritten), _mapValue(vbdd._high(), f, rewritten));
        rewritten.put(vbdd, newNode);
        return newNode;
    }



    private static Map<String, Symbol> symbols = new HashMap<>();
    private static int symbolCounter = 0;

    private static Symbol mkSymbol(String s) {
        if (symbols.containsKey(s)) return symbols.get(s);
        symbolCounter++;
        Symbol newSymbol = new Symbol(symbolCounter, s);
        symbols.put(s, newSymbol);
        return newSymbol;
    }


    public static final VValue<Boolean> TRUE = createValue(true);
    public static final VValue<Boolean> FALSE = createValue(false);

    public static VNode<Boolean> feature(String s) {
        return VBDDFactory.<Boolean>mk(mkSymbol(s), FALSE, TRUE);
    }

    public static <A, B, C> VNode<C> applyV(BiFunction<A, B, C> op, VNode<A> left, VNode<B> right) {
        return _apply((a, b) -> createValue(op.apply(a._value(), b._value())), left, right, new HashMap<>());
    }

    public static <A, B, C> VNode<C> apply(BiFunction<VValue<A>, VValue<B>, VValue<C>> op, VNode<A> left, VNode<B> right) {
        return _apply(op, left, right, new HashMap<>());
    }

    private static <A, B, C> VNode<C> _apply(BiFunction<VValue<A>, VValue<B>, VValue<C>> op, VNode<A> u1, VNode<B> u2, HashMap<Util.Pair<VNode<A>, VNode<B>>, VNode<C>> cache) {
        Util.Pair<VNode<A>, VNode<B>> pair = new Util.Pair(u1, u2);
        if (cache.containsKey(pair)) return cache.get(pair);
        VNode<C> result;
        if (u1._isValue() && u2._isValue())
            result = op.apply((VValue<A>) u1, (VValue<B>) u2);
        else if (u2._isValue())
            result = mk(u1._symbol(), _apply(op, u1._low(), u2, cache), _apply(op, u1._high(), u2, cache));
        else if (u1._isValue())
            result = mk(u2._symbol(), _apply(op, u1, u2._low(), cache), _apply(op, u1, u2._high(), cache));
        else if (u1._symbol().before(u2._symbol()))
            result = mk(u1._symbol(), _apply(op, u1._low(), u2, cache), _apply(op, u1._high(), u2, cache));
        else if (u2._symbol().before(u1._symbol()))
            result = mk(u2._symbol(), _apply(op, u1, u2._low(), cache), _apply(op, u1, u2._high(), cache));
        else //u1.symbol = u2.symbol
            result = mk(u1._symbol(), _apply(op, u1._low(), u2._low(), cache), _apply(op, u1._high(), u2._high(), cache));
        cache.put(pair, result);
        return result;
    }


    public static <T> VNode<T> ite(VNode<Boolean> f, VNode<T> g, VNode<T> h) {
        return _ite(f, g, h, new HashMap<>());
    }

    private static <T> VNode<T> _ite(VNode<Boolean> f, VNode<T> g, VNode<T> h, Map<Util.Triple<VNode<Boolean>, VNode<T>, VNode<T>>, VNode<T>> cache) {
        if (f == TRUE) return g;
        if (f == FALSE) return h;
        if (g == h) return h;

        Util.Triple<VNode<Boolean>, VNode<T>, VNode<T>> tr = new Util.Triple<>(f, g, h);
        if (cache.containsKey(tr))
            return cache.get(tr);

        Symbol v = f._symbol().min(g._symbol().min(h._symbol()));
        VNode<T> t = _ite(v == f._symbol() ? f._high() : f, v == g._symbol() ? g._high() : g, v == h._symbol() ? h._high() : h, cache);
        VNode<T> e = _ite(v == f._symbol() ? f._low() : f, v == g._symbol() ? g._low() : g, v == h._symbol() ? h._low() : h, cache);
        if (t == e) return e;
        VNode<T> result = mk(v, e, t);
        cache.put(tr, result);
        return result;
    }


    //public API for createValue -- hashConsing
    public static <T> VValue<T> one(T x) {
        return createValue(x);
    }




}
