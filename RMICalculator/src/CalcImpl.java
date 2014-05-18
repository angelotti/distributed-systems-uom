import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class CalcImpl extends UnicastRemoteObject implements CalcInterface{

	public CalcImpl() throws RemoteException {
		super();
	}

	@Override
	public double Add(double a, double b) throws RemoteException {
		System.out.println("Received: "+a+" + "+b+"\n");
		return a + b;
	}

	@Override
	public double Subtract(double a, double b) throws RemoteException {
		System.out.println("Received: "+a+" - "+b+"\n");
		return a - b;
	}

	@Override
	public double Multiply(double a, double b) throws RemoteException {
		System.out.println("Received: "+a+" * "+b+"\n");
		return a * b;
	}

	@Override
	public double Divide(double a, double b) throws RemoteException {
		System.out.println("Received: "+a+" / "+b+"\n");
		return a / b;
	}
	

}
