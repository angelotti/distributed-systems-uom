import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CalcProtocol {
	
	private String error = null;
	private BufferedReader stdIn =
            new BufferedReader(new InputStreamReader(System.in));
    
	
	public String getInput(){
		String fromUser=null;
		try {
			fromUser = stdIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return fromUser;
		}
	}
	public String processInput(String inputLine){
		String[] expression = inputLine.split(" ");
		error = null;
		if(expression.length != 3){
			error = "Wrong number of arguments. We need 1 operator and 2 values!" ;
		}
		else if(!(expression[0].equals("+") ||  expression[0].equals("-") || 
				expression[0].equals("*") ||  expression[0].equals("/"))) {
			error = "Expected an operator in position 0" ;
		}
		else{
			try{
				double a = Double.parseDouble(expression[1]);
				double b = Double.parseDouble(expression[2]);
			}
			catch(Exception e){
				error = "You didnt passed any numbers";				
			}
			finally{
				return error;
			}
			
		}
		return error;
	}
	
	public boolean terminate(){
		boolean t;
		System.out.println("Continue? 'y' or 'n' :");
		while(true){
			String in = getInput();
			if(in.equals("y")){
				t = false;
				break;
			}
			else if(in.equals("n")){
				t = true;
				break;
			}
			else{
				System.out.println("Type 'y' or 'n'");
			}
		}
		return t;
	}

}
