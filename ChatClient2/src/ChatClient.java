    import java.net.Inet4Address;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;
     
    public class ChatClient  extends UnicastRemoteObject implements ChatClientInt{
    	
    	private String name;
    	private ChatUI ui;	
    	public PrivateUi pui;	
    	public InetAddress ip;
    	

    	public ChatClient (String n,InetAddress ip1) throws RemoteException {
    		name=n;
    		ip=ip1;
    		}
    	
    	public void tell(String st) throws RemoteException{
    		System.out.println(st);
    		ui.writeMsg(st);
    	}
    	public String getName() throws RemoteException{

    		return name;
    	}
    	public InetAddress getip() throws RemoteException{
    		return ip;
    	}
    	public void setGUI(ChatUI t){ 
    		ui=t ; 
    		
    	} 	
    	
    	public void ptell (InetAddress ip1,InetAddress ip2, String name1, String name2,int nn,int p2,int count )throws RemoteException {

    			Client1  man =new Client1() ;  
    			Chat ch =new Chat (ip1);    
    			 pui = new PrivateUi(nn,p2,ip2,ip1,ui.server,name2,name1,false,man,ch) ;
    		          			 man.start(ch,pui); 

    			
    			 		
    
    		
    	}
    	

    }