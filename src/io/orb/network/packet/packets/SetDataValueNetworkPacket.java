package io.orb.network.packet.packets;

import io.orb.network.packet.NetworkPacket;

public class SetDataValueNetworkPacket extends NetworkPacket {
	
	private final String valueKey;
	private final Object value;
	
	public SetDataValueNetworkPacket(String valueKey, Object value) {
		this.valueKey = valueKey;
		this.value = value;
	}
	
	public String getValueKey() {
		return valueKey;
	}
	
	public Object getValue() {
		return value;
	}
	
}
