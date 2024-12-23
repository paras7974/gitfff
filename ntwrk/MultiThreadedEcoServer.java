

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedEcoServer extends Thread {

	private Socket client = null;

	public MultiThreadedEcoServer(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String inputLine;
			inputLine = br.readLine();
			while (inputLine != null)  {
				System.out.println("Server Recived " + inputLine);
				pw.println(inputLine + ".." + inputLine);
				if (inputLine.equals("Bye.")) {
					break;
				}
				inputLine = br.readLine();
			}
			pw.close();
			br.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = null;
		MultiThreadedEcoServer multiThrea = null;
		serverSocket = new ServerSocket(5643);
		System.out.println("Echo Server Started");
		Socket clientSocket = null;
		boolean flag = true;
		while (flag) {
			clientSocket = serverSocket.accept();
			multiThrea = new MultiThreadedEcoServer(clientSocket);
			multiThrea.start();
		}
		System.out.println("Echo Server Stoped");
		serverSocket.close();
	}

}
