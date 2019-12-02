package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	ServerSocket sock;
	Socket connSock;
	
	public ClientHandler(ServerSocket sock, Socket connSock) {
		this.sock = sock;
		this.connSock = connSock;
	}

	@Override
	public void run() {
		
		try {
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connSock.getInputStream()));
			DataOutputStream clientOutput = new DataOutputStream(connSock.getOutputStream());
			
			int responseChoice = (int) (Math.random() * 20) + 1;
			
			String body;
			
			switch (responseChoice) {
			case 1:
				body = "It is certain";
				break;
			case 2:
				body = "It is decidedly so";
				break;
			case 3:
				body = "Without a doubt";
				break;
			case 4:
				body = "Yes, definitely";
				break;
			case 5:
				body = "You may rely on it";
				break;
			case 6:
				body = "As I see it, yes";
				break;
			case 7:
				body = "Most likely";
				break;
			case 8:
				body = "Outlook good";
				break;
			case 9:
				body = "Yes";
				break;
			case 10:
				body = "Signs point to yes";
				break;
			case 11:
				body = "Reply hazy try again";
				break;
			case 12:
				body = "Ask again later";
				break;
			case 13:
				body = "Better not tell you now";
				break;
			case 14:
				body = "Cannot predict now";
				break;
			case 15:
				body = "Concentrate and ask again";
				break;
			case 16:
				body = "Don't count on it";
				break;
			case 17:
				body = "My reply is no";
				break;
			case 18:
				body = "My sources say no";
				break;
			case 19:
				body = "Outlook not so good";
				break;
			case 20:
				body = "Very doubtful";
				break;
			default:
				body = "Hello there";
			}
			
			String response = "HTTP/1.0 200 OK\n\n" + body;
			
			PrintWriter out = new PrintWriter(connSock.getOutputStream());
			out.println(response);
			out.flush();
			
			connSock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
