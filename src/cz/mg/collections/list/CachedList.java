package cz.mg.collections.list;

import cz.mg.collections.Collection;
import cz.mg.collections.array.Array;


public class CachedList<T> extends List<T> {
    private Array<ListItem<T>> array;

    public CachedList() {
    }

    public CachedList(T... data) {
        this();
        for(T d : data) addLast(d);
    }
    
    public CachedList(Collection<T> collection) {
        this();
        for(T item : collection) addLast(item);
    }
    
    @Override
    public final T get(int i) {
        if(array == null) rebuildArray();
        return array.get(i).get();
    }

    @Override
    public final ListItem<T> getItem(int i) {
        if(array == null) rebuildArray();
        return array.get(i);
    }
    
    private void rebuildArray(){
        array = new Array<>(count());
        int i = 0;
        for(ListItem<T> item = getFirstItem(); item != null; item = item.getNextItem()){
            array.set(item, i);
            i++;
        }
    }

    @Override
    protected void onItemAdded(T data) {
        array = null;
        super.onItemAdded(data);
    }

    @Override
    protected void onItemRemoved(T data) {
        array = null;
        super.onItemRemoved(data);
    }
}
