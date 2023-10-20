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
    public void	privateChat (int nn , String ss, int b2,int count ){
    	ChatClientInt tmp1=(ChatClientInt)v.get(nn);
		try {
			tmp1.ptell(nn,ss, b2 , count );
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	}
    	public Vector getConnected() throws RemoteException{
    		return v;
    	}

		public void privateChat(int nn) throws RemoteException {
			// TODO Auto-generated method stub
			
		}
    }