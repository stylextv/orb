package io.orb.server.network;

import io.orb.data.DataOwnerMap;
import io.orb.data.DataSnapshot;
import io.orb.network.NetworkClient;
import io.orb.network.packet.NetworkPacket;
import io.orb.network.packet.NetworkPacketProcessor;
import io.orb.network.packet.packets.DataValueNetworkPacket;
import io.orb.network.packet.packets.DeleteDataValueNetworkPacket;
import io.orb.network.packet.packets.GetDataValueNetworkPacket;
import io.orb.network.packet.packets.SetDataValueNetworkPacket;
import io.orb.server.OrbServer;
import io.orb.util.LogUtil;

public class ServerNetworkPacketProcessor extends NetworkPacketProcessor {
	
	private final OrbServer server;
	
	private final DataOwnerMap dataOwners = new DataOwnerMap();
	
	public ServerNetworkPacketProcessor(OrbServer server) {
		this.server = server;
	}
	
	@Override
	public void retrievePacket(NetworkPacket packet, NetworkClient client) {
		DataSnapshot dataSnapshot = server.getDataSnapshot();
		
		if(packet instanceof GetDataValueNetworkPacket getPacket) {
			String valueKey = getPacket.getValueKey();
			
			Object value = dataSnapshot.getValue(valueKey);
			
			NetworkPacket answerPacket = new DataValueNetworkPacket(value);
			answerPacket(getPacket, answerPacket, client);
			return;
		}
		
		if(packet instanceof SetDataValueNetworkPacket setPacket) {
			String valueKey = setPacket.getValueKey();
			Object value = setPacket.getValue();
			
			dataSnapshot.setValue(valueKey, value);
			return;
		}
		
		if(packet instanceof DeleteDataValueNetworkPacket deletePacket) {
			
			NetworkClient valueOwner = dataOwners.getValueOwner(key);
			
			String valueKey = deletePacket.getValueKey();
			
			dataSnapshot.deleteValue(valueKey);
			return;
		}
		
		String message = String.format("Unable to process network packet: %s", packet);
		LogUtil.log(message);
	}
	
}
