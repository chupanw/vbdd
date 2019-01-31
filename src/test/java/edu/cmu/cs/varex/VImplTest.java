package edu.cmu.cs.varex;


import edu.cmu.cs.varex.fexpr.FeatureExpr;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by ckaestne on 12/4/2015.
 */
public class VImplTest {

    V<Boolean> foo = VFactory.feature("foo");
    V<Boolean> bar = VFactory.feature("foo");
    V<Boolean> t = VFactory.TRUE;

    V<? extends Integer> c0 = VFactory.one(t, 0);
    V<? extends Integer> c1 = VFactory.one(t, 1);
    V<? extends Integer> c2 = VFactory.one(t, 2);
    V<? extends Integer> c3 = VFactory.one(t, 3);
    V<? extends Integer> c21 = VFactory.choice(foo, c2, c1);
    V<? extends Integer> c32 = VFactory.choice(foo, c3, c2);
    V<? extends Integer> c31 = VFactory.choice(foo, c3, c1);


    @Test
    public void testSimplification() {
        Assert.assertEquals(VFactory.one(t, 1), VFactory.choice(foo, VFactory.one(t, 1), VFactory.one(t, 1)));
        Assert.assertEquals(VFactory.choice(foo, VFactory.one(t, 2), VFactory.one(t, 1)), VFactory.choice(foo, VFactory.choice(foo, VFactory.one(t, 2), VFactory.one(t, 1)), VFactory.one(t, 1)));
    }

    @Test
    public void testFlatMap() {
        Assert.assertEquals(c2, c1.map(k -> k + 1));
        Assert.assertEquals(c2, c1.flatMap(k -> VFactory.one(t, k + 1)));
        Assert.assertEquals(c21, c1.flatMap(k -> VFactory.<Integer>choice(foo, k + 1, k)));
        Assert.assertEquals(c21, c21.flatMap(k -> VFactory.<Integer>choice(foo, k, k)));
        Assert.assertEquals(c31, c21.flatMap(k -> VFactory.<Integer>choice(foo, k + 1, k)));
    }

    private Iterator<Opt<Integer>> list(Opt<Integer>... elements) {
        List<Opt<Integer>> l = new ArrayList<>();
        for (int i = 0; i < elements.length; i++)
            l.add(elements[i]);
        return l.iterator();
    }

    @Test
    public void testVFold() {

        Assert.assertEquals(c3, VList.<Integer,Integer>foldRight(
                list(Opt.create(t, 1), Opt.create(t, 2)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b)
        ));

        Assert.assertEquals(c31, VList.<Integer,Integer>foldRight(
                list(Opt.create(t, 1), Opt.create(foo, 2)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b)
        ));

        Assert.assertEquals(VFactory.choice(foo, VFactory.choice(bar, 4, 3), VFactory.choice(bar, 2, 1)), VList.<Integer,Integer>foldRight(
                list(Opt.create(bar, 1), Opt.create(t, 1), Opt.create(foo, 2)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b)
        ));


    }

    @Test
    public void testVFoldUntil() {

        Assert.assertEquals(c3, VList.<Integer,Integer>foldRightUntil(
                list(Opt.create(t, 1), Opt.create(t, 2)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b)      ,
                x-> x>2
        ));

        Assert.assertEquals(c1, VList.<Integer,Integer>foldRightUntil(
                list(Opt.create(t, 1), Opt.create(foo, 2)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b),
                x-> x>=1
        ));

        Assert.assertEquals(VFactory.choice(foo, 13, 1), VList.<Integer,Integer>foldRightUntil(
                list(Opt.create(t, 1), Opt.create(foo, 2), Opt.create(foo, 10)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b),
                x-> x>1
        ));


        Assert.assertEquals(VFactory.choice(foo, 3, 11), VList.<Integer,Integer>foldRightUntil(
                list(Opt.create(t, 1), Opt.create(foo, 2), Opt.create(FeatureExpr.not(foo), 10), Opt.create(t, 100)), c0, t,
                (c, a, b) -> VFactory.one(t, a + b),
                x-> x>1
        ));

    }

