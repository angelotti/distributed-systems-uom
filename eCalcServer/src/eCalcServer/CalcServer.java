package eCalcServer;
import java.net.*;
import java.io.*;

public class CalcServer {
	private static final int PORT = 1234;
	
	public static void main(String[] args) throws IOException{
		int connectionCount = 0;
		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server started");
		
		while (true) {
			Socket dataSocket = connectionSocket.accept();
			connectionCount++;
			System.out.println("Received " + connectionCount + " request from " + dataSocket.getInetAddress());
			
			InputStream is = dataSocket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			OutputStream os = dataSocket.getOutputStream();
			PrintWriter out = new PrintWriter(os, true);
			
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
			dataSocket.close();
			System.out.println("Data socket closed");
			
		}
		
	}

}
