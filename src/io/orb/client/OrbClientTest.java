package io.orb.client;

public class OrbClientTest {
	
	private static final String HOST = "localhost";
	private static final int PORT = 7777;
	
	public static void main(String[] args) {
		OrbClient client = new OrbClient();
		
		client.connect(HOST, PORT);
		client.setDataValue("test", 123);
		System.out.println(client.getDataValue("test"));
		client.deleteDataValue("test");
		client.disconnect();
	}
	
}
