package io.orb.network.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkPacketConsumerMap {
	
	private static final long MINIMAL_TIMED_OUT_PACKET_CONSUMER_WAIT_DURATION = 15000;
	
	private final Map<UUID, NetworkPacketConsumer> packetConsumers = new HashMap<>();
	private final Map<UUID, Long> packetConsumerAddTime = new HashMap<>();
	
	public void addPacketConsumer(UUID packetID, NetworkPacketConsumer packetConsumer) {
		long addTime = System.currentTimeMillis();
		
		packetConsumers.put(packetID, packetConsumer);
		packetConsumerAddTime.put(packetID, addTime);
	}
	
	public void removeTimedOutPacketConsumers() {
		packetConsumerAddTime.forEach((packetID, addTime) -> {
			
			long waitTime = System.currentTimeMillis() - addTime;
			if(waitTime >= MINIMAL_TIMED_OUT_PACKET_CONSUMER_WAIT_DURATION) {
				
				NetworkPacketConsumer packetConsumer = removePacketConsumer(packetID);
				packetConsumer.consumePacket(null);
			}
		});
	}
	
	public NetworkPacketConsumer removePacketConsumer(UUID packetID) {
		NetworkPacketConsumer packetConsumer = packetConsumers.get(packetID);
		if(packetConsumer == null) return null;
		
		packetConsumers.remove(packetID);
		packetConsumerAddTime.remove(packetID);
		
		return packetConsumer;
	}
	
}
