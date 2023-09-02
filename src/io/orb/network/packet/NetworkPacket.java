package io.orb.network.packet;

import java.io.Serializable;
import java.util.UUID;

public class NetworkPacket implements Serializable {
	
	private UUID id;
	
	public NetworkPacket() {
		this(UUID.randomUUID());
	}
	
	private NetworkPacket(UUID id) {
		this.id = id;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setID(UUID id) {
		this.id = id;
	}
	
}
