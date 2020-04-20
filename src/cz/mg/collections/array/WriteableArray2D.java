package cz.mg.collections.array;

import cz.mg.collections.WriteableCollection;


public interface WriteableArray2D<T> extends WriteableCollection<T> {
    public void set(int x, int y, T data);
}
