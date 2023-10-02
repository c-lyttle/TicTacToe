import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonListener;

public class game implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private boolean turn = false;
	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
	private Boolean[] board = new Boolean[9];
	
	public static void main(String[] args) {
		//Creates new game instance
		new game();
	}
	
	public game() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setLayout(new GridLayout(3,3));
		
		//Create each button and add to panel
		for (int i = 0; i < 9; i++) {
			JButton curButton = new JButton();
			curButton.putClientProperty("num", i);
			curButton.addActionListener(this);
			curButton.setFont(new Font("Calibri", Font.PLAIN, 100));
			curButton.setMargin(new Insets(45, 5, 5, 5));
			buttonList.add(curButton);
			panel.add(curButton);
		}
		
		//Set up frame
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicTacToe");
		frame.setSize(new Dimension(400,400));
		frame.setVisible(true);
	}

	//Called on button press
	@Override
	public void actionPerformed(ActionEvent e) {
		//Fetch button identifier
		int i = ((Integer)((JButton)e.getSource()).getClientProperty("num"));
		//If the square has already been played on, break
		if (board[i]!=null){
			return;
		}
		board[i]=turn;
		JButton curButton=buttonList.get(i);
		if (turn==true) {
			curButton.setBackground(Color.red);
			curButton.setText("O");
		}
		else {
			curButton.setBackground(Color.green);
			curButton.setText("X");
		}
		
		/*
		Diagram of board(n) representation:
			 |     |     
		  0  |  1  |  2  
		_____|_____|_____
		     |     |     
		  3  |  4  |  5  
		_____|_____|_____
		     |     |     
		  6  |  7  |  8  
		     |     |     
		 */
		
		if (
			(board[0]==board[1]&&board[1]==board[2]&&board[0]!=null)
			||(board[3]==board[4]&&board[4]==board[5]&&board[3]!=null)
			||(board[6]==board[7]&&board[7]==board[8]&&board[6]!=null)
			||(board[0]==board[3]&&board[3]==board[6]&&board[0]!=null)
			||(board[1]==board[4]&&board[4]==board[7]&&board[1]!=null)
			||(board[2]==board[5]&&board[5]==board[8]&&board[2]!=null)
			||(board[0]==board[4]&&board[4]==board[8]&&board[0]!=null)
			||(board[2]==board[4]&&board[4]==board[6]&&board[2]!=null)
			)
			{
			System.out.println("GAMEOVER");
			frame.dispose();
		}
		
		turn = !turn;

	}


}
