package io.orb.network.packet.packets;

import io.orb.network.packet.NetworkPacket;

public class GetDataValueNetworkPacket extends NetworkPacket {
	
	private final String valueKey;
	
	public GetDataValueNetworkPacket(String valueKey) {
		this.valueKey = valueKey;
	}
	
	public String getValueKey() {
		return valueKey;
	}
	
}
