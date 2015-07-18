package Interface;
import java.awt.*;

import javax.swing.*;

import Common.Message;
import Common.User;
import Pattern.Connection;
import Tools.ManageChat;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class UserList extends JFrame implements ActionListener, MouseListener{
	
	JPanel jp1_1, jp1_2, jp1_3;
	JPanel jp2_1, jp2_2, jp2_3;
	JButton button1_1, button1_2, button1_3;
	JButton button2_1, button2_2, button2_3;
	JScrollPane jsp1, jsp2;
	JLabel label[];
	String tempOwnerID;
	
//	public static void main(String[] args) {
//		new UserList();
//	}
	
	public UserList(String ownerID) {
		
		tempOwnerID = ownerID;
		
		button1_1 = new JButton("friends");
		button1_2 = new JButton("chart");
		button1_2.addActionListener(this);
		button1_2.setActionCommand("command1_2");
		button1_3 = new JButton("blacklist");
		
		button2_1 = new JButton("friends");
		button2_1.addActionListener(this);
		button2_1.setActionCommand("command2_1");
		button2_2 = new JButton("stranger");
		button2_3 = new JButton("blacklist");
		
		jp1_1 = new JPanel();
		jp1_1.setLayout(new GridLayout(1,1));
		jp1_1.add(button1_1);
		
		jp1_2 = new JPanel();
		jp1_2.setLayout(new GridLayout(50,1));
	
		/*
		 * �ж�˭���ߡ�
		 */
		User u = new User();
		u.setId(ownerID);
		String optype = "getOnline";
		u.setType(optype);
		System.out.println("HI i'm in Userlist 58");
		ArrayList<Object> OnlineFriends = (new Connection().getOnlineFriend(u));
		
	    int enables[] = new int[1000];
	    
	    for (int i = 0; i < enables.length; ++i){
	    	enables[ i ] = 0;
	    }
	    //��ȡ�յ��ĺ����б�
	    for (int i = 0; i < OnlineFriends.size(); ++i){
	        int val = Integer.parseInt((String)(OnlineFriends.get(i)));
	    	enables[val] = 1;
	    	System.out.print("online member is " + val);
	    }
	    System.out.print("\n");
	    
		
		label = new JLabel[50];
		for (int i=0; i < label.length; i++) { // length is no a function!!!!!!!!!!!
			int number = new Random().nextInt(12) + 1;
			String path = "image/" + number +  ".png";
			label[i] = new JLabel(1+i+"", new ImageIcon(path), JLabel.LEFT);
			label[i].addMouseListener(this);
			label[i].setEnabled(false);
			if (label[i].getText().equals(ownerID) || enables[ i + 1 ] == 1) { // label can getText too!
				label[i].setEnabled(true);
			}
			jp1_2.add(label[i]);
		}
		
		jsp1 = new JScrollPane(jp1_2);
		
		jp1_3 = new JPanel();
		jp1_3.setLayout(new GridLayout(2,1));
		jp1_3.add(button1_2);
		jp1_3.add(button1_3);
	
		this.add(jp1_1,BorderLayout.NORTH);
		this.add(jsp1);
		this.add(jp1_3,BorderLayout.SOUTH);
		
		this.setSize(200, 600);
		this.setLocation(400, 100);
		this.setTitle("User "+ownerID);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("command1_2")){
		    System.out.println("I'M in chartlist listener 106");
			ChartList charts = new ChartList();	
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			String friendNum = ((JLabel)e.getSource()).getText(); // getSource()!! return the component
			System.out.println("chat with"+friendNum);
			Chat c = new Chat(tempOwnerID, friendNum);
			ManageChat.addChat(tempOwnerID+" "+friendNum, c);
			
			System.out.println("keyy is !: " + tempOwnerID + " " + friendNum);
			//Thread t = new Thread(c);
			//t.start();
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel J1 = ((JLabel)e.getSource());
		J1.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel J1 = ((JLabel)e.getSource());
		J1.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
