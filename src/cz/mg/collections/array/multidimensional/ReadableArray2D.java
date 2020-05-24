package cz.mg.collections.array.multidimensional;

import cz.mg.collections.ReadableCollection;


public interface ReadableArray2D<T> extends ReadableCollection<T> {
    public T get(int x, int y);
    public int getCountX();
    public int getCountY();
}
