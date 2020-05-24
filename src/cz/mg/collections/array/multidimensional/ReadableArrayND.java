package cz.mg.collections.array.multidimensional;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.ReadableArray;


public interface ReadableArrayND<T> extends ReadableCollection<T> {
    public T get(int... coordinates);
    public T get(ReadableArray<Integer> coordinates);
    public int getCount(int dimension);
    public int getDimensionCount();
}
