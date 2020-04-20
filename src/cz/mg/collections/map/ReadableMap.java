package cz.mg.collections.map;

public interface ReadableMap<K,V> {
    public V get(K key);
    public V get(K key, V defaultValue);
}
