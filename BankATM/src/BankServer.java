import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;



public class BankServer {
	private Connection conn = null;
	private String server, dbPortNumber, dbName, uname, pswd, driverName, url;

	public static void main(String[] args) throws IOException{
	
		
		if (args.length != 0) {
			System.err.println("Usage: java BankServer <port number>");
			System.exit(1);
		}
		
		int threadNumber = 0;
		int portNumber = 1234;//Integer.parseInt(args[0]);
		boolean listening = true;
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			System.out.println("Server started");
			while(listening){
				new BankServerThread(serverSocket.accept(), threadNumber).start();
				threadNumber++;
				System.out.println("Thread number: " + threadNumber);
			}
		} catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }

	}
	
	public BankServer(){
		driverName = "com.mysql.jdbc.Driver";
		uname = "pdpuser";
		pswd = "resupdp";
		server = "195.251.209.12";
		dbPortNumber = "3306";
		dbName = "it12";
		url = "jdbc:mysql://"+server+":"+dbPortNumber+"/"+dbName;
	}
	
	public boolean connectDB(){
		try {
			Class.forName(driverName);
			conn =DriverManager.getConnection(url, uname, pswd);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	
}


