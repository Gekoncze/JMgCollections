package cz.mg.collections.array;

import cz.mg.collections.ReversiblePass;


public class ArrayPass<T> implements ReversiblePass<T> {
    private final ReadableArray<T> array;
    private int i = 0;

    public ArrayPass(ReadableArray<T> array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return i < array.count();
    }

    @Override
    public boolean hasPrevious() {
        return i > 0;
    }

    @Override
    public T next() {
        if(!hasNext()) return null;
        return array.get(i++);
    }

    @Override
    public T back() {
        if(!hasPrevious()) return null;
        return array.get(--i);
    }
}
