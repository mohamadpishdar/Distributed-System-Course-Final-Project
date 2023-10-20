    import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
     
    public class ChatServer  extends UnicastRemoteObject implements ChatServerInt{
    	
    	private Vector v=new Vector();	
    	public ChatServer() throws RemoteException{}
    		
    	public boolean login(ChatClientInt a) throws RemoteException{	
    		System.out.println(a.getName() + "  got connected....");	
    		a.tell("You have Connected successfully.");
    		publish(a.getName()+ " has just connected.");
    		v.add(a);
    		
    		return true;		
    	}
    	
    	
    	public void publish(String s) throws RemoteException{
    	    System.out.println(s);
    		for(int i=0;i<v.size();i++){
    		    try{
    		    	ChatClientInt tmp=(ChatClientInt)v.get(i);
    			tmp.tell(s);
    		    }catch(Exception e){
    		    	//problem with the client not connected.
    		    	//Better to remove it
    		    }
    		}
    	}
    public void	privateChat (InetAddress ip1 ,InetAddress ip2 , int i ,int p2,String name1 , String name2,int count){
    	ChatClientInt tmp1=(ChatClientInt)v.get(i);
		try {
			tmp1.ptell(ip1,ip2,name1,name2,i,p2,count );
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	}
    	public Vector getConnected() throws RemoteException{
    		return v;
    	}

    	public void exit(ChatClientInt a) throws RemoteException{	
    	
    		publish(a.getName()+ " has just exit.");
    		v.remove(a);
    		
    			
    	}
    	
		public void privateChat(int nn) throws RemoteException {
			// TODO Auto-generated method stub
			
		}
    }