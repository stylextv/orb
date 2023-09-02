package io.orb.server;

import io.orb.data.DataSnapshot;
import io.orb.network.NetworkServer;
import io.orb.server.network.ServerNetworkPacketProcessor;
import io.orb.util.LogUtil;

public class OrbServer {
	
	private final DataSnapshot dataSnapshot;
	
	private final ServerNetworkPacketProcessor networkPacketProcessor = new ServerNetworkPacketProcessor(this);
	private final NetworkServer networkServer = new NetworkServer(networkPacketProcessor);
	
	public OrbServer(DataSnapshot dataSnapshot) {
		this.dataSnapshot = dataSnapshot;
	}
	
	public void setup(int port) {
		networkServer.setup(port);
		
		String message = String.format("Successfully set-up server on port %s", port);
		LogUtil.log(message);
	}
	
	public void terminate() {
		networkServer.terminate();
	}
	
	public DataSnapshot getDataSnapshot() {
		return dataSnapshot;
	}
	
	public NetworkServer getNetworkServer() {
		return networkServer;
	}
	
}
