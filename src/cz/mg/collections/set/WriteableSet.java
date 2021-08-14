package cz.mg.collections.set;

import cz.mg.collections.WriteableCollection;


public interface WriteableSet<T> extends WriteableCollection<T> {
    public void set(T object);
}
