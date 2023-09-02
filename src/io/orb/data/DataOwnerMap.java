package io.orb.data;

import io.orb.network.NetworkClient;

import java.util.HashMap;
import java.util.Map;

public class DataOwnerMap {
	
	private final Map<String, NetworkClient> valueOwners = new HashMap<>();
	
	public void setValueOwner(String valueKey, NetworkClient valueOwner) {
		valueOwners.put(valueKey, valueOwner);
	}
	
	public NetworkClient getValueOwner(String valueKey) {
		return valueOwners.get(valueKey);
	}
	
}
