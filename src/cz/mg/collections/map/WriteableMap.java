package cz.mg.collections.map;

public interface WriteableMap<K,V> {
    public void set(K key, V value);
    public void clear(K key);
}
