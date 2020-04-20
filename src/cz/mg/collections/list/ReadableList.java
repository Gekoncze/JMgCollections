package cz.mg.collections.list;

import cz.mg.collections.ReadableCollection;


public interface ReadableList<T> extends ReadableCollection<T> {
    public T getFirst();
    public T getLast();
    public T get(int i);
    public ReadableListItem<T> getFirstItem();
    public ReadableListItem<T> getLastItem();
    public ReadableListItem<T> getItem(int i);
}
