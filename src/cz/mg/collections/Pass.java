package cz.mg.collections;

import java.util.Iterator;


public interface Pass<T> extends Iterator<T>, Iterable<T> {
    @Override
    public default Pass<T> iterator() {
        return this;
    }
}
