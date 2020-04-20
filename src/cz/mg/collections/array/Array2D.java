package cz.mg.collections.array;

import cz.mg.collections.Collection;
import java.util.Iterator;


public class Array2D<T> implements ReadableArray2D<T>, WriteableArray2D<T>, Collection<T> {
    private final Array<T> innerArray;
    private final int columnCount;
    private final int rowCount;

    public Array2D(int columnCount, int rowCount) {
        if(columnCount < 0 || rowCount < 0) throw new IllegalArgumentException();
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.innerArray = new Array<>(columnCount*rowCount);
    }
    
    @Override
    public T get(int x, int y){
        if(x < 0 || y < 0 || x >= columnCount || y >= rowCount) return null;
        return innerArray.get(x+y*columnCount);
    }
    
    @Override
    public void set(int x, int y, T data){
        if(x < 0 || y < 0 || x >= columnCount || y >= rowCount) return;
        innerArray.set(x+y*columnCount, data);
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }
    
    @Override
    public int count() {
        return columnCount*rowCount;
    }
    
    @Override
    public void clear() {
        innerArray.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return innerArray.iterator();
    }
}
