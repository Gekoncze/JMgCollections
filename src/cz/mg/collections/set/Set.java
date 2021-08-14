package cz.mg.collections.set;

import cz.mg.collections.Collection;
import cz.mg.collections.Pass;

import java.util.HashSet;
import java.util.Iterator;


public class Set<T> implements ReadableSet<T>, WriteableSet<T>, Collection<T> {
    private final HashSet<T> hashSet = new HashSet<>();

    @Override
    public void set(T object) {
        hashSet.add(object);
    }

    @Override
    public boolean contains(T wanted) {
        return hashSet.contains(wanted);
    }

    @Override
    public int count() {
        return hashSet.size();
    }

    @Override
    public void clear() {
        hashSet.clear();
    }

    @Override
    public Pass<T> iterator() {
        return new Pass<T>() {
            private Iterator<T> iterator = hashSet.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                if(!hasNext()) return null;
                return iterator.next();
            }
        };
    }
}
