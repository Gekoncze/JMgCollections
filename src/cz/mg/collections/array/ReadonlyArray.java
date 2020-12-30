package cz.mg.collections.array;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.ReadonlyCollection;


public class ReadonlyArray<T> implements ReadableArray<T>, ReadonlyCollection<T> {
    private final Object[] array;

    public ReadonlyArray(T... array) {
        this.array = new Object[array.length];
        for(int i = 0; i < array.length; i++) this.array[i] = array[i];
    }

    public ReadonlyArray(ReadableCollection<? extends T> collection) {
        this.array = new Object[collection.count()];
        int i = 0;
        for(T item : collection){
            this.array[i] = item;
            i++;
        }
    }

    @Override
    public T get(int i){
        if(i < 0 || i >= count()) return null;
        return (T) array[i];
    }
    
    @Override
    public int count(){
        return array.length;
    }
}
