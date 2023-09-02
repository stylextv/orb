package io.orb.network;

import io.orb.network.packet.NetworkPacketProcessor;
import io.orb.util.LogUtil;
import io.orb.util.ThreadUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {
	
	private final NetworkPacketProcessor packetProcessor;
	
	private ServerSocket serverSocket;
	
	public NetworkServer(NetworkPacketProcessor packetProcessor) {
		this.packetProcessor = packetProcessor;
	}
	
	private Socket acceptClient() {
		try {
			
			return serverSocket.accept();
			
		} catch (IOException exception) {
			
			String message = String.format("Unable to accept network client: %s", exception);
			LogUtil.log(message);
			return null;
		}
	}
	
	public void setup(int port) {
		try {
			
			serverSocket = new ServerSocket(port);
			
			ThreadUtil.run(() -> {
				while(true) {
					
					Socket clientSocket = acceptClient();
					if(clientSocket == null) break;
					
					NetworkServerClient serverClient = new NetworkServerClient(packetProcessor);
					serverClient.connect(clientSocket);
				}
				
				serverSocket = null;
			});
			
		} catch(IOException exception) {
			
			String message = String.format("Unable to create network server: %s", exception);
			LogUtil.log(message);
		}
	}
	
	public void terminate() {
		if(serverSocket == null) return;
		
		try {
			
			serverSocket.close();
			
		} catch (IOException exception) {
			
			String message = String.format("Unable to destroy network server: %s", exception);
			LogUtil.log(message);
		}
	}
	
}
