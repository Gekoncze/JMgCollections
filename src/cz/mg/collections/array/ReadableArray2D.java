package cz.mg.collections.array;

import cz.mg.collections.ReadableCollection;


public interface ReadableArray2D<T> extends ReadableCollection<T> {
    public T get(int x, int y);
    public int getColumnCount();
    public int getRowCount();
}
