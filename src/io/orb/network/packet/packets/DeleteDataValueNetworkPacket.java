package io.orb.network.packet.packets;

import io.orb.network.packet.NetworkPacket;

public class DeleteDataValueNetworkPacket extends NetworkPacket {
	
	private final String valueKey;
	
	public DeleteDataValueNetworkPacket(String valueKey) {
		this.valueKey = valueKey;
	}
	
	public String getValueKey() {
		return valueKey;
	}
	
}
