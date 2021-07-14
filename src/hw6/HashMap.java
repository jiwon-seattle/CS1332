package hw6;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class HashMap<K, V> implements HashMapInterface<K, V> {
	private MapEntry<K, V>[] table;
	private int size;
	
	public HashMap() {
		table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
		size = 0;
	}
	public HashMap(int initialCapacity) {
		table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
		size = 0;
	}
	
	@Override
	public V put(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Error, key is null");
		}
		V tempp;
		if (((double) (size + 1) / (double) (table.length))> MAX_LOAD_FACTOR) {
			resizeBackingTable(table.length * 2 + 1);
		}
		
		int hashnumber = Math.abs(key.hashCode() % table.length);
		if (table[hashnumber] == null) {
			table[hashnumber] = new MapEntry<K, V>(key, value);
			size ++;
			return null;
		} else {
			MapEntry<K, V> check = table[hashnumber];
			do {
				if (check.getKey().equals(key)) {
					tempp = check.getValue();
					check.setValue(value);
					return tempp;
				}
			} while(check.getNext() != null);
			
			if (check.getKey().equals(key)) {
				tempp = check.getValue();
				check.setValue(value);
				return tempp;
			}
			
			MapEntry<K, V> temp = new MapEntry(key, value, table[hashnumber]);
			table[hashnumber] = temp;
			size++;
			return null;
		}
	}
	
	@Override
	public V remove(K key) {
		MapEntry<K, V> tempone;
		int counter = 0;
		if (key == null) {
			throw new IllegalArgumentException("Error, key is null");
		}
		
		int hashnumber = Math.abs(key.hashCode() % table.length);
		if (table[hashnumber] == null) {
			throw new java.util.NoSuchElementException("error, key not exist");
		} else {
			MapEntry<K, V> check = table[hashnumber];
			do {
				if (check.getKey().equals(key)) {
					if (counter == 0) {
						if(check.getNext() == null) {
							table[hashnumber] = null;
						}else {
							table[hashnumber] = check.getNext();
						}
					} else {
						tempone = table[hashnumber];
						for (int i = 0; i < counter - 1; i++) {
							tempone = tempone.getNext();
						}
						if (check.getNext() == null) {
							tempone.setNext(null);
						} else {
							tempone.setNext(check.getNext());
						}
					}
					size --;
					return check.getValue();
				}
				if (check.getNext() != null) {
					check = check.getNext();
				} 
			} while(check.getNext() != null);
			
			if (check.getKey().equals(key)) {
				if (counter == 0) {
					if (check.getNext() == null) {
						table[hashnumber] = null;
					} else {
						table[hashnumber] = check.getNext();
					}
				}
			} else {
				tempone = table[hashnumber];
				for (int i = 0; i < counter -1; i++) {
					tempone = tempone.getNext();
				}
				if (check.getNext() == null) {
					tempone.setNext(null);
				} else {
					tempone.setNext(check.getNext());
				}
			}
			size --;
			return check.getValue();
		}
	}
	
	@Override
	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Error, key is null");
		}
		int hashnumber = Math.abs(key.hashCode() % table.length);
		if (table[hashnumber] == null) {
			throw new java.util.NoSuchElementException("error, key not exist");
		} else {
			MapEntry<K, V> check = table[hashnumber];
			do {
				if (check.getKey().equals(key)) {
					return check.getValue();
				}
				
				if (check.getNext() != null) {
					check = check.getNext();
				} 
			} while(check.getNext() != null);
			
			if (check.getKey().equals(key)) {
				return check.getValue();
			}
			throw new java.util.NoSuchElementException("error, key not exist");
		}
	}
	
	@Override
	public boolean containsKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Error, key is null");
		}
		int hashnumber = Math.abs(key.hashCode() % table.length);
		if (table[hashnumber] == null) {
			return false;
		} else {
			MapEntry<K, V> check = table[hashnumber];
			do {
				if (check.getKey().equals(key)) {
					return true;
				}
				if (check.getNext() != null) {
					check = check.getNext();
				}
			} while(check.getNext() != null);
			
			if (check.getKey().equals(key)) {
				return true;
			}
			return false;
		}
	}
	
	@Override
	public void clear() {
		table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Set<K> keySet() {
		HashSet<K> keys = new HashSet<K> (size);
		for (MapEntry<K,V> item : table) {
			if (item != null) {
				keys.add(item.getKey());
				while(item.getNext() != null) {
					item = item.getNext();
					keys.add(item.getKey());
				}
			}
		}
		return keys;
	}
	
	@Override
	public List<V> values() {
		List<V> values = new ArrayList<V>(size);
		for (MapEntry<K, V> item : table) {
			if (item != null) {
				values.add(item.getValue());
				while(item.getNext() != null) {
					item = item.getNext();
					values.add(item.getValue());
				}
			}
		}
		return values;
	}
	
	@Override
	public void resizeBackingTable(int length) {
		int tempsize = size;
		if (length <= 0 || length < size) {
			throw new IllegalArgumentException("error");
		}
		MapEntry<K, V> temp;
		size = 0;
		MapEntry<K, V> [] temptable = table;
		table = (MapEntry<K,V>[]) new MapEntry[length];
		for (MapEntry<K,V> item : temptable) {
			size = 0;
			if (item != null) {
				put(item.getKey(), item.getValue());
				temp = item;
				while(temp.getNext() != null) {
					put(temp.getKey(), temp.getValue());
					temp = temp.getNext();
				}
				
				put(temp.getKey(), temp.getValue());
			}
		}
		size = tempsize;
	}
	
	@Override
	public MapEntry<K, V>[] getTable() {
		return table;
	}
}
