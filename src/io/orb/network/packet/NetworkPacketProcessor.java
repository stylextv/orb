package io.orb.network.packet;

import io.orb.network.NetworkClient;

import java.util.UUID;

public abstract class NetworkPacketProcessor {
	
	public abstract void retrievePacket(NetworkPacket packet, NetworkClient client);
	
	public void retrieveAnswerPacket(NetworkPacket packet, NetworkPacketConsumer answerPacketConsumer, NetworkClient client) {
		UUID packetID = packet.getID();
		
		client.retrievePacket(packetID, answerPacketConsumer);
	}
	
	public void answerPacket(NetworkPacket packet, NetworkPacket answerPacket, NetworkClient client) {
		UUID packetID = packet.getID();
		answerPacket.setID(packetID);
		
		client.sendPacket(answerPacket);
	}
	
	public void sendPacket(NetworkPacket packet, NetworkClient client) {
		client.sendPacket(packet);
	}
	
}
