import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;

import javax.swing.JOptionPane;

import org.omg.CORBA.portable.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Client1 implements Runnable {
	private Thread th ;
	Chat c;
	public PrivateUi pui;
	public boolean isfirst=true;
	DataInputStream dis;
	  Socket connectionSocket;
	  public static final String DEFAULT_ENCODING = "UTF-8"; 
	    static BASE64Encoder enc = new BASE64Encoder();
	    static BASE64Decoder dec = new BASE64Decoder();
	DataOutputStream ou;
	ServerSocket ss;
	Chat b;
	
	void start (Chat a,PrivateUi p) {
		b=a;
		th=new Thread(this , "Client1");
		pui=p;
		th.start();
	//	th.setPriority(10);
	}
	
	void stop () {
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		th.stop();
		
		
	}
	
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
    }//xorMessage

	
	  public static String base64decode(String text) {
	        try {
	            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
	        } catch (IOException e) {
	            return null;
	        }
	    }//base64decode
	
	
	
	public  void run()  {
		  String clientSentence;
	      String capitalizedSentence;

		 System.out.println("aa");
	     try {
			ss = new ServerSocket(6666);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 while (true){
		  
			try {
				if (isfirst==true){
	
		
		        

	        connectionSocket = ss.accept();	
	        isfirst=false;
	        b.isfirst=false;
	        
            b.dout = new DataOutputStream( connectionSocket.getOutputStream());
System.out.println(isfirst);
		
		/*
		 System.out.println("aa");
		dis= new DataInputStream(ss.accept().getInputStream());
	    ou=ss.accept().getOutputStream();
	      if (ss.accept().isConnected())
			isfirst=false;
	      System.out.println("aa");
	      */
				}
		        	b.br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

		         //  DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		           clientSentence = b.br.readLine();
		           String key = "key phrase used for XOR-ing";
		           System.out.print("XOR-ing back to original: " + xorMessage(base64decode(clientSentence), key));
		           if (xorMessage(base64decode(clientSentence), key).compareTo("-1-1-1")==0){
		  		     pui.writeMsg( "Friend Close Chat" );
		  		     stop();

		           }
		           else {
		     pui.writeMsg( xorMessage(base64decode(clientSentence), key));
		           }
  //  String str = (String)dis.readUTF();
		//	System.out.println("message= "+str);  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		
			
		 
	}	 
	}
}