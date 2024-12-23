
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient1 {

	public static void main(String[] args) throws IOException {

		Socket soket = new Socket("127.0.0.1", 5643); // Loopback address and port number pass kiya.

		PrintWriter out = new PrintWriter(soket.getOutputStream(), true);

		BufferedReader in = new BufferedReader(new InputStreamReader(soket.getInputStream()));

		System.out.println("Client Started");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();

		while (line != null) {

			out.println(line);

			System.out.println("Echo:" + in.readLine());

			if (line.equals("bye")) {
				break;
			}
			line = br.readLine();
		}
		out.close();
		in.close();
		br.close();
		soket.close();

	}

}
