    import java.rmi.*;
import java.util.*;
     
    public interface ChatServerInt extends Remote{	
    	public boolean login (ChatClientInt a)throws RemoteException ;
    	public void publish (String s)throws RemoteException ;
    	public Vector getConnected() throws RemoteException ;
    	public void privateChat (int nn , String ss , int b2,int count ) throws RemoteException ;

    }