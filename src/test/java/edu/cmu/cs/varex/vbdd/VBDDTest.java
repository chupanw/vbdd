package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.V;
import edu.cmu.cs.varex.fexpr.FeatureExpr;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class VBDDTest {


    @BeforeClass
    public static void setupSymbolsInOrder() {
        Symbol a = VBDDFactory.feature("a")._symbol();
        Symbol b = VBDDFactory.feature("b")._symbol();
        Symbol c = VBDDFactory.feature("c")._symbol();
        //ensure symbols were created in order
        Assert.assertTrue(a == a.min(b));
        Assert.assertTrue(a == a.min(c));
        Assert.assertTrue(b == b.min(c));
    }

    @Test
    public void createFeature() {
        VNode<Boolean> f = VBDDFactory.feature("foo");
        Assert.assertEquals("foo", f._symbol().getName());
        Assert.assertEquals(VBDDFactory.TRUE, f._high());
        Assert.assertEquals(VBDDFactory.FALSE, f._low());
//        VNode<Boolean> b = VBDDFactory.feature("bar");
//        System.out.println(f.toDot());
//        System.out.println(b.toDot());
    }

    @Test
    public void createValues() {
        VNode<String> v = VBDDFactory.one("Foo");
        Assert.assertTrue(v instanceof VValue);
        Assert.assertEquals("Foo", ((VValue<String>) v)._value());
        Assert.assertNull(v._high());
        Assert.assertNull(v._low());
    }

    @Test
    public void map() {
        VNode<Boolean> f = VBDDFactory.feature("foo");
        VNode<Integer> i = VBDDFactory.map(f, (x) -> x ? 2 : 3);
        Assert.assertEquals(new Integer(2), ((VValue<Integer>) i._high())._value());
        Assert.assertEquals(new Integer(3), ((VValue<Integer>) i._low())._value());
//        System.out.println(i.toDot());

        VNode<Boolean> b = VBDDFactory.map(f, (x) -> true);
        Assert.assertEquals(VBDDFactory.TRUE, b);
    }


    @Test
    public void ite() {
        Symbol a = VBDDFactory.feature("a")._symbol();
        Symbol b = VBDDFactory.feature("b")._symbol();
        Symbol c = VBDDFactory.feature("c")._symbol();
        //ensure symbols were created in order
        Assert.assertTrue(a == a.min(b));
        Assert.assertTrue(a == a.min(c));
        Assert.assertTrue(b == b.min(c));

        VNode<String> vf = VBDDFactory.one("foo");
        VNode<String> vb = VBDDFactory.one("bar");
        VNode<String> vp = VBDDFactory.one("pin");

        VNode<String> x = VBDDFactory.ite(VBDDFactory.feature("b"), vf, vb);
        Assert.assertEquals(b, x._symbol());
        Assert.assertEquals(vf, x._high());
        Assert.assertEquals(vb, x._low());
        VNode<String> y = VBDDFactory.ite(VBDDFactory.feature("c"), x, vp);
        Assert.assertEquals(b, y._symbol());
        Assert.assertEquals(vf, y._high()._high());
        Assert.assertEquals(vp, y._high()._low());
        Assert.assertEquals(vp, y._low()._low());
        Assert.assertEquals(vb, y._low()._high());
        VNode<String> z = VBDDFactory.ite(VBDDFactory.feature("a"), x, vp);
        Assert.assertEquals(a, z._symbol());
        Assert.assertEquals(vf, z._high()._high());
        Assert.assertEquals(vp, z._low());
        Assert.assertEquals(vb, z._high()._low());

        VNode<Boolean> aandc = VBDDFactory.ite(VBDDFactory.feature("a"),
                VBDDFactory.ite(VBDDFactory.feature("c"), VBDDFactory.TRUE, VBDDFactory.FALSE), VBDDFactory.FALSE);
        VNode<String> xx = VBDDFactory.ite(aandc, vp,
                VBDDFactory.ite(VBDDFactory.feature("b"), vf, vb));
        Assert.assertEquals(vp, xx._high()._high()._high());
        Assert.assertEquals(vp, xx._high()._low()._high());
        Assert.assertEquals(vf, xx._low()._high());
        Assert.assertEquals(vf, xx._high()._high()._low());
        Assert.assertEquals(vb, xx._low()._low());
        Assert.assertEquals(vb, xx._high()._low()._low());

        System.out.println(xx.toDot());
    }

    private <T> VValue<T> one(T x) {
        return VBDDFactory.one(x);
    }

    @Test
    public void apply() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Integer> x = VBDDFactory.ite(a, one(1), one(0));
        VNode<Integer> y = VBDDFactory.ite(b, one(2), one(0));

        VNode<Integer> add = VBDDFactory.applyV((p1, p2) -> p1 + p2, x, y);
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(b, one(3), one(1)), VBDDFactory.ite(b, one(2), one(0))), add);


        VNode<Integer> aa = VBDDFactory.applyV((p1, p2) -> p1 + p2, x, x);
        Assert.assertEquals(VBDDFactory.ite(a, one(2), one(0)), aa);


        Assert.assertEquals(VBDDFactory.applyV((p1, p2) -> p1 + p2, y, x),
                VBDDFactory.applyV((p1, p2) -> p1 + p2, x, y));


    }

    private static <T> void printDot(V<T> v) {
        System.out.println(((VNode<T>) v).toDot());

    }

    @Test
    public void select() {
        VNode<Integer> x = one(1);
        VNode<Integer> y = (VNode<Integer>) x.select(VBDDFactory.feature("a"));
        Assert.assertEquals(one(1), y._high());
        Assert.assertEquals(VBDDFactory.EMPTY, y._low());

        VNode<Integer> z = (VNode<Integer>) y.reduce(VBDDFactory.feature("b"));
        Assert.assertEquals(one(1), z._high()._high());
        Assert.assertEquals(VBDDFactory.EMPTY, z._low());

        Function<Integer, Integer> f = (v) -> v + 1;
        VNode<Integer> zz = (VNode<Integer>) z.<Integer>map(f);
        Assert.assertEquals(one(2), zz._high()._high());
        Assert.assertEquals(VBDDFactory.EMPTY, z._low());
    }

    @Test
    public void when() {
        VNode<Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2));
        Assert.assertEquals(VBDDFactory.feature("a"), x.when((a) -> a == 1, false));
        V<Integer> y = x.select(VBDDFactory.feature("b"));
        Assert.assertEquals(VBDDFactory.feature("a").select(VBDDFactory.feature("b")), y.when((a) -> a == 1, false));

