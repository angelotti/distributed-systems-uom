import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class BankServerThread extends Thread {
	private String fromUser, toUser;
	private Socket socket = null;
	private Protocol p;
	private boolean terminate = false;//true;
	
	public BankServerThread(Socket socket, BankServer bs, int n) {
		super("BankServerThread, Thread "+n);
		this.socket = socket;
		p = new Protocol(bs);
	}
	
	public void run() {
   	 
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        )  {
        	fromUser = in.readLine();
        	System.out.println("fromUser"+fromUser);
        	toUser = p.findAccount(fromUser);
        	//if(toUser.equals("E 101")) { terminate  = true; }
        	out.println(toUser);
        	
        	while(!terminate) {
        		fromUser = in.readLine();
        		terminate = p.processInput(fromUser);
        		out.println(p.getToUser());
        	}
        	
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

}
