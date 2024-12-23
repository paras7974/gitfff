
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] args) throws IOException {

		Socket client = new Socket("127.0.0.1", 5643); // Loopback address and port number pass kiya.

		PrintWriter pw = new PrintWriter(client.getOutputStream(), true);

		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

		System.out.println("Client Started");

		BufferedReader inn = new BufferedReader(new InputStreamReader(System.in));

		String str = inn.readLine();

		while (str != null) {

			pw.println(str);

			System.out.println("Echo:" + br.readLine());

			if ("Bye".equals(str)) {
				break;
			}
			str = inn.readLine();
		}
		pw.close();
		br.close();
		inn.close();
		client.close();

	}

}
