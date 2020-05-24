package cz.mg.collections.array.multidimensional;

import cz.mg.collections.Collection;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.array.ReadonlyArray;
import java.util.Iterator;


// not tested yet ...
public class ArrayND<T> implements ReadableArrayND<T>, WriteableArrayND<T>, Collection<T> {
    private final Array<T> innerArray;
    private final ReadonlyArray<Integer> counts;
    private final int count;

    public ArrayND(ReadableCollection<T> collection, ReadableCollection<Integer> counts) {
        int totalCount = 0;
        for(Integer count : counts){
            if(count < 0 || count < 0) throw new IllegalArgumentException();
            totalCount += count;
        }
        if(collection.count() != totalCount) throw new IllegalArgumentException();
        this.count = totalCount;
        this.counts = new ReadonlyArray<>(counts);
        this.innerArray = new Array<>(collection);
    }

    public ArrayND(ReadableCollection<Integer> counts) {
        int totalCount = 0;
        for(Integer count : counts){
            if(count < 0 || count < 0) throw new IllegalArgumentException();
            totalCount += count;
        }
        this.count = totalCount;
        this.counts = new ReadonlyArray<>(counts);
        this.innerArray = new Array<>(totalCount);
    }

    public Array<T> asArray() {
        return innerArray;
    }

    private int transform(int... coordinates){
        if(coordinates.length != getDimensionCount()) throw new IllegalArgumentException();
        int i = 0;
        int size = 1;
        for(int d = 0; d < counts.count(); d++){
            if(coordinates[d] < 0 || coordinates[d] >= counts.get(d) ) return -1;
            size *= counts.get(d);
            i += coordinates[d] * size;
        }
        return i;
    }

    private int transform(ReadableArray<Integer> coordinates){
        if(coordinates.count() != getDimensionCount()) throw new IllegalArgumentException();
        int i = 0;
        int size = 1;
        for(int d = 0; d < counts.count(); d++){
            if(coordinates.get(d) < 0 || coordinates.get(d) >= counts.get(d) ) return -1;
            size *= counts.get(d);
            i += coordinates.get(d) * size;
        }
        return i;
    }

    @Override
    public T get(int... coordinates){
        return innerArray.get(transform(coordinates));
    }

    @Override
    public T get(ReadableArray<Integer> coordinates) {
        return innerArray.get(transform(coordinates));
    }

    @Override
    public void set(T data, int... coordinates){
        innerArray.set(data, transform(coordinates));
    }

    @Override
    public void set(T data, ReadableArray<Integer> coordinates) {
        innerArray.set(data, transform(coordinates));
    }

    @Override
    public int getCount(int dimension) {
        return counts.get(dimension);
    }

    @Override
    public int getDimensionCount() {
        return counts.count();
    }

    @Override
    public int count() {
        return count;
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
