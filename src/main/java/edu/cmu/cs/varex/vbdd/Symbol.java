package edu.cmu.cs.varex.vbdd;

/**
 * value objects, can be compared with identity
 *
 * use VBDDFactory.mkFeature to create
 */
public class Symbol implements Comparable<Symbol> {


    private final String name;
    final int idx;

    Symbol(int idx, String name) {
        this.idx = idx;
        this.name = name;

    }

    @Override
    public int compareTo(Symbol that) {
        return this.idx - that.idx;
    }

    public String getName() {
        return name;
    }

    public Symbol min(Symbol that) {
        if (this.idx<=that.idx)
            return this;
        return that;
    }

    public boolean before(Symbol that) {
        return compareTo(that)<0;
    }
}
