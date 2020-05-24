package cz.mg.collections.array.multidimensional;

import cz.mg.collections.Collection;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import java.util.Iterator;


public class Array2D<T> implements ReadableArray2D<T>, WriteableArray2D<T>, Collection<T> {
    private final Array<T> innerArray;
    private final int countX;
    private final int countY;

    public Array2D(ReadableCollection<T> collection, int countX, int countY) {
        if(countX < 0 || countY < 0) throw new IllegalArgumentException();
        if(collection.count() != countX * countY) throw new IllegalArgumentException();
        this.countX = countX;
        this.countY = countY;
        this.innerArray = new Array<>(collection);
    }

    public Array2D(int countX, int countY) {
        if(countX < 0 || countY < 0) throw new IllegalArgumentException();
        this.countX = countX;
        this.countY = countY;
        this.innerArray = new Array<>(countX * countY);
    }

    public Array<T> asArray() {
        return innerArray;
    }

    private int transform(int x, int y){
        if(x < 0 || y < 0 || x >= countX || y >= countY) return -1;
        return x+y*countX;
    }

    @Override
    public T get(int x, int y){
        return innerArray.get(transform(x, y));
    }
    
    @Override
    public void set(T data, int x, int y){
        innerArray.set(data, transform(x, y));
    }

    @Override
    public int getCountX() {
        return countX;
    }

    @Override
    public int getCountY() {
        return countY;
    }
    
    @Override
    public int count() {
        return countX * countY;
    }
    
    @Override
    public void clear() {
        innerArray.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return innerArray.iterator();
    }
}
