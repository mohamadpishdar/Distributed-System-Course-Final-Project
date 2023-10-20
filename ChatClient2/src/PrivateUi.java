    import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

    import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
     
    public class PrivateUi{
    	 
    	int count =0;
    	 PrivateUi pp; 
         Client1 man1;

    	
      public static void main(String [] args){
    	System.out.println("Hello World !");
    	//PrivateUi c1=new PrivateUi();
    	
    	
      }  
      public void writeMsg(String st){  tx.setText(tx.getText()+"\n"+st);  }

      //User Interface code.
      public PrivateUi( final int p1,final int p2,final InetAddress ip1,final InetAddress ip2 ,final ChatServerInt server,final String name1,final String name2, final boolean run,final Client1 man,final Chat ch){
    	    frame=new JFrame(name1);
    	
    	    JPanel main =new JPanel();
    	    JPanel top =new JPanel();
    	    JPanel cn =new JPanel();
    	    JPanel bottom =new JPanel();
    	    ip=new JTextField();
    	    tf=new JTextField();
    	    name=new JTextField();
    	    tx=new JTextArea();
    	    connect=new JButton("Connect");
        JButton bt=new JButton("Send");
    	    JButton pt=new JButton("Private");

    	    lst=new JList();        
    	    main.setLayout(new BorderLayout(5,5));         
    	    top.setLayout(new GridLayout(1,0,5,5));   
    	    cn.setLayout(new BorderLayout(5,5));
    	    bottom.setLayout(new BorderLayout(5,5));
    	 //   top.add(new JLabel("Your name: "));top.add(name);    
    	 //   top.add(new JLabel("Server Address: "));top.add(ip);
    	 //   top.add(connect);
    	    cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
    	    cn.add(lst, BorderLayout.EAST);    
    	    bottom.add(tf, BorderLayout.CENTER);    
    	  //  top.add(pt);
    	    bottom.add(bt, BorderLayout.EAST);

    	    main.add(top, BorderLayout.NORTH);
    	    main.add(cn, BorderLayout.CENTER);
    	    main.add(bottom, BorderLayout.SOUTH);
    	    main.setBorder(new EmptyBorder(10, 10, 10, 10) );
    	    //Events
    	    if (run==true ){
    	    	
    	    try { 
				
 server.privateChat (ip1 , ip2 ,  p1, p2, name1 , name2, 0);
			 

man.start(ch,this);
pp=this;
		
    	    } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Connection fail"); return;
			}
    	    }
    	    bt.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String st=tf.getText();
			          st="["+name1+"] "+st;
			          tf.setText("");
					
					
				
			          
			          
			          
			      	
			

			   String sentence ="";
			try {
				sentence = st;
				if (sentence!="" && ch.isfirst==true){
				ch.start(pp);
				man.stop();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeMsg(sentence);  
			ch.send(sentence);	
			
				

							 
							
							 

				
					

						
						
					
				}
			});
    	    
    	    frame.addWindowListener(new WindowAdapter()
    	    {
    	        public void windowClosing(WindowEvent e)
    	        {
    				ch.send("-1-1-1");	

    	        }
    	    });
    	    
    	    lst.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent arg0) {
					  String selected = (String) lst.getSelectedValue();
					  JOptionPane.showMessageDialog(frame, selected);
					  
					
				}
			});
    	    
    	   
    	    frame.setContentPane(main);
    	    frame.setSize(300,300);
    	    frame.setVisible(true);  
    	    
    	    
    	  }
    	  JTextArea tx;
    	  JTextField tf,ip, name;
    	  JButton connect;
    	  JList lst;
    	  JFrame frame;
    }