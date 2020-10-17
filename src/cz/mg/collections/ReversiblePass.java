package cz.mg.collections;

public interface ReversiblePass<T> extends Pass<T> {
    public boolean hasPrevious();
    public T back();
}
