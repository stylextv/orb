package io.orb.network.packet.packets;

import io.orb.network.packet.NetworkPacket;

public class DataValueNetworkPacket extends NetworkPacket {
	
	private final Object value;
	
	public DataValueNetworkPacket(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
}
