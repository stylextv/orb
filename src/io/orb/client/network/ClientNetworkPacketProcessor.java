package io.orb.client.network;

import io.orb.client.OrbClient;
import io.orb.data.DataSnapshot;
import io.orb.network.NetworkClient;
import io.orb.network.packet.NetworkPacket;
import io.orb.network.packet.NetworkPacketProcessor;
import io.orb.network.packet.packets.DataValueNetworkPacket;
import io.orb.network.packet.packets.DeleteDataValueNetworkPacket;
import io.orb.network.packet.packets.GetDataValueNetworkPacket;
import io.orb.network.packet.packets.SetDataValueNetworkPacket;
import io.orb.util.LogUtil;

public class ClientNetworkPacketProcessor extends NetworkPacketProcessor {
	
	private final OrbClient client;
	
	public ClientNetworkPacketProcessor(OrbClient client) {
		this.client = client;
	}
	
	public void deleteDataValue(String valueKey) {
		NetworkPacket packet = new DeleteDataValueNetworkPacket(valueKey);
		sendPacket(packet);
	}
	
	public void setDataValue(String valueKey, Object value) {
		DataSnapshot dataSnapshot = client.getDataSnapshot();
		if(dataSnapshot.containsValue(valueKey)) {
			
			dataSnapshot.setValue(valueKey, value);
			return;
		}
		
		NetworkPacket packet = new SetDataValueNetworkPacket(valueKey, value);
		NetworkClient networkClient = client.getNetworkClient();
		sendPacket(packet);
	}
	
	public Object getDataValue(String valueKey) {
		DataSnapshot dataSnapshot = client.getDataSnapshot();
		if(dataSnapshot.containsValue(valueKey)) {
			
			return dataSnapshot.getValue(valueKey);
		}
		
		NetworkPacket packet = new GetDataValueNetworkPacket(valueKey);
		NetworkClient networkClient = client.getNetworkClient();
		sendPacket(packet, networkClient);
		retrieveAnswerPacket(packet, (answerPacket) -> {
			
			
			
		}, networkClient);
		
		return null;
	}
	
	@Override
	public void retrievePacket(NetworkPacket packet, NetworkClient client) {
		String message = String.format("Unable to process network packet: %s", packet);
		LogUtil.log(message);
	}
	
}
