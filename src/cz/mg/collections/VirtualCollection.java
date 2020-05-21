package cz.mg.collections;

import java.util.Iterator;


public class VirtualCollection<T> implements Clump<T> {
    private final Class<T> clazz;
    private final Collection collection;

    public VirtualCollection(Class<T> clazz, Collection collection) {
        this.clazz = clazz;
        this.collection = collection;
    }

    @Override
    public Iterator<T> iterator() {
        return new Filter(collection.iterator());
    }

    private class Filter implements Iterator<T> {
        private final Iterator iterator;
        private T current = null;

        public Filter(Iterator iterator) {
            this.iterator = iterator;
            this.current = move();
        }

        @SuppressWarnings("unchecked")
        private T move(){
            while(iterator.hasNext()){
                Object o = iterator.next();
                if(clazz.isInstance(o)){
                    return (T) o;
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
}