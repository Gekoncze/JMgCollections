package cz.mg.collections.array;

import cz.mg.collections.Pass;
import cz.mg.collections.ReadableCollection;


public interface ReadableArray<T> extends ReadableCollection<T> {
    public T get(int i);

    public default T getFirst() {
        return get(0);
    }

    public default T getLast() {
        return get(count() - 1);
    }

    @Override
    public default Pass<T> iterator() {
        return new Pass<T>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count();
            }

            @Override
            public T next() {
                if(!hasNext()) return null;
                return get(i++);
            }
        };
    }
}
