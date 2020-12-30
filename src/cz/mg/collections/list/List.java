package cz.mg.collections.list;

import cz.mg.collections.Collection;
import cz.mg.collections.Pass;


public class List<T> implements ReadableList<T>, WriteableList<T>, Collection<T> {
    protected int itemCount = 0;
    protected ListItem<T> firstItem = null;
    protected ListItem<T> lastItem = null;

    public List() {
    }
    
    public List(T... array) {
        for(T item : array) addLast(item);
    }
    
    public List(Iterable<? extends T> collection) {
        for(T item : collection) addLast(item);
    }
    
    @Override
    public T getFirst() {
        if (firstItem == null) return null;
        return firstItem.get();
    }

    @Override
    public T getLast() {
        if (lastItem == null) return null;
        return lastItem.get();
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= itemCount) return null;
        return getItem(i).get();
    }

    @Override
    public void setFirst(T data) {
        ListItem<T> item = getFirstItem();
        if(item != null) item.setData(data);
    }

    @Override
    public void setLast(T data) {
        ListItem<T> item = getLastItem();
        if(item != null) item.setData(data);
    }

    @Override
    public void set(T data, int i) {
        ListItem<T> item = getItem(i);
        if(item != null) item.setData(data);
    }

    @Override
    public ListItem<T> getFirstItem() {
        return firstItem;
    }

    @Override
    public ListItem<T> getLastItem() {
        return lastItem;
    }

    @Override
    public ListItem<T> getItem(int i) {
        if (i < 0 || i >= itemCount) return null;
        ListItem<T> currentItem = firstItem;
        for (int ii = 0; ii < i; ii++) currentItem = currentItem.getNextItem();
        return currentItem;
    }

    @Override
    public void addFirst(T data) {
        if(!onItemAddFilter(data)) return;
        if (firstItem == null) {
            rootItemAdded(createItem(data));
        } else {
            firstItem.addPrevious(data);
        }
    }

    @Override
    public void addLast(T data) {
        if(!onItemAddFilter(data)) return;
        if (lastItem == null) {
            rootItemAdded(createItem(data));
        } else {
            lastItem.addNext(data);
        }
    }

    @Override
    public void add(T data, int i) {
        if(isEmpty()){
            addFirst(data);
        } else {
            if(i < 0) i = 0;
            if(i > count()) i = count();

            if(i == count()){
                addLast(data);
            } else {
                getItem(i).addPrevious(data);
            }
        }
    }

    @Override
    public T removeFirst() {
        if (firstItem == null) return null;
        return firstItem.remove();
    }

    @Override
    public T removeLast() {
        if (lastItem == null) return null;
        return lastItem.remove();
    }
    
    @Override
    public T remove(T data) {
        for(ListItem<T> item = getFirstItem(); item != null; item = item.getNextItem()){
            if(item.get() == data) return item.remove();
        }
        return null;
    }

    @Override
    public T remove(int i) {
        return getItem(i).remove();
    }

    @Override
    public int count() {
        return itemCount;
    }
    
    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    final void itemRemoved(ListItem<T> removedItem) {
        itemCount--;
    }

    final void firstItemRemoved(ListItem<T> newFirstItem, ListItem<T> removedItem) {
        firstItem = newFirstItem;
        itemCount--;
    }

    final void lastItemRemoved(ListItem<T> newLastItem, ListItem<T> removedItem) {
        lastItem = newLastItem;
        itemCount--;
    }

    final void rootItemRemoved(ListItem<T> removedItem) {
        firstItem = null;
        lastItem = null;
        itemCount = 0;
    }

    final void itemAdded(ListItem<T> newItem) {
        itemCount++;
    }

    final void firstItemAdded(ListItem<T> newItem) {
        firstItem = newItem;
        itemCount++;
    }

    final void lastItemAdded(ListItem<T> newItem) {
        lastItem = newItem;
        itemCount++;
    }
    
    final void rootItemAdded(ListItem<T> newItem) {
        firstItem = newItem;
        lastItem = newItem;
        itemCount = 1;
    }
    
    protected boolean onItemAddFilter(T data) {
        return true;
    }

    @Override
    public final Pass<T> iterator() {
        return new Pass<T>() {
            private ListItem<T> current = firstItem;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if(!hasNext()) return null;
                T rval = current.get();
                current = current.getNextItem();
                return rval;
            }
        };
    }
    
    public static <T> List<T> unionAll(List<T> a, List<T> b) {
        List<T> result = new List<>();
        for (T aa : a) result.addLast(aa);
        for (T bb : b) result.addLast(bb);
        return result;
    }

    public static <T> List<T> unionAll(T a, List<T> b) {
        List<T> result = new List<>();
        result.addLast(a);
        for (T bb : b) result.addLast(bb);
        return result;
    }

    public static <T> List<T> unionAll(List<T> a, T b) {
        List<T> result = new List<>();
        for (T aa : a) result.addLast(aa);
        result.addLast(b);
        return result;
    }

    public void addCollectionFirst(Iterable<? extends T> collection){
        ListItem<T> oldFirst = getFirstItem();
        if(oldFirst != null){
            for(T data : collection) {
                oldFirst.addPrevious(data);
            }
        } else {
            addCollectionLast(collection);
        }
    }

    public void addCollectionLast(Iterable<? extends T> collection){
        for(T data : collection) addLast(data);
    }

    public void addCollectionFirst(T[] collection){
        ListItem<T> oldFirst = getFirstItem();
        if(oldFirst != null){
            for(T data : collection) {
                oldFirst.addPrevious(data);
            }
        } else {
            addCollectionLast(collection);
        }
    }

    public void addCollectionLast(T[] collection){
        for(T data : collection) addLast(data);
    }

    protected void onItemRemoved(T data) {
    }

    protected void onItemAdded(T data) {
    }

    protected ListItem<T> createItem(T data){
        return new ListItem<T>(this, data);
    }
}
