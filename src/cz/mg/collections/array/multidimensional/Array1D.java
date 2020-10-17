package cz.mg.collections.array.multidimensional;

import cz.mg.collections.Collection;
import cz.mg.collections.Pass;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;


public class Array1D<T> implements ReadableArray1D<T>, WriteableArray1D<T>, Collection<T> {
    private final Array<T> innerArray;
    private final int countX;

    public Array1D(ReadableCollection<T> collection) {
        this(collection, collection.count());
    }

    public Array1D(ReadableCollection<T> collection, int countX) {
        if(countX < 0) throw new IllegalArgumentException();
        if(collection.count() != countX) throw new IllegalArgumentException();
        this.countX = countX;
        this.innerArray = new Array<>(collection);
    }

    public Array1D(int countX) {
        if(countX < 0) throw new IllegalArgumentException();
        this.countX = countX;
        this.innerArray = new Array<>(countX);
    }

    public Array<T> asArray() {
        return innerArray;
    }

    private int transform(int x){
        if(x < 0 || x >= countX) return -1;
        return x;
    }

    @Override
    public T get(int x){
        return innerArray.get(transform(x));
    }
    
    @Override
    public void set(T data, int x){
        innerArray.set(data, transform(x));
    }

    @Override
    public int getCountX() {
        return countX;
    }

    @Override
    public int count() {
        return countX;
    }
    
    @Override
    public void clear() {
        innerArray.clear();
    }

    @Override
    public Pass<T> iterator() {
        return innerArray.iterator();
    }
}
