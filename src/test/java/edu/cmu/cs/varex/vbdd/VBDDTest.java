package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.V;
import edu.cmu.cs.varex.fexpr.FeatureExpr;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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

        VNode<Integer> z = (VNode<Integer>) y.select(VBDDFactory.feature("b"));
        Assert.assertEquals(one(1), z._high()._high());
        Assert.assertEquals(VBDDFactory.EMPTY, z._low());

        Function<Integer, Integer> f = (v) -> v + 1;
        VNode<Integer> zz = (VNode<Integer>) z.<Integer>map(f);
        Assert.assertEquals(one(2), z._high()._high());
        Assert.assertEquals(VBDDFactory.EMPTY, z._low());
    }

    @Test
    public void when() {
        VNode<Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2));
        Assert.assertEquals(VBDDFactory.feature("a"), x.when((a) -> a == 1, false));
        V<Integer> y = x.select(VBDDFactory.feature("b"));
        Assert.assertEquals(VBDDFactory.feature("a").select(VBDDFactory.feature("b")), y.when((a) -> a == 1, false));

        printDot(y.when((a) -> a == 1, false));
    }

    @Test
    public void configSpace() {
        V<Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));
        printDot(x);
        printDot(x.getConfigSpace());
        Assert.assertEquals(VBDDFactory.feature("b"), x.getConfigSpace());
    }

    @Test
    public void foreach() {
        V<Integer> x = VBDDFactory.ite(VBDDFactory.feature("a"), one(1), one(2)).select(VBDDFactory.feature("b"));

        x.foreach((ctx, v) -> System.out.println(FeatureExpr.toString(ctx) + " - " + v));

    }
}
