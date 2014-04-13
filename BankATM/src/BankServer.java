import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class BankServer {
	private Connection conn = null;
	private String server, dbPortNumber, dbName, uname, pswd, driverName, url;

	public static void main(String[] args) throws IOException{
	
		
		if (args.length != 0) {
			System.err.println("Usage: java BankServer <port number>");
			System.exit(1);
		}
		
		BankServer bs = new BankServer();
		
		int threadNumber = 0;
		int portNumber = 1234;//Integer.parseInt(args[0]);
		boolean listening = false;
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			System.out.println("Server started");
			if(bs.connectDB()){
				listening = true;
				System.out.println("Connected to db");
			}
			while(listening){
				new BankServerThread(serverSocket.accept(), bs, threadNumber).start();
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

	public Account retrieveAccount(String id){
		Account acc = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Customers WHERE id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				acc = new Account(rs.getString(1),rs.getDouble(2));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		
		return acc;
	}
	
	public void transaction (Account a, String type, double amount) {
		try {
			String sql = "INSERT INTO Transactions VALUES (?,?,?,?)" ;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			pstmt.setString(1, dateFormat.format(date));
			
			pstmt.setString(2, a.getID());
			pstmt.setString(3, type);
			pstmt.setDouble(4, amount);
			
			if(!type.equals("Check Balance")){ updateAccount(a); }
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateAccount (Account a) {
		String sql = "UPDATE Transactions SET Balance = ? WHERE ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, a.getBalance());
			pstmt.setInt(2, Integer.parseInt(a.getID()));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}


