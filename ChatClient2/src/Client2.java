
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;

import org.omg.CORBA.portable.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Client2 implements Runnable {
	private Thread th ;
	ServerSocket ss; 
	public PrivateUi pui;
	 public static final String DEFAULT_ENCODING = "UTF-8"; 
	    static BASE64Encoder enc = new BASE64Encoder();
	    static BASE64Decoder dec = new BASE64Decoder();
	   BufferedReader dis;
	boolean isfirst=true;
	void start (   BufferedReader d,PrivateUi p) {
		th=new Thread(this , "Client1");
		dis=d;
		pui=p;
		th.start();
		
	}
	
	void stop () {
		th.stop();
		
		
	}
	

    public static String base64decode(String text) {
        try {
            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }//base64decode
    
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
	public  void run()  {
		System.out.println("3");
			 String clientSentence;
			       String key = "key phrase used for XOR-ing";
		 while (true){
			
			try {
				//  clientSentence = dis.readLine();
		         //  System.out.print("encoded: " + clientSentence);
		         //  if ( xorMessage(base64decode(dis.readLine()), key).compareTo("-1-1-1")==0){
			  		//     pui.writeMsg( "Friend Close Chat" );

			       //    }
			  		     pui.writeMsg( xorMessage(base64decode(clientSentence=dis.readLine()), key));
			  		    if ( xorMessage(base64decode(clientSentence), key).compareTo("-1-1-1")==0){
					  		     pui.writeMsg( "Friend Close Chat" );
                                 stop();
					         }
			          
						//DataInputStream dis= new DataInputStream(ss.accept().getInputStream());
		
      //  outToClient.writeBytes(capitalizedSentence);
	//	String str = (String)dis.readUTF();
		 
		//	System.out.println("message= "+str);  

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		
			
		 
	}	 
	}
}
