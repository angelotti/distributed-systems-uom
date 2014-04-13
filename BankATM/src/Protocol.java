
public class Protocol {
	private boolean terminate = false;
	private String toUser;
	
	public String findAccount(String input){
		String[] exp = input.split(" ");
		
		if (exp[0].equals("ID")) {
			return "ID query";
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
			return "withdraw query" ;
		}
	}
	
	public String deposit(String m) {
		double amount = Double.parseDouble(m); 

		return "deposit query";
	}
	
	public String printBalance() {
		
		return "Balance: ";
		
	}
	
	public String getToUser(){
		return toUser;
	}

}
