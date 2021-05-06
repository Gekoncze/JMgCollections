package cz.mg.collections.map;

import cz.mg.collections.Collection;
import cz.mg.collections.Pass;


public class Map2D<K,V> implements Collection<V> {
    private final Map<K,V> key2val = new Map<>();
    private final Map<V,K> val2key = new Map<>();

    public Map2D() {
    }

    public Map2D(Map.Pair<K,V>... pairs){
        for(Map.Pair<K,V> pair : pairs){
            set(pair.key, pair.value);
        }
    }

    public void set(K key, V value){
        key2val.set(key, value);
        val2key.set(value, key);
    }

    public void getValue(K key){
        key2val.get(key);
    }

    public void getKey(V value){
        val2key.get(value);
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
}
