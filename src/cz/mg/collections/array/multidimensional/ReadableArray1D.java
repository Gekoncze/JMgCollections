package cz.mg.collections.array.multidimensional;

import cz.mg.collections.ReadableCollection;


public interface ReadableArray1D<T> extends ReadableCollection<T> {
    public T get(int x);
    public int getCountX();
}
