package cz.mg.collections;

public interface ReadableCollection<T> extends Clump<T> {
    public int count();
    
    public default boolean isEmpty(){
        return count() <= 0;
    }
}
