package io.orb.server;

import io.orb.data.DataSnapshot;

public class OrbServerTest {
	
	private static final int PORT = 7777;
	private static final String DATA_SNAPSHOT_PATH = "data.json";
	
	public static void main(String[] args) {
		DataSnapshot dataSnapshot = DataSnapshot.loadFromFile(DATA_SNAPSHOT_PATH);
		OrbServer server = new OrbServer(dataSnapshot);
		
		server.setup(PORT);
//		server.terminate();
//		
//		DataSnapshot.saveToFile(dataSnapshot, DATA_SNAPSHOT_PATH);
	}
	
}
