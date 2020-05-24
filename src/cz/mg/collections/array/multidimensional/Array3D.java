package cz.mg.collections.array.multidimensional;

import cz.mg.collections.Collection;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import java.util.Iterator;


public class Array3D<T> implements ReadableArray3D<T>, WriteableArray3D<T>, Collection<T> {
    private final Array<T> innerArray;
    private final int countX;
    private final int countY;
    private final int countZ;

    public Array3D(ReadableCollection<T> collection, int countX, int countY, int countZ) {
        if(countX < 0 || countY < 0 || countZ < 0) throw new IllegalArgumentException();
        if(collection.count() != countX * countY * countZ) throw new IllegalArgumentException();
        this.countX = countX;
        this.countY = countY;
        this.countZ = countZ;
        this.innerArray = new Array<>(collection);
    }

    public Array3D(int countX, int countY, int countZ) {
        if(countX < 0 || countY < 0 || countZ < 0) throw new IllegalArgumentException();
        this.countX = countX;
        this.countY = countY;
        this.countZ = countZ;
        this.innerArray = new Array<>(countX * countY * countZ);
    }

    public Array<T> asArray() {
        return innerArray;
    }

    private int transform(int x, int y, int z){
        if(x < 0 || y < 0 || z < 0 || x >= countX || y >= countY || z >= countZ) return -1;
        return x + y*countX + z*countX*countY;
    }

    @Override
    public T get(int x, int y, int z){
        return innerArray.get(transform(x, y, z));
    }
    
    @Override
    public void set(T data, int x, int y, int z){
        innerArray.set(data, transform(x, y, z));
    }

    @Override
    public int getCountX() {
        return countX;
    }

    @Override
    public int getCountY() {
        return countY;
    }

    @Override
    public int getCountZ() {
        return countZ;
    }

    @Override
    public int count() {
        return countX * countY * countZ;
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
