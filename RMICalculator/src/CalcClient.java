import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalcClient {
	double a,b;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public CalcClient(){
		System.out.print("\n***Welcome to CalcClient!***\n"
				+ " Type me some math and my friend CalcServer will tell you the answer!!\n\n"
				+ "Be careful everything must be space-separated! Something like this \"+ 3 5\" \n"
				+ "\nFor terminating CalcClient type \"exit\" \n\n");
	}

	public static void main(String[] args) {
		CalcClient client = new CalcClient();
		boolean terminate = false;
		double result = 0;
		String op; //operation
		
		try {
			Registry registry = LocateRegistry.getRegistry(4444); //localhost is used as default
			CalcInterface calc = (CalcInterface) registry.lookup("RMICalculator");
			
			while(!terminate) {
				op = client.userInput();
				if( op.equals("+")) {
					result = calc.Add(client.a, client.b);
					System.out.println("Result = "+result);
				}
				else if (op.equals("-")){
					result = calc.Subtract(client.a, client.b);
					System.out.println("Result = "+result);
				}
				else if (op.equals("*")) {
					result = calc.Multiply(client.a, client.b);
					System.out.println("Result = "+result);
				}
				else if (op.equals("/")) {
					result = calc.Divide(client.a, client.b);
					System.out.println("Result = "+result);
				}
				else if (op.equals("exit")) {
					System.out.println("\nGoodByeeee!!");
					break;
				} else {
					System.out.println("\nWrong Input\n");
				}
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String  userInput(){
		System.out.print("Enter message: ");
		String msg;
		try {
			msg = in.readLine();
			msg.trim();
			if(!msg.equals("exit")){
				String[] expression = msg.split(" ");
				if(expression.length == 3){
					if (expression[0].matches("[+\\-*/]") && expression[1].matches("\\d+") && expression[2].matches("\\d+") ){
						a = Double.parseDouble(expression[1]);
						b = Double.parseDouble(expression[2]);
						return expression[0];
					}
				}
			} else {
				return "exit" ;
			}
		} catch (IOException e) {
			System.out.println("User Input failed");
			e.printStackTrace();
			return null;
		}
		return "error";
		
	}

}
