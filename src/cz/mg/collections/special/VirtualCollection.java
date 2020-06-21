package cz.mg.collections.special;

import cz.mg.collections.Clump;

import java.util.Iterator;


public class VirtualCollection<T> implements Clump<T> {
    private final Clump clump;
    private final Class<T> clazz;

    public VirtualCollection(Clump clump, Class<T> clazz) {
        if(clump == null) throw new NullPointerException();
        this.clump = clump;
        this.clazz = clazz;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator(clump.iterator());
    }

    private class FilterIterator implements Iterator<T> {
        private final Iterator iterator;
        private T current;

        public FilterIterator(Iterator iterator) {
            this.iterator = iterator;
            this.current = move();
        }

        @SuppressWarnings("unchecked")
        private T move(){
            while(iterator.hasNext()){
                Object object = iterator.next();
                if(clazz.isInstance(object)){
                    return (T) object;
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
            if(!hasNext()) return null;
            T result = current;
            current = move();
            return result;
        }
    }
}