//        printDot(y.when((a) -> a == 1, false));
    }

    @Test
    public void configSpace() {
        V<Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));
        V<Integer> y = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2));
        printDot(y.getConfigSpace());
        printDot(x.getConfigSpace());
        Assert.assertEquals(VBDDFactory.feature("b").select(VBDDFactory.feature("b")), x.getConfigSpace());
        Assert.assertNotEquals(x.getConfigSpace(),y.getConfigSpace());
    }

    @Test
    public void foreach() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        V<Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));

        LinkedList<Util.Pair<V<Boolean>, Integer>> r = new LinkedList<Util.Pair<V<Boolean>, Integer>>();
        x.foreach((ctx, v) -> r.add(new Util.Pair<>(ctx, v)));
//        x.foreach((ctx, v) -> System.out.println(FeatureExpr.toString(ctx)));
        Assert.assertEquals(new Integer(2), r.get(0).b);
        Assert.assertEquals(new Integer(1), r.get(1).b);
        Assert.assertEquals(FeatureExpr.and(FeatureExpr.not(a), b), r.get(0).a);
        Assert.assertEquals(FeatureExpr.and(a, b), r.get(1).a);

    }

    @Test
    public void flatMap() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));
        Function<Integer, V<? extends Integer>> id = (aa) -> this.<Integer>one(aa);
        V<? extends Integer> xy = x.<Integer>flatMap(id);
        Assert.assertEquals(x, xy);
        V<? extends Integer> xx = x.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(c, this.<Integer>one(aa), this.<Integer>one(aa + 10)));
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(c, one(1), one(11)), VBDDFactory.ite(c, one(2), one(12))).select(b), xx);

        V<? extends Integer> y = VBDDFactory.ite(c, one(1), one(11)).select(b);
        V<? extends Integer> yx = y.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(a, this.<Integer>one(aa), this.<Integer>one(aa + 1)));
        Assert.assertEquals(xx, yx);

