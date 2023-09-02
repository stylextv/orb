package io.orb.network;

import io.orb.network.packet.NetworkPacket;
import io.orb.network.packet.NetworkPacketConsumer;
import io.orb.network.packet.NetworkPacketConsumerMap;
import io.orb.network.packet.NetworkPacketProcessor;
import io.orb.util.LogUtil;
import io.orb.util.ThreadUtil;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class NetworkClient {
	
	private final NetworkPacketProcessor packetProcessor;
	private final NetworkPacketConsumerMap packetConsumers = new NetworkPacketConsumerMap();
	
	private Socket clientSocket;
	
	public NetworkClient(NetworkPacketProcessor packetProcessor) {
		this.packetProcessor = packetProcessor;
	}
	
	public void sendPacket(NetworkPacket packet) {
		try {
			
			OutputStream outputStream = clientSocket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			
			objectOutputStream.writeObject(packet);
			
		} catch (IOException exception) {
			
			String message = String.format("Unable to send network packet: %s", exception);
			LogUtil.log(message);
		}
	}
	
	public void retrievePacket(UUID packetID, NetworkPacketConsumer packetConsumer) {
		packetConsumers.addPacketConsumer(packetID, packetConsumer);
	}
	
	private NetworkPacket retrievePacket() {
		try {
			
			InputStream inputStream = clientSocket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			
			return (NetworkPacket) objectInputStream.readObject();
			
		} catch (IOException | ClassNotFoundException exception) {
			
			String message = String.format("Unable to retrieve network packet: %s", exception);
			LogUtil.log(message);
			return null;
		}
	}
	
	public void connect(String host, int port) {
		try {
			
			Socket clientSocket = new Socket(host, port);
			connect(clientSocket);
			
		} catch (IOException exception) {
			
			String message = String.format("Unable to create network client via host and port: %s", exception);
			LogUtil.log(message);
		}
	}
	
	public void connect(Socket clientSocket) {
		this.clientSocket = clientSocket;
		
		ThreadUtil.run(() -> {
			while(true) {
				
				packetConsumers.removeTimedOutPacketConsumers();
				
				NetworkPacket packet = retrievePacket();
				if(packet == null) break;
				
				UUID packetID = packet.getID();
				if(packetID == null) {
					
					String message = String.format("Retrieved packet with missing ID: %s", packet);
					LogUtil.log(message);
					continue;
				}
				
				NetworkPacketConsumer packetConsumer = packetConsumers.removePacketConsumer(packetID);
				if(packetConsumer != null) {
					
					packetConsumer.consumePacket(packet);
					continue;
				}
				
				packetProcessor.retrievePacket(packet, this);
			}
			
			this.clientSocket = null;
		});
	}
	
	public void disconnect() {
		if(clientSocket == null) return;
		
		try {
			
			clientSocket.close();
			
		} catch (IOException exception) {
			
			String message = String.format("Unable to destroy network client: %s", exception);
			LogUtil.log(message);
		}
	}
	
}
