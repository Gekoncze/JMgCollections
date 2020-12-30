package cz.mg.collections.array;

import cz.mg.collections.Collection;
import cz.mg.collections.ReadableCollection;


public class Array<T> implements ReadableArray<T>, WriteableArray<T>, Collection<T> {
    private final Object[] array;

    public Array(int count) {
        this.array = new Object[count];
    }
    
    public Array(T... array) {
        this.array = new Object[array.length];
        for(int i = 0; i < array.length; i++) this.array[i] = array[i];
    }
    
    public Array(ReadableCollection<? extends T> collection) {
        this.array = new Object[collection.count()];
        int i = 0;
        for(T item : collection){
            this.array[i] = item;
            i++;
        }
    }

    public Object[] getJavaArray() {
        return array;
    }

    @Override
    public T get(int i){
        if(i < 0 || i >= count()) return null;
        return (T) array[i];
    }

    @Override
    public void set(T data, int i){
        if(i < 0 || i >= count()) return;
        array[i] = data;
    }

    @Override
    public void setFirst(T data) {
        set(data, 0);
    }

    @Override
    public void setLast(T data) {
        set(data, count() - 1);
    }

    @Override
    public int count(){
        return array.length;
    }

    @Override
    public void clear() {
        for(int i = 0; i < array.length; i++) array[i] = null;
    }
}
