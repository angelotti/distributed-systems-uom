import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalcServer {
	
	public static void main (String args[]) {
		try {
			
			CalcImpl c = new CalcImpl();
			
			Registry registry = LocateRegistry.createRegistry(4444);
			registry.bind("RMICalculator", c);
			
			//String rmiObjectName = "rmi://localhost/Calculator";
			//Naming.rebind(rmiObjectName, c);
			
			System.out.println("Calc Server ready");
		}
		catch (Exception e) {
			System.out.println("Calc Server failed: "+e);
		}
	}

}
