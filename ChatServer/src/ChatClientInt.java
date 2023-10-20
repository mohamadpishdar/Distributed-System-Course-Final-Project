    import java.rmi.*;
     
    public interface ChatClientInt extends Remote{	
    	public void tell (String name)throws RemoteException ;
    	public String getName()throws RemoteException ;
    	public void ptell (int nn ,String name, int b2 , int count)throws RemoteException ;

    }