package io.orb.client;

import io.orb.client.network.ClientNetworkPacketProcessor;
import io.orb.data.DataSnapshot;
import io.orb.network.NetworkClient;
import io.orb.util.LogUtil;

public class OrbClient {
	
	private final DataSnapshot dataSnapshot = new DataSnapshot();
	
	private final ClientNetworkPacketProcessor networkPacketProcessor = new ClientNetworkPacketProcessor(this);
	private final NetworkClient networkClient = new NetworkClient(networkPacketProcessor);
	
	public void deleteDataValue(String valueKey) {
		networkPacketProcessor.deleteDataValue(valueKey);
	}
	
	public void setDataValue(String valueKey, Object value) {
		networkPacketProcessor.setDataValue(valueKey, value);
	}
	
	public Object getDataValue(String valueKey) {
		return networkPacketProcessor.getDataValue(valueKey);
	}
	
	public void connect(String host, int port) {
		networkClient.connect(host, port);
		
		String message = String.format("Successfully connected to server at %s:%s", host, port);
		LogUtil.log(message);
	}
	
	public void disconnect() {
		networkClient.disconnect();
	}
	
	public DataSnapshot getDataSnapshot() {
		return dataSnapshot;
	}
	
	public NetworkClient getNetworkClient() {
		return networkClient;
	}
	
}
