    import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

    import java.awt.*;
import java.awt.event.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
     
    public class ChatUI{
      private ChatClient client;
      public ChatServerInt server;
      int my;
      String Name;
      InetAddress myip; 
	 boolean  p12=false;

      int p2;
      public void doConnect(){
    	    if (connect.getText().equals("Connect")){
    	    	if (name.getText().length()<2){JOptionPane.showMessageDialog(frame, "You need to type a name."); return;}
    	    	if (ip.getText().length()<2){JOptionPane.showMessageDialog(frame, "You need to type an IP."); return;}	    	
    	    	try{
    	    		myip=Inet4Address.getLocalHost();
    				client=new ChatClient(name.getText(),myip);
    				Name=name.getText();
    	    		client.setGUI(this);
    				server=(ChatServerInt)Naming.lookup("rmi://"+ip.getText()+"/myabc");
    				server.login(client);
    		p2=server.getConnected().size();
    			    connect.setText("Disconnect");			    
    	    	}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(frame, "ERROR, we wouldn't connect....");}		  
    	      }else{
    	    	  try {
					server.exit(client);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    	  	//updateUsers(null);
    	    	  	connect.setText("Connect");
    	    	  	//Better to implement Logout ....
    		}
    	  }  
      
      public void sendText(){
        if (connect.getText().equals("Connect")){
        	JOptionPane.showMessageDialog(frame, "You need to connect first."); return;	
        }
          String st=tf.getText();
          st="["+name.getText()+"] "+st;
          tf.setText("");
          //Remove if you are going to implement for remote invocation
          try{
        	  	server.publish(st);
      	  	}catch(Exception e){JOptionPane.showMessageDialog(frame, "Server Fail"); return;}
      }
     
      public void writeMsg(String st){  tx.setText(tx.getText()+"\n"+st);  }
     
      public void updateUsers(Vector v){
    	  my=v.size()-1;
          DefaultListModel listModel = new DefaultListModel();
          if(v!=null) for (int i=0;i<v.size();i++){
        	  
        	  try{  String tmp=((ChatClientInt)v.get(i)).getName();
        	  		listModel.addElement(tmp);
        	  }catch(Exception e){e.printStackTrace();}
          }
          lst.setModel(listModel);
          
          
      }
     
      public static void main(String [] args){
    	System.out.println("Hello World !");
    	try {
			System.out.println(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ChatUI c=new ChatUI();
      }  
      
      //User Interface code.
      public ChatUI(){
    	    frame=new JFrame("Group Chat");
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
    	    top.add(new JLabel("Your name: "));top.add(name);    
    	    top.add(new JLabel("Server Address: "));top.add(ip);
    	    top.add(connect);
    	    cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
    	    cn.add(lst, BorderLayout.EAST);    
    	    bottom.add(tf, BorderLayout.CENTER);    
    	    top.add(pt);
    	    bottom.add(bt, BorderLayout.EAST);

    	    main.add(top, BorderLayout.NORTH);
    	    main.add(cn, BorderLayout.CENTER);
    	    main.add(bottom, BorderLayout.SOUTH);
    	    main.setBorder(new EmptyBorder(10, 10, 10, 10) );
    	    //Events
    	    connect.addActionListener(new ActionListener(){
    	      public void actionPerformed(ActionEvent e){ doConnect();   }  });
    	    bt.addActionListener(new ActionListener(){
    	      public void actionPerformed(ActionEvent e){ sendText();   }  });
    	    tf.addActionListener(new ActionListener(){
    	      public void actionPerformed(ActionEvent e){ sendText();   }  });
    	    pt.addActionListener(new ActionListener(){
      	      public void actionPerformed(ActionEvent e){ try {
				updateUsers(server.getConnected());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  }  });
    	    
    	    lst.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent arg0) {
					  String selected = (String) lst.getSelectedValue();
						System.out.println("no ");

				    		try {
				    			String tmp3;
				    			int i1=0;
								while (i1<server.getConnected().size()){
								tmp3=((ChatClientInt)server.getConnected().get(i1)).getName();
									if (selected.compareToIgnoreCase(tmp3)==0 && p12==false ){
										System.out.println(i1);								
										Client1 man1 =new Client1() ;   
									InetAddress youip	=((ChatClientInt)server.getConnected().get(i1)).getip();
									Chat ch =new Chat(youip);
										PrivateUi pp=new PrivateUi(i1,p2,myip,youip,server,Name,tmp3,true,man1,ch);
									client.pui=pp;
								      
										p12=true;
										i1=100;
									}
									 i1++;
										
								}
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    		
				}
			});
    	    
    	   
    	    frame.setContentPane(main);
    	    frame.setSize(600,600);
    	    frame.setVisible(true);  
    	    
    	    main.setBackground(Color.YELLOW);  //Whatever color
    	    tx.setBackground(Color.GREEN);  //Whatever color
    	    tf.setBackground(Color.GRAY);  //Whatever color
    	    ip.setBackground(Color.RED);  //Whatever color
    	    name.setBackground(Color.RED);  //Whatever color
    	    connect.setBackground(Color.magenta);  //Whatever color
    	    bt.setBackground(Color.magenta);  //Whatever color
    	    
    	  }
    	  JTextArea tx;
    	  JTextField tf,ip, name;
    	  JButton connect;
    	  JList lst;
    	  JFrame frame;
    }