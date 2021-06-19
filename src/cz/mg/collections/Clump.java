package cz.mg.collections;


public interface Clump<T> extends Iterable<T> {
    public default boolean contains(T wanted){
        for(T data : this){
            if(data == wanted) return true;
        }
        return false;
    }

    public default int indexOf(T wanted){
        int i = 0;
        for(T data : this){
            if(data == wanted) return i;
            i++;
        }
        return -1;
    }

    public default ToTextBuilder<T> toText(){
        return new ToTextBuilder<>(this);
    }

    @Override
    public Pass<T> iterator();

    public static int count(Clump clump){
        int count = 0;
        for(Object o : clump) count++;
        return count;
    }

    public static boolean contains(Clump clump, Object wanted){
        for(Object object : clump){
            if(object == wanted) return true;
        }
        return false;
    }
}
