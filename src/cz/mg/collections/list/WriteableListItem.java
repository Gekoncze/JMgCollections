package cz.mg.collections.list;


public interface WriteableListItem<T> {
    public void setData(T data);
    boolean hasNext();
    boolean hasPrevious();
    public T removePrevious();
    public T removeNext();
    public T remove();
    public void addPrevious(T data);
    public void addNext(T data);
    public WriteableListItem<T> getPreviousItem();
    public WriteableListItem<T> getNextItem();
}
