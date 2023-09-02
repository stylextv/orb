package io.orb.network.packet;

@FunctionalInterface
public interface NetworkPacketConsumer {
	
	void consumePacket(NetworkPacket packet);
	
}
