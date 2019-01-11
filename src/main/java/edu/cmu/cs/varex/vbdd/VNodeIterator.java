package edu.cmu.cs.varex.vbdd;

import java.util.*;

public class VNodeIterator<T> implements Iterator<VNode<T>>, Iterable<VNode<T>> {

    private final Set<VNode<T>> seen = new HashSet<>();
    private final Queue<VNode<T>> queue = new LinkedList<>();

    public VNodeIterator(VNode<T> bdd) {
        queue.add(bdd);
    }

    @Override
    public boolean hasNext() {
        while (!queue.isEmpty() && seen.contains(queue.peek()))
            queue.poll();

        return !queue.isEmpty();
    }

    @Override
    public VNode<T> next() {
        while (!queue.isEmpty() && seen.contains(queue.peek()))
            queue.poll();

        VNode<T> b = queue.poll();
        seen.add(b);
        if (b._low() != null)
            queue.add(b._low());
        if (b._high() != null)
            queue.add(b._high());

        return b;
    }

    @Override
    public Iterator<VNode<T>> iterator() {
        return this;
    }
}
