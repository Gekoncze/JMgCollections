package cz.mg.collections.array;

import cz.mg.collections.WriteableCollection;


public interface WriteableArray<T> extends WriteableCollection<T> {
    public void set(T data, int i);
}
