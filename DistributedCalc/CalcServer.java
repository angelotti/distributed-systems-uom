import java.net.*;
import java.io.*;

public class CalcServer {

	public static void main(String[] args)  throws IOException {
		if (args.length != 1) {
            System.err.println("Usage: java CalcServer <port number>");
            System.exit(1);
        }
 
        int portNumber = Integer.parseInt(args[0]);
 
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
        	double a,b;
			String msg = in.readLine();
			while(!msg.equals("CLOSE")){
				String[] expression = msg.split(" ");
				
				//the parsing is done inside every if so that the server don't crashes to incorrect input				
				if( expression[0].equals("+")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a+b;
					out.println("Result = "+a);
				}
				else if( expression[0].equals("-")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a-b;
					out.println("Result = "+a);
				}
				else if( expression[0].equals("*")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a*b;
					out.println("Result = "+a);
				}
				else if( expression[0].equals("/")){
					a = Double.parseDouble(expression[1]);
					b = Double.parseDouble(expression[2]);
					a = a/b;
					out.println("Result = "+a);
				}
				else{
					out.println("Wrong format! Try again");
				}
				
				msg = in.readLine();
			}
        }

	}

}
