package edu.cmu.cs.varex.bdd;

import org.junit.Test;

/**
 * Created by ckaestne on 6/23/2016.
 */
public class BDDTest {

    @Test
    public void test() {
        BDDFactory f = new BDDFactory();

        BDDFactory.BDD x = f.option("x");
        BDDFactory.BDD y = f.option("y");
        BDDFactory.BDD z = f.option("z");
        System.out.println(x.and(y));
        f.printDot(x.and(y).or(z).not());

    }
}
