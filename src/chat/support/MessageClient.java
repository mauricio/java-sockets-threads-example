package chat.support;

public interface MessageClient {

	public void errorOnWrite( Exception e );
	public void errorOnRead( Exception e );
	public void onMessage( String message );
	
}
