
public class Protocol {
	private boolean terminate = false;
	private String toUser;
	private Account acc;
	private BankServer bs;
	
	public Protocol(BankServer bs){
		this.bs = bs;
	}
	
	public String findAccount(String input){
		String[] exp = input.split(" ");
		
		if (exp[0].equals("ID")) {
			acc = bs.retrieveAccount(exp[1]);
			return acc.getID();
		} else {
			return "E 101";
		}
	}
	
	
	public boolean processInput(String input) {
		String[] exp = input.split(" ");
		
		if (exp[0].equals("1")) {
			toUser = withdraw(exp[1]);
		}
		else if (exp[0].equals("2")) {
			toUser = deposit(exp[1]);
		}
		else if (exp[0].equals("3")) {
			toUser = printBalance();
		}
		else if (exp[0].equals("4")) {
			terminate = true;
		}
		else { System.out.println("Error in Transaction"); }
		
		return terminate;
	}
	
	
	public String withdraw(String m) {
		double amount = Double.parseDouble(m); 
		
		if(amount > 600) {
			return "E 102";
		} else {
			acc.withdraw(amount);
			bs.transaction(acc, "Withdraw", amount);
			return "Withdrawn "+amount+" Euro" ;
		}
	}
	
	public String deposit(String m) {
		double amount = Double.parseDouble(m); 
		acc.deposit(amount);
		bs.transaction(acc, "Deposit", amount);
		return "Added "+amount+" Euro";
	}
	
	public String printBalance() {
		bs.transaction(acc, "Check Balance", 0);
		return "Balance: "+acc.getBalance();
		
	}
	
	public String getToUser(){
		return toUser;
	}

}
