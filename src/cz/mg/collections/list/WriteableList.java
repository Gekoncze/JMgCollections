package cz.mg.collections.list;

import cz.mg.collections.WriteableCollection;


public interface WriteableList<T> extends WriteableCollection<T> {
    public void setFirst(T data);
    public void setLast(T data);
    public void set(T data, int i);
    public void addFirst(T data);
    public void addLast(T data);
    public void add(T data, int i);
    public T removeFirst();
    public T removeLast();
    public T remove(T data);
    public T remove(int i);
    public WriteableListItem<T> getFirstItem();
    public WriteableListItem<T> getLastItem();
    public WriteableListItem<T> getItem(int i);
}
