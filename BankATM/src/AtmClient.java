import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class AtmClient {

	public static void main(String[] args)   throws IOException {
	
	
		if (args.length != 1) {
            System.err.println(
                "Usage: java AtmClient  <port number>");
            System.exit(1);
        }
		
		int portNumber = Integer.parseInt(args[0]);
		AtmClient atm = new AtmClient(portNumber);

	}
	
	int portNumber;
	private BufferedReader stdIn =
            new BufferedReader(new InputStreamReader(System.in));
	String fromUser = "";
	String fromServer = "";
	
	public AtmClient(int portNumber) throws IOException {
		boolean terminate = false;


		InetAddress host = InetAddress.getLocalHost();
		this.portNumber = portNumber;

		try(
				Socket cSocket = new Socket(host, portNumber);
				PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
		) {
			System.out.println("\n***Welcome to MY Bank***\n");
			
			connectAccount();
			out.println("ID "+fromUser);

			while (!terminate) {
				printMenu();
				getInput();
				while(!(fromUser.matches("[1,2,3,4]")/*fromUser.equals("1") || fromUser.equals("2") ||
						fromUser.equals("3") || fromUser.equals("4") */) ) {
					System.out.println("\nMust be a number from 1 to 4: ");
					getInput();
				}
				
				if (fromUser.equals("1")) {
					out.println("1 " + withdraw());
					fromServer = in.readLine();
				}
				else if (fromUser.equals("2")) {
					out.println("2 " + deposit());
					fromServer = in.readLine();
				}
				else if (fromUser.equals("3 ")) {
					out.println("3 ");
					balance(in.readLine());
				}
				else {
					terminate = true;
				}
					
			}
			
			
			
		} catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + host);
	        System.exit(1);
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to " +
	            host);
	        System.exit(1);
	    }
	}

	private void balance(String fromServer) {
		System.out.println("Your balance is : "+fromServer);
		
	}

	private String withdraw() {
		System.out.println("\n---Withdraw---\n"+ "Amount: ");
		getInput();
		return fromUser;
	}

	private String deposit() {
		System.out.println("\n---Withdraw---\n"+ "Amount: ");
		getInput();
		return fromUser;
		
	}

	public void printMenu() {
		System.out.println("\nActions:\n"
				+ "1. Withdraw\n"
				+ "2. Deposit\n"
				+ "3. Balance\n"
				+ "4. Terminate\n"
				+ "\nType the number of the action: \n");
	}
	
	public void getInput() {
		boolean flag = true;
		try{
			while (flag) {
				fromUser = stdIn.readLine();
				fromUser.trim();
				if(fromUser.matches("\\d+")) { flag = false; }
				else { System.out.println("Must type digits!"); }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectAccount() {
		System.out.println("\nGive your account ID :");
		getInput();
		while(!fromUser.matches("\\d+")){
			System.out.println("\nMust be a number");
			getInput();
		}
	}
	
	
}

