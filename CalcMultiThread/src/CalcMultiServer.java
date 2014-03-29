import java.io.IOException;
import java.net.ServerSocket;


public class CalcMultiServer {

	public static void main(String[] args) throws IOException{
		
		if (args.length != 1) {
			System.err.println("Usage: java CalcMultiServer <port number>");
			System.exit(1);
		}
		
		int threadNumber = 0;
		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			System.out.println("Server started");
			while(listening){
				new CalcMultiServerThread(serverSocket.accept(), threadNumber).start();
				threadNumber++;
				System.out.println("Thread number: " + threadNumber);
			}
		} catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }

	}

}
