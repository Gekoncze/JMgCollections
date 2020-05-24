package cz.mg.collections.array.multidimensional;

import cz.mg.collections.WriteableCollection;


public interface WriteableArray2D<T> extends WriteableCollection<T> {
    public void set(T data, int x, int y);
}
