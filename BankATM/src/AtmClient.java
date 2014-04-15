import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class AtmClient {

	public static void main(String[] args)   throws IOException {
	
	
		if (args.length != 0) {
            System.err.println(
                "Usage: java AtmClient  <port number>");
            System.exit(1);
        }
		
		int portNumber = 1234;//Integer.parseInt(args[0]);
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
			fromServer = in.readLine();
			if(fromServer==null){
				System.out.println("Account not found");
				terminate = true;
			} else {
				System.out.println("Connected to account: "+fromServer);
			}
			
			while (!terminate) {
				printMenu();
				getInput();
				/*while(!(fromUser.matches("[1,2,3,4]")/*fromUser.equals("1") || fromUser.equals("2") ||
						fromUser.equals("3") || fromUser.equals("4") /) ) {
					System.out.println("\nMust be a number from 1 to 4: ");
					getInput();
				}*/
				
				double d = Double.parseDouble(fromUser);
				if (d==1/*fromUser.equals("1")*/) {
					withdraw();
					out.println("1 " + fromUser);
					fromServer = in.readLine();
					System.out.println(fromServer);
				}
				else if (d==2/*fromUser.equals("2")*/) {
					deposit();
					out.println("2 " + fromUser);
					fromServer = in.readLine();
					System.out.println(fromServer);
				}
				else if (d==3/*fromUser.equals("3 ")*/) {

					System.out.println("Balance");
					out.println("3");
					balance(in.readLine());
					System.out.println(fromServer);
				}
				else if (d==4) {
					terminate = true;
					out.println("4 ");
					System.out.println("\n-_-_-_See you_-_-_-\n");
				}
				else { System.out.println("You must type a number from 1 to 4"); }
				
				
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
		System.out.println("\n---Deposit---\n"+ "Amount: ");
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
				//System.out.println(fromUser);
				if(fromUser.matches("\\d+")) { flag = false; }
				else { System.out.println("Must type digits!"); }
						//System.out.println(fromUser);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectAccount() {
		System.out.println("\nGive your account ID :");
		getInput();
		/*while(!fromUser.matches("\\d+")){
			System.out.println("\nMust be a number");
			getInput();
		}*/
	}
	
	
}

