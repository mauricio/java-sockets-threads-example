
package chat.client;

import chat.support.BaseMessageClient;

public class Client extends BaseMessageClient {

	public Client(String host, int port) throws Exception {
		super( host, port );
	}
	
	public void onMessage( String message ) {
		System.out.printf( "Message read is -> %s%n", message );
	}
	
}
