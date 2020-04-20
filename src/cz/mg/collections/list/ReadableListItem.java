package cz.mg.collections.list;


public interface ReadableListItem<T> {
    T get();
    boolean hasNext();
    boolean hasPrevious();
    T getPrevious();
    T getNext();
    public ReadableListItem<T> getPreviousItem();
    public ReadableListItem<T> getNextItem();
}