//        printDot(yx);
    }

    @Test
    public void flatMapNew() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));
        Function<Integer, V<? extends Integer>> id = (aa) -> this.<Integer>one(aa);
        V<? extends Integer> xy = x.<Integer>flatMapNew(id);
        Assert.assertEquals(x, xy);
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(c, this.<Integer>one(aa), this.<Integer>one(aa + 10)));
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(c, one(1), one(11)), VBDDFactory.ite(c, one(2), one(12))).select(b), xx);

        V<? extends Integer> y = VBDDFactory.ite(c, one(1), one(11)).select(b);
        V<? extends Integer> yx = y.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(a, this.<Integer>one(aa), this.<Integer>one(aa + 1)));
        Assert.assertEquals(xx, yx);
    }

    @Test
    public void nITE() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        HashMap<VValue<Boolean>, VNode<Integer>> replacements = new HashMap<>();
        replacements.put(VBDDFactory.TRUE, VBDDFactory.ite(a, one(1), one(2)));
        replacements.put(VBDDFactory.FALSE, VBDDFactory.ite(b, one(1), one(3)));
        VNode<Integer> res = VBDDFactory.nITE(c, replacements);
        System.out.println(res.toDot());
    }

    @Test
    public void flatMapOriginal1() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(c, this.<Integer>one(aa + 5), this.<Integer>one(aa * 10)));
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(c, one(6), one(10)), VBDDFactory.ite(c, one(7), one(20))).select(b), xx);
        printDot(xx);
    }

    @Test
    public void flatMapOriginal2() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        V<? extends Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(b, this.<Integer>one(aa + 5), this.<Integer>one(aa * 10)));
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(b, one(6), one(10)), VBDDFactory.ite(b, one(7), one(20))), xx);
    }

    @Test
    public void flatMapOrdered() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Integer> x = VBDDFactory.ite(VBDDFactory.feature("b"), one(1), one(2)).select(VBDDFactory.feature("c"));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(a, this.<Integer>one(aa + 5), this.<Integer>one(aa * 10)));
        String[] ordering = {"a","b", "c", "<value>"};
        List<String> testOrder = new ArrayList<String>();

        for (VNode<Integer> n : new VNodeIterator<Integer>((VNode<Integer>) xx))
            if(!testOrder.contains(n._symbol().getName())) {
                testOrder.add(n._symbol().getName());
            }
        Assert.assertArrayEquals(ordering,testOrder.toArray());
        for(int i = 0; i <4; i++) {
            System.out.println(ordering[i] + " " + testOrder.get(i));
        }

    }

    @Test
    public void flatMapReduced() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Boolean> x = VBDDFactory.ite(VBDDFactory.feature("a"),VBDDFactory.ite(b, one(true), one(true)),VBDDFactory.ite(b, one(false), one(true)));
        V<? extends Boolean> xx = x.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(c, this.<Boolean>one(!aa) , this.<Boolean>one(aa )));

        List<String> testReduced = new ArrayList<String>();
        String[] reducedLeafNodes = {"One(true)", "One(false)"};
        for (VNode<Boolean> n : new VNodeIterator<Boolean>((VNode<Boolean>) xx))
            if(n._isValue()) {
                testReduced.add(n.toString());
            }
        Assert.assertEquals(testReduced.size(),2);
        Assert.assertArrayEquals(testReduced.toArray(),reducedLeafNodes);
    }

    @Test
    public void flatMapBoolean() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Boolean> x = VBDDFactory.ite(VBDDFactory.feature("a"),VBDDFactory.ite(b, one(true), one(true)),VBDDFactory.ite(b, one(false), one(true)));
        V<? extends Boolean> xx = x.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(c, this.<Boolean>one(!aa) , this.<Boolean>one(aa )));
        printDot(xx);
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(b, VBDDFactory.ite(c, one(false), one(true)), VBDDFactory.ite(c, one(false), one(true))), VBDDFactory.ite(b, VBDDFactory.ite(c, one(true), one(false)), VBDDFactory.ite(c, one(false), one(true)))), xx);
    }

    @Test
    public void flatMapStrings() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        V<? extends String> x = VBDDFactory.ite(VBDDFactory.feature("a"), one("Sleep"), one("Work"));
        V<? extends String> xx = x.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(b, this.<String>one(aa + " Now!"), this.<String>one(aa + " Later!")));
        printDot(xx);
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(b, one("Sleep Now!"), one("Sleep Later!")), VBDDFactory.ite(b, one("Work Now!"), one("Work Later!"))), xx);
    }

    @Test
    public void flatMap3Attributes() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Integer> x = VBDDFactory.ite(a, VBDDFactory.ite(b,  one(3), one(2)),  VBDDFactory.ite(b, one(4), one(5)));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(c, one(aa), this.<Integer>one(aa + 2)));
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(b, VBDDFactory.ite(c, one(3), one(5)), VBDDFactory.ite(c, one(2), one(4))), VBDDFactory.ite(b, VBDDFactory.ite(c, one(4), one(6)), VBDDFactory.ite(c, one(5), one(7)))), xx);
        printDot(xx);
    }

    @Test
    public void flatMap2Attributes() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        V<? extends Integer> x = VBDDFactory.ite(a, one(2), one(4));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(b, one(aa), this.<Integer>one(aa + 2)));
        Assert.assertEquals(VBDDFactory.ite(a, VBDDFactory.ite(b, one(2), one(4)), VBDDFactory.ite(b, one(4), one(6))), xx);
        printDot(xx);
    }

    @Test
    public void compareToOld1() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        V<? extends Integer> x = VBDDFactory.ite(a, one(2), one(4));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(b, one(aa), this.<Integer>one(aa + 2)));
        Assert.assertEquals(x.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(b, one(aa), this.<Integer>one(aa + 2))), xx);
        printDot(xx);
    }

    @Test
    public void compareToOld2() {
        VNode<Boolean> a = VBDDFactory.feature("a");
        VNode<Boolean> b = VBDDFactory.feature("b");
        VNode<Boolean> c = VBDDFactory.feature("c");
        V<? extends Integer> x = VBDDFactory.ite(a, VBDDFactory.ite(b,  one(3), one(2)),  VBDDFactory.ite(b, one(4), one(5)));
        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(c, one(aa), this.<Integer>one(aa + 2)));
        Assert.assertEquals(x.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(c, one(aa), this.<Integer>one(aa + 2))), xx);
        printDot(xx);
    }

