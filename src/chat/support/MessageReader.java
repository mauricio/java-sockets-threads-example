package chat.support;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MessageReader implements Runnable {

	private static final Executor POOL = Executors.newFixedThreadPool(16);

	final private MessageClient client;
	private DataInputStream stream;
	private boolean connected = true;

	public MessageReader(MessageClient client, InputStream stream) {
		this.client = client;
		this.stream = new DataInputStream(stream);
		System.out.println("Reader is ready");
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	@Override
	public void run() {

		try {
			while (this.isConnected()) {
				int size = this.stream.readInt();
				System.out.printf("Reading %d bytes%n", size);
				byte[] message = new byte[size];
				for (int x = 0; x < size; x++) {
					System.out.printf("reading byte %d of %d%n", x, size);
					message[x] = (byte) this.stream.read();
				}
				final String realMessage = new String(message);

				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						client.onMessage(realMessage);
					}
				};
				POOL.execute(runnable);
			}
		} catch (Exception e) {
			this.client.errorOnRead(e);
			throw new RuntimeException(e);
		}

	}

}
