    import java.net.InetAddress;
import java.rmi.*;
import java.util.*;
     
    public interface ChatServerInt extends Remote{	
    	public boolean login (ChatClientInt a)throws RemoteException ;
    	public void publish (String s)throws RemoteException ;
    	public Vector getConnected() throws RemoteException ;
    	public void privateChat (InetAddress ip1 ,InetAddress ip2 , int i ,int p2,String name1 , String name2,int count) throws RemoteException ;
    	public void exit(ChatClientInt a) throws RemoteException;	

    }