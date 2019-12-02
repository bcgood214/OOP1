package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket sock = new ServerSocket(7654);
		while (true) {
			Socket connSock = sock.accept();
			Thread t = new Thread(new ClientHandler(sock, connSock));
			t.start();
		
		}
	}
}
