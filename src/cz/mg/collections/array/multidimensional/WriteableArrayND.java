package cz.mg.collections.array.multidimensional;

import cz.mg.collections.WriteableCollection;
import cz.mg.collections.array.ReadableArray;


public interface WriteableArrayND<T> extends WriteableCollection<T> {
    public void set(T data, int... coordinates);
    public void set(T data, ReadableArray<Integer> coordinates);
}
