package chat.client;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Preparando cliente");
		Client client = new Client( "localhost", 8080 );
		System.out.println("Cliente conectado");
		Scanner s = new Scanner(System.in);
		boolean loop = true;
		
		while ( loop ) {
			System.out.println( "Escolha uma opção" );
			System.out.println( "1 - Escrever memsagem" );
			System.out.println( "2 - Sair" );
			
			int opcao = s.nextInt();
			
			switch( opcao ) {
			case 1:
				s.nextLine();
				System.out.println("Escreva sua mensagem");
				String message = s.nextLine();
				System.out.printf("Mensagem recebida -> %s%n", message);
				client.writeMessage(message);
				break;
			case 2:
				client.disconnect();
				loop = false;
				break;
			default:
				System.out.println("Esse comando não existe");
			}
		}
		
		client.disconnect();
		
	}

}
