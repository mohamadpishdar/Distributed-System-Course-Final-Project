    import java.net.InetAddress;
import java.rmi.*;
     
    public interface ChatClientInt extends Remote{	
    	public void tell (String name)throws RemoteException ;
    	public String getName()throws RemoteException ;
    	public InetAddress getip()throws RemoteException ;
    	public void ptell (InetAddress ip1,InetAddress ip2, String name1, String name2,int nn,int p2,int count)throws RemoteException ;

    }