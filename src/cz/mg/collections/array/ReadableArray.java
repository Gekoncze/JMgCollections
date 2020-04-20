package cz.mg.collections.array;

import cz.mg.collections.ReadableCollection;
import java.util.Iterator;


public interface ReadableArray<T> extends ReadableCollection<T> {
    public T get(int i);

    public default T getFirst() {
        return get(0);
    }

    public default T getLast() {
        return get(count() - 1);
    }

    @Override
    public default Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count();
            }

            @Override
            public T next() {
                return get(i++);
            }
        };
    }
}
