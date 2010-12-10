package chat.server;

import java.net.Socket;

import chat.support.BaseMessageClient;

public class ServerClient extends BaseMessageClient {

	private Server server;
	
	public ServerClient(Socket client, Server server) {
		super(client);
		this.server = server;
	}

	@Override
	public void onMessage(String message) {
		System.out.println( "Got message from client:" );
		System.out.println( message );
		this.server.onMessage(message, this);
	}

}
