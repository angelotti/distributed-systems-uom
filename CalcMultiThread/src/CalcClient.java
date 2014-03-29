import java.io.*;
import java.net.*;

public class CalcClient {
	
	
	public static void main(String[] args)  throws IOException {
	
		boolean terminate = false;
		
		if (args.length != 1) {
            System.err.println(
                "Usage: java CalcClient  <port number>");
            System.exit(1);
        }
		
		InetAddress host = InetAddress.getLocalHost();
        int portNumber = Integer.parseInt(args[0]);
	

        String error,fromUser;
        CalcProtocol cp = new CalcProtocol();
        
	try(
		Socket cSocket = new Socket(host, portNumber);
		PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
	) {
		System.out.println("\n***Welcome to CalcClient!***\n"
				+ " Type me some math and my friend CalcServer will tell you the answer!!\n\n"
				+ "Be careful everything must be space-separated! Something like this \"+ 3 5\" \n ");
		
		while(!terminate){
			System.out.println("Enter message:");
			fromUser = cp.getInput();
			error = cp.processInput(fromUser);
			if(error == null){
				out.println(fromUser);
				//prints result from server
				System.out.println(in.readLine());
				terminate = cp.terminate();
			}
			else{
				System.out.println(error);
			}
		}
		out.println("CLOSE");
		
	} catch (UnknownHostException e) {
        System.err.println("Don't know about host " + host);
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to " +
            host);
        System.exit(1);
    }
        
        
        
        
	}

}
