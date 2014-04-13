
public class Account {

	private String id;
	private double balance;
	
	public Account(String id, double balance){
		this.id = id;
		this.balance = balance;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setBalance(double d){
		balance = d;
	}
	
	public String getID(){
		return id;
	}
	
	public double withdraw(double d){
		return balance = balance - d;
	}
	
	public double deposit(double d){
		return balance = balance + d;
	}
}
