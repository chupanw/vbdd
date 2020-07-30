package edu.cmu.cs.varex.vbdd;

import edu.cmu.cs.varex.V;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class VBDDCorrectnessTests {

    private <T> VValue<T> one(T x) {
        return VBDDFactory.one(x);
    }

    @Test
    public void flatMap1() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("mouse"), one("cat"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("cat"), one("rabbit"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap2() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(3), one(5)), VBDDFactory.ite(var2, one(5), one(2))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(3)), VBDDFactory.ite(var2, one(1), one(5))));
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(4 * aa)));
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(4 * aa)));
        Assert.assertEquals(oldMap, newMap);
    }

    @Test
    public void flatMap3() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(true), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(true), one(false));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap4() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(true)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(false), one(true)))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(true), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(true)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(false)))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var4, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var4, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap5() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(2), one(4)), VBDDFactory.ite(var1, one(4), one(1)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(4), one(4)), VBDDFactory.ite(var1, one(1), one(5)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(3 * aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(3 * aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap6() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, one(5), one(4));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, one(4), one(2));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(3 / aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(3 / aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap7() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(false), one(true));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(false), one(true));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap8() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(false), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(true), one(true));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap9() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("mouse")), VBDDFactory.ite(var3, one("rabbit"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("cat"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("mouse"), one("mouse")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap10() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("mouse"), one("cat")), VBDDFactory.ite(var1, one("mouse"), one("dog")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("rabbit")), VBDDFactory.ite(var1, one("cat"), one("dog")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap11() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("cat")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("mouse"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("mouse"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("dog")), VBDDFactory.ite(var3, one("cat"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("mouse"), one("mouse")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("cat")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("rabbit")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap12() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("cat"), one("dog")), VBDDFactory.ite(var2, one("rabbit"), one("dog"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("rabbit"), one("cat")), VBDDFactory.ite(var2, one("dog"), one("rabbit"))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("cat"), one("mouse")), VBDDFactory.ite(var2, one("rabbit"), one("dog"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("cat"), one("dog")), VBDDFactory.ite(var2, one("mouse"), one("mouse"))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap13() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(true)), VBDDFactory.ite(var1, one(true), one(false)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(true)), VBDDFactory.ite(var1, one(false), one(true)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap14() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("dog")), VBDDFactory.ite(var3, one("mouse"), one("dog")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("rabbit")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap15() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, one(3), one(5));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, one(1), one(3));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap16() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("rabbit"), one("cat")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("dog"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("dog")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap17() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(1)), VBDDFactory.ite(var2, one(1), one(2))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(1), one(1)), VBDDFactory.ite(var2, one(2), one(3))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(2)), VBDDFactory.ite(var2, one(2), one(5))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(2), one(5)), VBDDFactory.ite(var2, one(3), one(1))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(2 / aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(2 / aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap18() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(true)), VBDDFactory.ite(var1, one(true), one(true)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(true)), VBDDFactory.ite(var1, one(true), one(false)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap19() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(2)), VBDDFactory.ite(var3, one(4), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(5)), VBDDFactory.ite(var3, one(5), one(5)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(3)), VBDDFactory.ite(var3, one(2), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(2)), VBDDFactory.ite(var3, one(2), one(4)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(3), one(5))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(5)), VBDDFactory.ite(var3, one(1), one(3)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(1)), VBDDFactory.ite(var3, one(1), one(3))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(3)), VBDDFactory.ite(var3, one(1), one(3)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(4 * aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(4 * aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap20() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(true), one(false)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(true)))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(false), one(false)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(true)))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap21() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("rabbit"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("mouse"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("cat")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("mouse"), one("mouse")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("cat")), VBDDFactory.ite(var3, one("dog"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("rabbit")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap22() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("cat")), VBDDFactory.ite(var1, one("dog"), one("mouse")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("mouse")), VBDDFactory.ite(var1, one("rabbit"), one("dog")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap23() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("mouse"), one("mouse"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("cat"), one("rabbit"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap24() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, one(2), one(4));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, one(2), one(5));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(1 % aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(1 % aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap25() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(false)), VBDDFactory.ite(var2, one(true), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(false)), VBDDFactory.ite(var2, one(false), one(true))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(true)), VBDDFactory.ite(var2, one(true), one(true))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(true)), VBDDFactory.ite(var2, one(true), one(false))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap26() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("mouse"), one("dog"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("rabbit"), one("mouse"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap27() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(true), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(false), one(true));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap28() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("rabbit"), one("mouse"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("rabbit"), one("cat"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap29() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("dog"), one("mouse")), VBDDFactory.ite(var2, one("rabbit"), one("cat"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("mouse"), one("cat")), VBDDFactory.ite(var2, one("mouse"), one("cat"))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("mouse"), one("dog")), VBDDFactory.ite(var2, one("rabbit"), one("dog"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("dog"), one("dog")), VBDDFactory.ite(var2, one("mouse"), one("dog"))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap30() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(5), one(1)), VBDDFactory.ite(var1, one(5), one(2)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(3), one(5)), VBDDFactory.ite(var1, one(1), one(4)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(1 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(1 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap31() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("mouse"), one("cat")), VBDDFactory.ite(var2, one("dog"), one("rabbit"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("cat"), one("cat")), VBDDFactory.ite(var2, one("cat"), one("rabbit"))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("dog"), one("cat")), VBDDFactory.ite(var2, one("rabbit"), one("dog"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("dog"), one("mouse")), VBDDFactory.ite(var2, one("cat"), one("cat"))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap32() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("mouse")), VBDDFactory.ite(var1, one("rabbit"), one("rabbit")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("mouse")), VBDDFactory.ite(var1, one("rabbit"), one("rabbit")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap33() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(true), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(false), one(false));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap34() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("cat")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("cat")), VBDDFactory.ite(var3, one("dog"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("cat")), VBDDFactory.ite(var3, one("dog"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("dog")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap35() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("dog"), one("dog"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("dog"), one("mouse"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap36() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("mouse")), VBDDFactory.ite(var1, one("cat"), one("cat")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("dog")), VBDDFactory.ite(var1, one("rabbit"), one("mouse")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap37() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(4)), VBDDFactory.ite(var2, one(4), one(1))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(2)), VBDDFactory.ite(var2, one(1), one(2))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(1)), VBDDFactory.ite(var2, one(3), one(2))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(1), one(3)), VBDDFactory.ite(var2, one(1), one(5))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(4 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(4 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap38() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(false)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(true)))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(true)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(true), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false)))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap39() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(true), one(true))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(true)), VBDDFactory.ite(var2, one(false), one(false))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(true), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(true), one(true))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one(true || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one(true || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap40() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("dog")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("rabbit"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("dog")), VBDDFactory.ite(var3, one("cat"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("dog"), one("rabbit")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var3, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap41() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(false)), VBDDFactory.ite(var1, one(true), one(false)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(true)), VBDDFactory.ite(var1, one(true), one(false)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap42() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(true), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(false), one(false));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap43() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(true)), VBDDFactory.ite(var2, one(true), one(true))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(false), one(true))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(true)), VBDDFactory.ite(var2, one(true), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(true), one(true))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap44() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("mouse"), one("cat")), VBDDFactory.ite(var1, one("mouse"), one("rabbit")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("mouse"), one("cat")), VBDDFactory.ite(var1, one("cat"), one("rabbit")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap45() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(true)), VBDDFactory.ite(var1, one(false), one(false)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(false)), VBDDFactory.ite(var1, one(true), one(false)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(true || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(true || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap46() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(2), one(1)), VBDDFactory.ite(var1, one(5), one(2)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(1), one(1)), VBDDFactory.ite(var1, one(1), one(3)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(1 / aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(1 / aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap47() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("dog")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("rabbit"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("mouse")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap48() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(3), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(1), one(1)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(3)), VBDDFactory.ite(var3, one(2), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(3), one(3)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(3)), VBDDFactory.ite(var3, one(5), one(3))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(4)), VBDDFactory.ite(var3, one(4), one(2)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(1)), VBDDFactory.ite(var3, one(3), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(1)), VBDDFactory.ite(var3, one(3), one(1)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(2 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(2 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap49() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(2), one(1)), VBDDFactory.ite(var1, one(4), one(3)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(2), one(4)), VBDDFactory.ite(var1, one(4), one(3)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap50() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("mouse"), one("dog"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("cat"), one("dog"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap51() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("rabbit"), one("cat")), VBDDFactory.ite(var2, one("dog"), one("mouse"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("rabbit"), one("mouse")), VBDDFactory.ite(var2, one("dog"), one("dog"))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("cat"), one("cat")), VBDDFactory.ite(var2, one("dog"), one("dog"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("dog"), one("mouse")), VBDDFactory.ite(var2, one("mouse"), one("cat"))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap52() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(true)), VBDDFactory.ite(var1, one(false), one(false)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(true)), VBDDFactory.ite(var1, one(true), one(false)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap53() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("rabbit"), one("mouse"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("cat"), one("mouse"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap54() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(3)), VBDDFactory.ite(var2, one(5), one(1))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(3), one(2)), VBDDFactory.ite(var2, one(4), one(1))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(2), one(5)), VBDDFactory.ite(var2, one(1), one(4))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(3), one(2)), VBDDFactory.ite(var2, one(4), one(1))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(5 % aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(5 % aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap55() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("rabbit"), one("cat"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("cat"), one("dog"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap56() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(false)), VBDDFactory.ite(var1, one(false), one(false)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(false)), VBDDFactory.ite(var1, one(true), one(true)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap57() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(false)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(false), one(true)))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(true), one(false)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(true)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(true)))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(false && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap58() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(2), one(5)), VBDDFactory.ite(var1, one(1), one(2)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(1), one(3)), VBDDFactory.ite(var1, one(3), one(4)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(1 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(1 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap59() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("dog")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("cat")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap60() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(3)), VBDDFactory.ite(var3, one(3), one(3))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(1), one(2)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(2)), VBDDFactory.ite(var3, one(2), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(2)), VBDDFactory.ite(var3, one(3), one(1)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(4)), VBDDFactory.ite(var3, one(3), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(3)), VBDDFactory.ite(var3, one(5), one(3)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(5), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(4), one(1)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(5 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(5 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap61() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(4)), VBDDFactory.ite(var3, one(2), one(1))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(2)), VBDDFactory.ite(var3, one(4), one(4)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(5)), VBDDFactory.ite(var3, one(2), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(4)), VBDDFactory.ite(var3, one(1), one(5)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(4)), VBDDFactory.ite(var3, one(4), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(1), one(1)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(2)), VBDDFactory.ite(var3, one(2), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(3)), VBDDFactory.ite(var3, one(3), one(5)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(1 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(1 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap62() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(true)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false)))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(true)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(false)))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap63() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, one(3), one(4));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, one(4), one(5));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap64() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(3), one(5))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(4)), VBDDFactory.ite(var3, one(3), one(2)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(3)), VBDDFactory.ite(var3, one(5), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(3)), VBDDFactory.ite(var3, one(4), one(3)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(5)), VBDDFactory.ite(var3, one(1), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(4)), VBDDFactory.ite(var3, one(5), one(2)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(4)), VBDDFactory.ite(var3, one(4), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(1)), VBDDFactory.ite(var3, one(2), one(4)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(4 / aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(4 / aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap65() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(4), one(1)), VBDDFactory.ite(var1, one(4), one(2)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(4), one(4)), VBDDFactory.ite(var1, one(5), one(2)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(5 / aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(5 / aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap66() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("cat"), one("cat"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("dog"), one("rabbit"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap67() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("rabbit"), one("mouse")), VBDDFactory.ite(var1, one("dog"), one("mouse")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("mouse"), one("cat")), VBDDFactory.ite(var1, one("mouse"), one("dog")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap68() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("rabbit"), one("cat")), VBDDFactory.ite(var1, one("dog"), one("dog")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("dog")), VBDDFactory.ite(var1, one("rabbit"), one("cat")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap69() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(false)), VBDDFactory.ite(var2, one(true), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(false), one(false))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(true)), VBDDFactory.ite(var2, one(true), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(true)), VBDDFactory.ite(var2, one(false), one(true))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap70() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("mouse")), VBDDFactory.ite(var3, one("mouse"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("rabbit")), VBDDFactory.ite(var3, one("rabbit"), one("cat")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("dog")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("cat"), one("cat")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap71() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(5), one(3)), VBDDFactory.ite(var1, one(3), one(4)));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(3), one(3)), VBDDFactory.ite(var1, one(3), one(1)));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(3 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(3 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap72() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(2)), VBDDFactory.ite(var3, one(2), one(3))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(2)), VBDDFactory.ite(var3, one(4), one(4)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(5), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(4)), VBDDFactory.ite(var3, one(3), one(2)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(2), one(5))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(2)), VBDDFactory.ite(var3, one(4), one(2)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(2)), VBDDFactory.ite(var3, one(3), one(1))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(3)), VBDDFactory.ite(var3, one(1), one(5)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(5 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(5 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap73() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("mouse"), one("rabbit")), VBDDFactory.ite(var1, one("cat"), one("dog")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("cat")), VBDDFactory.ite(var1, one("mouse"), one("mouse")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap74() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(1), one(2)), VBDDFactory.ite(var2, one(2), one(5))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(5)), VBDDFactory.ite(var2, one(1), one(1))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(1), one(2)), VBDDFactory.ite(var2, one(1), one(2))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(2)), VBDDFactory.ite(var2, one(1), one(1))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 % aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 % aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap75() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(2), one(1)), VBDDFactory.ite(var2, one(5), one(5))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(2), one(3)), VBDDFactory.ite(var2, one(4), one(1))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(2), one(3)), VBDDFactory.ite(var2, one(5), one(2))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(3), one(4)), VBDDFactory.ite(var2, one(5), one(5))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 / aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(2 / aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap76() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(true), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(false), one(true));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var1, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap77() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(4)), VBDDFactory.ite(var3, one(5), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(1), one(3)), VBDDFactory.ite(var3, one(4), one(2)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(3)), VBDDFactory.ite(var3, one(4), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(2)), VBDDFactory.ite(var3, one(1), one(5)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(2)), VBDDFactory.ite(var3, one(1), one(3))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(3), one(1)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(5)), VBDDFactory.ite(var3, one(2), one(2))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(5)), VBDDFactory.ite(var3, one(3), one(5)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(3 % aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(3 % aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap78() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("mouse"), one("cat"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("mouse"), one("mouse"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap79() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(true), one(false)), VBDDFactory.ite(var3, one(true), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(true), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(true)))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(true), one(true))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(true)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(true)), VBDDFactory.ite(var3, one(false), one(false))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(false), one(false)), VBDDFactory.ite(var3, one(false), one(false)))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap80() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("dog")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("dog")), VBDDFactory.ite(var3, one("mouse"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("cat"), one("cat")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("mouse")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("cat"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("cat")), VBDDFactory.ite(var3, one("rabbit"), one("dog")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap81() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, one(4), one(3));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, one(4), one(4));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(4 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(4 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap82() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(false)), VBDDFactory.ite(var1, one(false), one(false)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(true)), VBDDFactory.ite(var1, one(false), one(false)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var2, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap83() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(2), one(3)), VBDDFactory.ite(var2, one(1), one(5))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(2)), VBDDFactory.ite(var2, one(1), one(2))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(3)), VBDDFactory.ite(var2, one(4), one(1))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(4)), VBDDFactory.ite(var2, one(1), one(2))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(5 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var0, this.<Integer>one( aa ), this.<Integer>one(5 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap84() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(2)), VBDDFactory.ite(var2, one(2), one(2))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(5)), VBDDFactory.ite(var2, one(3), one(1))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(4)), VBDDFactory.ite(var2, one(2), one(4))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(5), one(3)), VBDDFactory.ite(var2, one(4), one(2))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(4 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(4 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap85() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(true), one(false)), VBDDFactory.ite(var1, one(false), one(true)));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one(false), one(true)), VBDDFactory.ite(var1, one(false), one(false)));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap86() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(3), one(5)), VBDDFactory.ite(var2, one(3), one(4))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(3), one(3)), VBDDFactory.ite(var2, one(1), one(5))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(4), one(4)), VBDDFactory.ite(var2, one(5), one(1))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(1), one(5)), VBDDFactory.ite(var2, one(4), one(4))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(3 * aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var3, this.<Integer>one( aa ), this.<Integer>one(3 * aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap87() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("cat")), VBDDFactory.ite(var3, one("cat"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("dog")), VBDDFactory.ite(var3, one("rabbit"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("rabbit"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("rabbit")), VBDDFactory.ite(var3, one("dog"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("rabbit"), one("dog")), VBDDFactory.ite(var3, one("cat"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("dog")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("cat"), one("mouse")), VBDDFactory.ite(var3, one("cat"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("cat"), one("mouse")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var4, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap88() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("rabbit"), one("dog")), VBDDFactory.ite(var1, one("rabbit"), one("mouse")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("cat")), VBDDFactory.ite(var1, one("rabbit"), one("cat")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap89() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(true)), VBDDFactory.ite(var2, one(false), one(true))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(false), one(true))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(false)), VBDDFactory.ite(var2, one(false), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(true)), VBDDFactory.ite(var2, one(false), one(true))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var3, this.<Boolean>one( aa ), this.<Boolean>one(false || aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap90() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("mouse"), one("rabbit"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("dog"), one("dog"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap91() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("mouse")), VBDDFactory.ite(var1, one("cat"), one("mouse")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("cat")), VBDDFactory.ite(var1, one("dog"), one("rabbit")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap92() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(false)), VBDDFactory.ite(var2, one(false), one(true))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(true), one(false))));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(true), one(false)), VBDDFactory.ite(var2, one(true), one(false))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one(false), one(true)), VBDDFactory.ite(var2, one(true), one(false))));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one(true && aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap93() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("mouse"), one("rabbit")), VBDDFactory.ite(var1, one("cat"), one("mouse")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("cat"), one("cat")), VBDDFactory.ite(var1, one("dog"), one("rabbit")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap94() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends String > bdd = VBDDFactory.ite(var0, one("rabbit"), one("cat"));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, one("rabbit"), one("mouse"));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var0, this.<String>one( aa ), this.<String>one("dog" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap95() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Boolean > bdd = VBDDFactory.ite(var0, one(false), one(false));
        V<? extends Boolean > bdd2 = VBDDFactory.ite(var0, one(true), one(false));
        Long start = System.nanoTime();
        V<? extends Boolean > oldMap = bdd.<Boolean>flatMap((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Boolean > newMap = bdd.<Boolean>flatMapNew((aa) -> VBDDFactory.<Boolean>ite(var0, this.<Boolean>one( aa ), this.<Boolean>one( ! aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap96() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("cat")), VBDDFactory.ite(var3, one("mouse"), one("rabbit"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("mouse")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("mouse")), VBDDFactory.ite(var3, one("rabbit"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("rabbit")), VBDDFactory.ite(var3, one("mouse"), one("mouse")))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("mouse")), VBDDFactory.ite(var3, one("mouse"), one("mouse"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("mouse")), VBDDFactory.ite(var3, one("dog"), one("rabbit")))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("dog"), one("dog")), VBDDFactory.ite(var3, one("dog"), one("dog"))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one("mouse"), one("cat")), VBDDFactory.ite(var3, one("dog"), one("dog")))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("mouse" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap97() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("rabbit"), one("cat")), VBDDFactory.ite(var2, one("mouse"), one("dog"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("rabbit"), one("dog")), VBDDFactory.ite(var2, one("rabbit"), one("rabbit"))));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("cat"), one("rabbit")), VBDDFactory.ite(var2, one("cat"), one("cat"))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, one("mouse"), one("mouse")), VBDDFactory.ite(var2, one("rabbit"), one("cat"))));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var1, this.<String>one( aa ), this.<String>one("cat" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap98() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, one(1), one(4));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, one(4), one(3));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(1 + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var1, this.<Integer>one( aa ), this.<Integer>one(1 + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap99() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        VNode<Boolean> var3 = VBDDFactory.feature("var3");
        VNode<Boolean> var03 = VBDDFactory.feature("var3");
        VNode<Boolean> var4 = VBDDFactory.feature("var4");
        VNode<Boolean> var04 = VBDDFactory.feature("var4");
        V<? extends Integer > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(1)), VBDDFactory.ite(var3, one(5), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(3), one(1)), VBDDFactory.ite(var3, one(2), one(3)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(3)), VBDDFactory.ite(var3, one(4), one(3))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(4)), VBDDFactory.ite(var3, one(3), one(1)))));
        V<? extends Integer > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(2)), VBDDFactory.ite(var3, one(1), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(5), one(3)), VBDDFactory.ite(var3, one(1), one(5)))), VBDDFactory.ite(var1, VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(2), one(2)), VBDDFactory.ite(var3, one(4), one(4))), VBDDFactory.ite(var2, VBDDFactory.ite(var3, one(4), one(2)), VBDDFactory.ite(var3, one(1), one(2)))));
        Long start = System.nanoTime();
        V<? extends Integer > oldMap = bdd.<Integer>flatMap((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(4 - aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends Integer > newMap = bdd.<Integer>flatMapNew((aa) -> VBDDFactory.<Integer>ite(var2, this.<Integer>one( aa ), this.<Integer>one(4 - aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }

    @Test
    public void flatMap100() throws IOException {
        VNode<Boolean> var0 = VBDDFactory.feature("var0");
        VNode<Boolean> var00 = VBDDFactory.feature("var0");
        VNode<Boolean> var1 = VBDDFactory.feature("var1");
        VNode<Boolean> var01 = VBDDFactory.feature("var1");
        VNode<Boolean> var2 = VBDDFactory.feature("var2");
        VNode<Boolean> var02 = VBDDFactory.feature("var2");
        V<? extends String > bdd = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("rabbit"), one("dog")), VBDDFactory.ite(var1, one("rabbit"), one("mouse")));
        V<? extends String > bdd2 = VBDDFactory.ite(var0, VBDDFactory.ite(var1, one("dog"), one("mouse")), VBDDFactory.ite(var1, one("rabbit"), one("mouse")));
        Long start = System.nanoTime();
        V<? extends String > oldMap = bdd.<String>flatMap((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long oldTime = System.nanoTime() - start;
        Long start2 = System.nanoTime();
        V<? extends String > newMap = bdd.<String>flatMapNew((aa) -> VBDDFactory.<String>ite(var2, this.<String>one( aa ), this.<String>one("rabbit" + aa)));
        Long newTime = System.nanoTime() - start2;
        Assert.assertEquals(oldMap, newMap);

    }
}
