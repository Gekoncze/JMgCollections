package cz.mg.collections.special;

import cz.mg.collections.Clump;
import java.util.Iterator;


public class FilteredCollection<T> implements Clump<T> {
    private final Clump<T> clump;
    private final Filter<T> filter;

    public FilteredCollection(Clump<T> clump, Filter<T> filter) {
        this.clump = clump;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator(clump.iterator());
    }

    private class FilterIterator implements Iterator<T> {
        private final Iterator<T> iterator;
        private T current;

        public FilterIterator(Iterator<T> iterator) {
            this.iterator = iterator;
            this.current = move();
        }

        private T move(){
            while(iterator.hasNext()){
                T object = iterator.next();
                if(filter.shallPass(object)){
                    return object;
                }
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T result = current;
            current = move();
            return result;
        }
    }

    public interface Filter<T> {
        boolean shallPass(T data);
    }
}
