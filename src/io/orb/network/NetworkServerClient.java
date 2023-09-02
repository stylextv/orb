package io.orb.network;

import io.orb.network.packet.NetworkPacketProcessor;

public class NetworkServerClient extends NetworkClient {
	
	public NetworkServerClient(NetworkPacketProcessor packetProcessor) {
		super(packetProcessor);
	}
	
}