    private void assertEquiv(V<Boolean> expect, V<Boolean> actual) {
        //with BDDs equivalence = equality
        Assert.assertEquals(expect, actual);

    }

    @Test
    public void testConfigSpaces() {
        assertEquiv(VFactory.one(foo, 1).getConfigSpace(),(foo));
        assertEquiv(VFactory.choice(bar, VFactory.one(foo, 1), VFactory.one(foo, 2)).getConfigSpace(),(foo));
        assertEquiv(VFactory.choice(bar, VFactory.one(foo, 1), VFactory.one(t, 2)).getConfigSpace(),(FeatureExpr.orNot(foo,bar)));
        assertEquiv(VFactory.choice(bar, VFactory.one(t, 1), VFactory.one(t, 2)).getConfigSpace(),(t));

        Assert.assertEquals(VFactory.one(foo, 1), VFactory.choice(foo, VFactory.one(t, 1), VFactory.one(t, 2)).select(foo));
        Assert.assertEquals(VFactory.one(foo, 1), VFactory.one(t, 1).select(foo));
        Assert.assertEquals(VFactory.one(FeatureExpr.and(foo,bar), 1), VFactory.one(t, 1).select(foo).select(FeatureExpr.and(foo,bar)));
        Assert.assertEquals(VFactory.EMPTY, c21.select(VFactory.FALSE));
        Assert.assertEquals(VFactory.EMPTY, c21.select(foo).select(VFactory.FALSE));
        Assert.assertEquals(VFactory.EMPTY, VFactory.one(t, 1).select(foo).select(VFactory.FALSE));
        Assert.assertEquals(VFactory.one("a").select(foo), VFactory.choice(foo, VFactory.one(t, "a"), VFactory.one(t, "b")).select(foo));
    }

    @Test
    public void testSMap() {
        Assert.assertEquals(VFactory.one(foo, 2),
                VFactory.choice(foo, 1, 2).smap(foo, p -> p + 1));
        Assert.assertEquals(VFactory.choice(bar, VFactory.one(foo, 2), VFactory.one(foo, 3)),
                VFactory.choice(bar, 1, 2).smap(foo, p -> p + 1));
        Assert.assertEquals(VFactory.one(foo, 2),
                VFactory.one(t, 1).smap(foo, p -> p + 1));
        Assert.assertEquals(VFactory.one(foo, 2),
                VFactory.choice(foo, 1, 2).smap(foo, (c, p) -> {
                    assert c==foo;
                    return p + 1;
                }));
        Assert.assertEquals(VFactory.one(foo, 2),
                VFactory.one(t, 1).smap(foo, (c, p) -> {
                    assert c==(foo);
                    return p + 1;
                }));

        Assert.assertEquals(VFactory.one(foo, 2),
                c1.sflatMap(foo, (c, k) -> VFactory.one(t, k + 1)));
        Assert.assertEquals(VFactory.choice(bar, VFactory.one(foo, 1), VFactory.one(foo, 2)),
                c1.sflatMap(foo, (c, k) -> VFactory.<Integer>choice(bar, k, k + 1)));
    }

    @Test
    public void testPMap() {
        Assert.assertEquals(VFactory.choice(foo, 3, 2),
                VFactory.choice(foo, 1, 2).pmap(foo, p -> p + 2, p -> p));
        Assert.assertEquals(VFactory.choice(foo, 3, 2),
                VFactory.choice(foo, 1, 2).pmap(foo, (Integer p) -> p + 2, Function.identity()));

        Assert.assertEquals(VFactory.choice(foo, VFactory.choice(bar, 2, 3), VFactory.one(1)),
                c1.pflatMap(foo, v -> VFactory.choice(bar, v + 1, v + 2), v -> VFactory.one(v)));
    }

}