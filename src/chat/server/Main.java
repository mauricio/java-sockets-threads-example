package chat.server;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		final Server server = new Server(8080);
		Runnable r = new Runnable() {
			public void run() {
				server.start();
			};
		};
		new Thread(r).start();

		Scanner s = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			System.out.println("Digite uma opção:");
			System.out.println("1 - Sair");
			int opcao = s.nextInt();
			switch (opcao) {
			case 1:
				server.setConnected(false);
				loop = false;
				break;
			default:
				System.out.println("Essa opção não existe");
			}
		}
	}

}
