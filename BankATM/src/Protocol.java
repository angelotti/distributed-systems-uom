
public class Protocol {
	
	
	public String findAccount(String input){
		String[] exp = input.split(" ");
		
		if (exp[0].equals("ID")) {
			return "ID query";
		} else {
			return "Account not found";
		}
	}
	
	
	public void processInput(String input) {
		String[] exp = input.split(" ");
		
		if (exp[0].equals("1")) {
			withdraw(exp[1]);
		}
		else if (exp[0].equals("2")) {
			deposit(exp[1]);
		}
		else if (exp[0].equals("3")) {
			printBalance();
		}
		else { System.out.println("Error in Transaction"); }
	}
	
	
	public String withdraw(String m) {
		double amount = Double.parseDouble(m); 
		
		if(amount > 600) {
			return "E 101";
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

}
