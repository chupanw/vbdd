package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.UnimplementedVException;
import edu.cmu.cs.varex.V;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class VBDDFactory {
    public static <U> V<? extends U> choice(V<Boolean> condition, U a, U b) {
        throw new UnimplementedVException();
    }

    static final Symbol VALUEFEATURE = new Symbol(Integer.MAX_VALUE, "<value>");

    final static VNode<?> EMPTY = new VNodeImpl(VALUEFEATURE, null, null) {
        @Override
        public int hashCode() {
            return -1;
        }

        @Override
        public boolean equals(Object that) {
            return false;
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


    //    private val valueCache: mutable.WeakHashMap[Any, WeakReference[VValue[Any]]] = new mutable.WeakHashMap()
//
//    def createValue[T](x: T): VValue[T] = {
//        val v = valueCache.get(x).flatMap(_.get)
//        if (v.isDefined) v.get.asInstanceOf[VValue[T]]
//        else {
//            val xv = new Value[T](x)
//                    valueCache.put(x, WeakReference.apply[VValue[Any]](xv))
//            xv
//        }
//    }

    private static final WeakHashMap<Object, WeakReference<VValue<Object>>> valueCache = new WeakHashMap<>();

    static <T> VValue<T> createValue(T x) {
        return (VValue<T>) VBDDFactory.lookupCache(valueCache, x, () -> new VValue((Object) x));
    }

    //
//
//    def createChoice[T](ctx: VNode[Boolean], a: VNode[T], b: VNode[T]): VNode[T] = ite(ctx, a, b)
//
//    //    a.select(ctx) union b.select(ctx.not)
//
//    def apply[A, B, C](op: (VValue[A], VValue[B]) => VValue[C], left: VNode[A], right: VNode[B]): VNode[C] = {
//        var cache: Map[(VNode[A], VNode[B]), VNode[C]] = Map()
//
//        def app(u1: VNode[A], u2: VNode[B]): VNode[C] = {
//                val cached = cache.get((u1, u2))
//        if (cached.nonEmpty)
//            return cached.get
//
//        val u =
//        if (u1.isValue && u2.isValue)
//            op(u1.asInstanceOf[VValue[A]], u2.asInstanceOf[VValue[B]])
//        else if (u2.isValue)
//            mk(u1.v, app(u1.low, u2), app(u1.high, u2))
//        else if (u1.isValue)
//            mk(u2.v, app(u1, u2.low), app(u1, u2.high))
//        else if (u1.v < u2.v)
//            mk(u1.v, app(u1.low, u2), app(u1.high, u2))
//        else if (u1.v > u2.v)
//            mk(u2.v, app(u1, u2.low), app(u1, u2.high))
//        else //if (u1.v==u2.v)
//            mk(u1.v, app(u1.low, u2.low), app(u1.high, u2.high))
//
//        cache += ((u1, u2) -> u)
//        u
//    }
//
//        app(left, right)
//    }
//
//
//    def ite[T](f: VNode[Boolean], g: VNode[T], h: VNode[T]): VNode[T] = {
//        var cache: Map[(VNode[Boolean], VNode[T], VNode[T]), VNode[T]] = Map()
//
//        def ite_(f: VNode[Boolean], g: VNode[T], h: VNode[T]): VNode[T] = {
//        if (f eq TRUE) g
//      else if (f eq FALSE) h
//      else if (g eq h) h
//        //      else if ((g eq TRUE) && (h eq FALSE)) f
//        //      else if ((g eq FALSE) && (h eq TRUE)) f.not
//      else {
//            if ((g eq TRUE) && (h eq FALSE)) println("TODO missed opportunity for booleans")
//            if ((g eq FALSE) && (h eq TRUE)) println("TODO missed opportunity for booleans")
//            val cached = cache.get((f, g, h))
//            if (cached.nonEmpty)
//                return cached.get
//
//        import Math._
//            val v = min(f.v, min(g.v, h.v))
//
//            val t = ite_(if (v == f.v) f.high else f, if (v == g.v) g.high else g, if (v == h.v) h.high else h)
//            val e = ite_(if (v == f.v) f.low else f, if (v == g.v) g.low else g, if (v == h.v) h.low else h)
//            if (t eq e) return e
//            val result = mk(v, e, t)
//            cache += ((f, g, h) -> result)
//            result
//        }
//    }
//
//        ite_(f, g, h)
//    }
//
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

    //
//    private val notCache: mutable.WeakHashMap[VNode[Boolean], WeakReference[VNode[Boolean]]] = new mutable.WeakHashMap()
//    private val boolOpCache: mutable.WeakHashMap[(Boolean, VNode[Boolean], VNode[Boolean]), WeakReference[VNode[Boolean]]] = new mutable.WeakHashMap()
//
//    def not(bdd: VNode[Boolean]): VNode[Boolean] = lookupCache(notCache, bdd, {
//        map[Boolean, Boolean](bdd, x => !x)
//    })
//
//
//    def map[T, U](bdd: VNode[T], f: T => U): VNode[U] = mapValue[T, U](bdd,
//    x => if (x == NOVALUE) NOVALUE else createValue(f(x.value))
//            )


    public static <U, T> VNode<U> map(VNode<T> bdd, Function<T, U> f) {
        return mapValue(bdd, (x) -> (x == EMPTY) ? (VValue<U>) EMPTY : createValue(f.apply((T) x._value())));
    }
//

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


    //    def mapPair[T, U, V](a: VNode[T], b: VNode[U], f: (T, U) => V): VNode[V] =
//    apply[T, U, V]((aa, bb) => createValue[V](f(aa.value, bb.value)), a, b)
//
    private static Map<String, Symbol> symbols = new HashMap<>();

    //
//    def varNum = symbols.size
//
    private static Symbol mkSymbol(String s) {
        if (symbols.containsKey(s)) return symbols.get(s);
        Symbol newSymbol = new Symbol(symbols.size(), s);
        symbols.put(s, newSymbol);
        return newSymbol;
    }


    public static final VValue<Boolean> TRUE = createValue(true);
    public static final VValue<Boolean> FALSE = createValue(false);

    public static VNode<Boolean> feature(String s) {
        return VBDDFactory.<Boolean>mk(mkSymbol(s), FALSE, TRUE);
    }


    //    def apply[A, B, C](op: (VValue[A], VValue[B]) => VValue[C], left: VNode[A], right: VNode[B]): VNode[C] = {
//        var cache: Map[(VNode[A], VNode[B]), VNode[C]] = Map()
//
//        def app(u1: VNode[A], u2: VNode[B]): VNode[C] = {
//                val cached = cache.get((u1, u2))
//        if (cached.nonEmpty)
//            return cached.get
//
//        val u =
//        if (u1.isValue && u2.isValue)
//            op(u1.asInstanceOf[VValue[A]], u2.asInstanceOf[VValue[B]])
//        else if (u2.isValue)
//            mk(u1.v, app(u1.low, u2), app(u1.high, u2))
//        else if (u1.isValue)
//            mk(u2.v, app(u1, u2.low), app(u1, u2.high))
//        else if (u1.v < u2.v)
//            mk(u1.v, app(u1.low, u2), app(u1.high, u2))
//        else if (u1.v > u2.v)
//            mk(u2.v, app(u1, u2.low), app(u1, u2.high))
//        else //if (u1.v==u2.v)
//            mk(u1.v, app(u1.low, u2.low), app(u1.high, u2.high))
//
//        cache += ((u1, u2) -> u)
//        u
//    }
//
//        app(left, right)
//    }
//
    public static <T> VNode<T> ite(VNode<Boolean> f, VNode<T> g, VNode<T> h) {
        return _ite(f, g, h, new HashMap<>());
    }

    private static <T> VNode<T> _ite(VNode<Boolean> f, VNode<T> g, VNode<T> h, Map<Triple<VNode<Boolean>, VNode<T>, VNode<T>>, VNode<T>> cache) {
        if (f == TRUE) return g;
        if (f == FALSE) return h;
        if (g == h) return h;

        Triple<VNode<Boolean>, VNode<T>, VNode<T>> tr = new Triple<>(f, g, h);
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
    public static <T> VNode<T> one(T x) {
        return createValue(x);
    }


    private static class Triple<A, B, C> {
        private final A a;
        private final B b;
        private final C c;

        private Triple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int hashCode() {
            return a.hashCode() + b.hashCode() + c.hashCode();
        }

        public boolean equals(Object t) {
            if (t instanceof Triple) {
                Triple<A, B, C> that = (Triple<A, B, C>) t;
                return this.a == that.a && this.b == that.b && this.c == that.c;
            }
            return false;
        }
    }

}
