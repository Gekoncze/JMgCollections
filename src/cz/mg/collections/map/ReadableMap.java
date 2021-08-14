package cz.mg.collections.map;

import cz.mg.collections.ReadableCollection;


public interface ReadableMap<K,V> extends ReadableCollection<V> {
    public V get(K key);
    public V get(K key, V defaultValue);
}
