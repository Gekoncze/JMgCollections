package cz.mg.collections.array;

public class ArrayView<T> implements ReadableArray<T> {
    private final ReadableArray<T> array;
    private final int begin;
    private final int end;

    public ArrayView(ReadableArray<T> array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public T get(int i) {
        int ii = begin + i;
        if(ii >= end) return null;
        if(ii < begin) return null;
        return array.get(ii);
    }

    @Override
    public int count() {
        return end - begin;
    }
}
