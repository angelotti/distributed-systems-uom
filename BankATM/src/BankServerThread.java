import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class BankServerThread extends Thread {
	private Socket socket = null;
	
	public BankServerThread(Socket socket, int n) {
		super("BankServerThread, Thread "+n);
		this.socket = socket;
	}
	
	public void run() {
   	 
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        )  {
        	
        }
	}

}
