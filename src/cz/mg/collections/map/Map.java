package cz.mg.collections.map;

import cz.mg.collections.Collection;
import cz.mg.collections.Pass;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;

import java.util.HashMap;
import java.util.Iterator;


public class Map<K,V> implements ReadableMap<K,V>, WriteableMap<K,V>, Collection<V> {
    private final HashMap<K,V> hashMap = new HashMap<>();

    public Map() {
    }

    public Map(Pair<K,V>... pairs){
        for(Pair<K,V> pair : pairs){
            set(pair.key, pair.value);
        }
    }

    @Override
    public V get(K key){
        return get(key, null);
    }

    @Override
    public V get(K key, V defaultValue){
        return hashMap.getOrDefault(key, defaultValue);
    }

    @Override
    public void set(K key, V value){
        hashMap.put(key, value);
    }

    @Override
    public int count() {
        return hashMap.size();
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public V remove(K key) {
        if(hashMap.containsKey(key)){
            return hashMap.remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Pass<V> iterator() {
        return new Pass<V>() {
            private Iterator<V> iterator = hashMap.values().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public V next() {
                if(!hasNext()) return null;
                return iterator.next();
            }
        };
    }

    public ReadableList<K> keys(){
        return new List<>(hashMap.keySet());
    }

    public static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
