package cz.mg.collections.map;

import cz.mg.collections.WriteableCollection;


public interface WriteableMap<K,V> extends WriteableCollection<V> {
    public void set(K key, V value);
    public V remove(K key);
}
