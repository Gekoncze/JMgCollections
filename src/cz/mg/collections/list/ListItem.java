package cz.mg.collections.list;


public class ListItem<T> implements ReadableListItem<T>, WriteableListItem<T> {
    protected List<T> list;
    protected T data;
    protected ListItem<T> previousItem = null;
    protected ListItem<T> nextItem = null;

    public ListItem(List<T> list, T data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public final T get() {
        return data;
    }

    @Override
    public final void setData(T data) {
        if(list != null) list.onItemRemoved(this.data);
        this.data = data;
        if(list != null) list.onItemAdded(this.data);
    }
    
    @Override
    public final boolean hasNext(){
        return nextItem != null;
    }
    
    @Override
    public final boolean hasPrevious(){
        return previousItem != null;
    }
    
    @Override
    public final T getPrevious(){
        if(previousItem == null) return null;
        return previousItem.data;
    }
    
    @Override
    public final T getNext(){
        if(nextItem == null) return null;
        return nextItem.data;
    }

    @Override
    public final ListItem<T> getPreviousItem() {
        return previousItem;
    }

    @Override
    public final ListItem<T> getNextItem() {
        return nextItem;
    }

    @Override
    public final T removePrevious() {
        if(previousItem == null) return null;
        return previousItem.remove();
    }

    @Override
    public final T removeNext() {
        if(nextItem == null) return null;
        return nextItem.remove();
    }
    
    @Override
    public final T remove() {
        List<T> backup = list;
        if(list == null) return null;
        if(previousItem == null && nextItem == null) list.rootItemRemoved(this);
        else if(previousItem == null) list.firstItemRemoved(nextItem, this);
        else if(nextItem == null) list.lastItemRemoved(previousItem, this);
        else list.itemRemoved(this);
        disconnectMiddle(previousItem, this, nextItem);
        backup.onItemRemoved(data);
        return data;
    }

    @Override
    public final void addPrevious(T data) {
        if(!list.onItemAddFilter(data)) return;
        ListItem<T> newItem = list.createItem(data);
        if(previousItem == null) list.firstItemAdded(newItem);
        else list.itemAdded(newItem);
        connectMiddle(previousItem, newItem, this);
        list.onItemAdded(data);
    }

    @Override
    public final void addNext(T data) {
        if(!list.onItemAddFilter(data)) return;
        ListItem<T> newItem = list.createItem(data);
        if(nextItem == null) list.lastItemAdded(newItem);
        else list.itemAdded(newItem);
        connectMiddle(this, newItem, nextItem);
        list.onItemAdded(data);
    }

    private void disconnectMiddle(ListItem left, ListItem middle, ListItem right) {
        middle.list = null;
        middle.previousItem = null;
        middle.nextItem = null;
        if(left != null) left.nextItem = right;
        if(right != null) right.previousItem = left;
    }

    private void connectMiddle(ListItem left, ListItem middle, ListItem right) {
        middle.list = left == null ? right.list : left.list;
        middle.previousItem = left;
        middle.nextItem = right;
        if(left != null) left.nextItem = middle;
        if(right != null) right.previousItem = middle;
    }
}