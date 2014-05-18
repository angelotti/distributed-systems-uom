import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CalcInterface extends Remote{
	public double Add(double a, double b)  throws RemoteException;
	public double Subtract(double a, double b)  throws RemoteException;
	public double Multiply(double a, double b)  throws RemoteException;
	public double Divide(double a, double b)  throws RemoteException;
}
