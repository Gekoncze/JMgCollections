package cz.mg.collections.special;

import cz.mg.collections.Clump;
import cz.mg.collections.Collection;
import cz.mg.collections.Pass;
import cz.mg.collections.array.Array;

import java.util.Iterator;


public class CompositeCollection<T> implements Clump<T> {
    private final Array<Clump<? extends T>> clumps;

    @SafeVarargs
    public CompositeCollection(Clump<? extends T>... clumps) {
        this.clumps = new Array<>(clumps);
    }

    public CompositeCollection(Collection<Clump<? extends T>> clumps) {
        this.clumps = new Array<>(clumps);
    }

    @Override
    public Pass<T> iterator() {
        return new GlueIterator(clumps.iterator());
    }

    private class GlueIterator implements Pass<T> {
        private final Iterator<Clump<? extends T>> iterators;
        private Iterator<? extends T> currentIterator = null;
        private T current;

        public GlueIterator(Iterator<Clump<? extends T>> iterators) {
            this.iterators = iterators;
            this.current = move();
        }

        private T move(){
            while(true) {
                if(currentIterator == null){
                    if(iterators.hasNext()){
                        Clump<? extends T> clump = iterators.next();
                        if(clump != null){
                            currentIterator = clump.iterator();
                        }
                    } else {
                        return null;
                    }
                } else {
                    if(currentIterator.hasNext()){
                        return currentIterator.next();
                    } else {
                        currentIterator = null;
                    }
                }
            }
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
