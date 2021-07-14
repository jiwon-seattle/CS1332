package hw6;
import java.util.Set;
import java.util.List;

public interface HashMapInterface<K, V> {
	int INITIAL_CAPACITY = 11;
	double MAX_LOAD_FACTOR = 0.67;
	
	V put(K key, V value);
	V remove(K key);
	V get(K key);
	boolean containsKey(K key);
	void clear();
	int size();
	Set<K> keySet();
	List<V> values();
	void resizeBackingTable(int length);
	MapEntry<K, V>[] getTable();
}
