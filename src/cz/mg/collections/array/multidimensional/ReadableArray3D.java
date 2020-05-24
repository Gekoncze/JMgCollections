package cz.mg.collections.array.multidimensional;

import cz.mg.collections.ReadableCollection;


public interface ReadableArray3D<T> extends ReadableCollection<T> {
    public T get(int x, int y, int z);
    public int getCountX();
    public int getCountY();
    public int getCountZ();
}
