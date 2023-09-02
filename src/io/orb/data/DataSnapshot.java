package io.orb.data;

import java.util.HashMap;
import java.util.Map;

public class DataSnapshot {
	
	private final Map<String, Object> values = new HashMap<>();
	
	public void deleteValue(String valueKey) {
		synchronized(values) {
			values.remove(valueKey);
		}
	}
	
	public void setValue(String valueKey, Object value) {
		synchronized(values) {
			values.put(valueKey, value);
		}
	}
	
	public Object getValue(String valueKey) {
		synchronized(values) {
			return values.get(valueKey);
		}
	}
	
	public boolean containsValue(String valueKey) {
		synchronized(values) {
			return values.containsKey(valueKey);
		}
	}
	
	public static void saveToFile(DataSnapshot snapshot, String path) {
		
	}
	
	public static DataSnapshot loadFromFile(String path) {
		return new DataSnapshot();
	}
	
}
