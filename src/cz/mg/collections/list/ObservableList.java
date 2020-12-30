package cz.mg.collections.list;

import cz.mg.collections.Collection;
import cz.mg.collections.CollectionItemListener;
import cz.mg.collections.ObservableCollection;


public class ObservableList<T> extends List<T> implements ObservableCollection<T> {
    private List<CollectionItemListener<T>> itemListeners = null;

    public ObservableList() {
    }

    public ObservableList(T... array) {
        super(array);
    }

    public ObservableList(Collection<? extends T> collection) {
        super(collection);
    }

    @Override
    public void addFirst(T data) {
        if(!onItemAddFilter(data)) return;
        if (firstItem == null) {
            rootItemAdded(createItem(data));
            onItemAdded(data);
        } else {
            firstItem.addPrevious(data);
        }
    }

    @Override
    public void addLast(T data) {
        if(!onItemAddFilter(data)) return;
        if (lastItem == null) {
            rootItemAdded(createItem(data));
            onItemAdded(data);
        } else {
            lastItem.addNext(data);
        }
    }

    @Override
    protected void onItemRemoved(T data) {
        if(itemListeners != null) for(CollectionItemListener<T> listener : itemListeners) listener.onItemChanged(data);
    }

    @Override
    protected void onItemAdded(T data) {
        if(itemListeners != null) for(CollectionItemListener<T> listener : itemListeners) listener.onItemChanged(data);
    }

    @Override
    public List<CollectionItemListener<T>> getItemListeners() {
        return itemListeners;
    }
}
