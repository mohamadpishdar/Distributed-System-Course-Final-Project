    import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

    import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
     
    public class PrivateUi{
    	boolean run1;
    	int count =0;
      public static void main(String [] args){
    	System.out.println("Hello World !");
    	//PrivateUi c1=new PrivateUi();
    	
      }  
      public void writeMsg(String st){  tx.setText(tx.getText()+"\n"+st);  }

      //User Interface code.
      public PrivateUi( final int b1, final int b2 ,final ChatServerInt server,final boolean run,final String name1){
    	    frame=new JFrame("G Chat");
    	    run1=run;
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
			
    	    bt.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String st=tf.getText();
			          st="["+name1+"] "+st;
			          tf.setText("");
					try {
					if (run1==true){
						server.privateChat(b1,st,b2,0);
				run1=false;
					}
					else 
						server.privateChat(b1,st,b2,1);

						

						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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