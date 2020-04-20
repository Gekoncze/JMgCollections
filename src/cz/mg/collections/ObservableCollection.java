package cz.mg.collections;

import cz.mg.collections.list.List;


public interface ObservableCollection<T> {
    public List<CollectionItemListener<T>> getItemListeners();
}