//    @Test
//    public void manyAttributes() {
//        VNode<Boolean> a = VBDDFactory.feature("a");
//        VNode<Boolean> b = VBDDFactory.feature("b");
//        VNode<Boolean> c = VBDDFactory.feature("c");
//        VNode<Boolean> d = VBDDFactory.feature("d");
//        VNode<Boolean> e = VBDDFactory.feature("e");
//        V<? extends Integer> x = VBDDFactory.ite(a, VBDDFactory.ite(b,  VBDDFactory.ite(c,  VBDDFactory.ite(d,  one(3), one(2)), VBDDFactory.ite(d,  one(3), one(2))), VBDDFactory.ite(c,  VBDDFactory.ite(d,  one(3), one(2)), VBDDFactory.ite(d,  one(3), one(2)))),  VBDDFactory.ite(b,  VBDDFactory.ite(c,  VBDDFactory.ite(d,  one(3), one(2)), VBDDFactory.ite(d,  one(3), one(2))), VBDDFactory.ite(c,  VBDDFactory.ite(d,  one(6), one(7)), VBDDFactory.ite(d,  one(8), one(9)))));
//        V<? extends Integer> xx = x.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(e, one(aa), this.<Integer>one(aa + 2)));
//        Assert.assertEquals(x.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(e, one(aa), this.<Integer>one(aa + 2))), xx);
//        printDot(xx);
//    }

    @Test
    public void testGenerator() {
        new TestGenerator(10,3);
    }
}
