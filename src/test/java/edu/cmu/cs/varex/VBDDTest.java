package edu.cmu.cs.varex;

import edu.cmu.cs.varex.vbdd.VBDDFactory;
import edu.cmu.cs.varex.vbdd.VNode;
import edu.cmu.cs.varex.vbdd.VValue;
import org.junit.Assert;
import org.junit.Test;

public class VBDDTest {

    @Test
    public void createFeature() {
        VNode<Boolean> f = VBDDFactory.feature("foo");
        Assert.assertEquals("foo", f._feature().getName());
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
    Assert.assertEquals("Foo", ((VValue<String>)v)._value());
    Assert.assertNull(v._high());
    Assert.assertNull(v._low());
    }
}
