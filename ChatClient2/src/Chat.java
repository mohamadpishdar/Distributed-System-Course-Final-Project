import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class Chat {
	
	 public static final String DEFAULT_ENCODING = "UTF-8"; 
	    static BASE64Encoder enc = new BASE64Encoder();
	    static BASE64Decoder dec = new BASE64Decoder();
	
	static boolean isfirst =true;
	static DataOutputStream dout;
	static BufferedReader br=null;
	 InetAddress Destip;
	 Socket clientSocket;
	 Client2 c2;
	
	Chat (InetAddress ip){
		Destip=ip;
		
	}
	
    public static String base64encode(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }//base64encode
	
	 public static String xorMessage(String message, String key) {
	        try {
	            if (message == null || key == null) return null;

	            char[] keys = key.toCharArray();
	            char[] mesg = message.toCharArray();

	            int ml = mesg.length;
	            int kl = keys.length;
	            char[] newmsg = new char[ml];

	            for (int i = 0; i < ml; i++) {
	                newmsg[i] = (char)(mesg[i] ^ keys[i % kl]);
	            }//for i

	            return new String(newmsg);
	        } catch (Exception e) {
	            return null;
	        }
	    }//xorM
	 
	 
	public void send (String a) {
		
		
	     String txt = a;
	        String key = "key phrase used for XOR-ing";
	        System.out.println(txt + " XOR-ed to: " + (txt = xorMessage(txt, key)));

	        String encoded = base64encode(txt);       
	       // System.out.println(" is encoded to: " + encoded + " and that is decoding to: " + (txt = base64decode(encoded)));

		
	     try {
			dout.writeBytes(encoded + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

public void start (PrivateUi p) {
	    
            Socket s = null;
	      

	     
  		 try {
  			

  			
  				 
  			
  				 

  	clientSocket = new Socket(Destip, 6666);
  		       
  	   dout = new DataOutputStream(clientSocket.getOutputStream());
  	  br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  

isfirst=false;
 c2=new Client2();
c2.start(br,p);

  			 
  		
  		
		} catch(Exception e){System.out.println(e);} 
  		 
  		
 
		    		 
	 
	 }
	 }     
	    

