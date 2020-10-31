package cz.mg.collections.special;

import cz.mg.collections.Clump;
import cz.mg.collections.Pass;


public class PartCollection<P, T> implements Clump<T> {
    private final Clump<P> parent;
    private final PartGetter<P, T> partGetter;

    public PartCollection(Clump<P> parent, PartGetter<P, T> partGetter) {
        this.parent = parent;
        this.partGetter = partGetter;
    }

    @Override
    public Pass<T> iterator() {
        return new PartCollectionPass(parent.iterator());
    }

    public interface PartGetter<P, T>{
        public T get(P parent);
    }

    private class PartCollectionPass implements Pass<T>{
        private final Pass<P> pass;

        public PartCollectionPass(Pass<P> pass) {
            this.pass = pass;
        }

        @Override
        public boolean hasNext() {
            return pass.hasNext();
        }

        @Override
        public T next() {
            return partGetter.get(pass.next());
        }
    }
}
