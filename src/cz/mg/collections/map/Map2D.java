package cz.mg.collections.map;

import cz.mg.collections.Collection;
import cz.mg.collections.Pass;
import cz.mg.collections.list.ReadableList;


public class Map2D<K,V> implements Collection<V> {
    private final Map<K,V> key2val = new Map<>();
    private final Map<V,K> val2key = new Map<>();

    public Map2D() {
    }

    public Map2D(Pair<K,V>... pairs){
        for(Pair<K,V> pair : pairs){
            set(pair.key, pair.value);
        }
    }

    public void set(K key, V value){
        K oldKey = val2key.get(value);
        V oldVal = key2val.get(key);
        key2val.remove(oldKey);
        val2key.remove(oldVal);
        key2val.set(key, value);
        val2key.set(value, key);
    }

    public V getValue(K key){
        return key2val.get(key);
    }

    public K getKey(V value){
        return val2key.get(value);
    }

    public V getValueOrCreate(K key, ValueFactory<V> factory){
        V value = key2val.get(key);
        if(value == null) {
            value = factory.create();
            set(key, value);
        }
        return value;
    }

    public K getKeyOrCreate(V value, KeyFactory<K> factory){
        K key = val2key.get(value);
        if(key == null) {
            key = factory.create();
            set(key, value);
        }
        return key;
    }

    public ReadableList<K> getKeys(){
        return key2val.keys();
    }

    public ReadableList<V> getValues(){
        return val2key.keys();
    }

    @Override
    public int count() {
        return key2val.count();
    }

    @Override
    public Pass<V> iterator() {
        return key2val.iterator();
    }

    @Override
    public void clear() {
        key2val.clear();
        val2key.clear();
    }

    @FunctionalInterface
    public interface ValueFactory<V>{
        V create();
    }

    @FunctionalInterface
    public interface KeyFactory<K>{
        K create();
    }
}
