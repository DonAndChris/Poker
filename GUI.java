import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//TODO PokerTimer on the corner
//TODO Make 8 seats for Poker table and randomly assign each seat to a player
//TODO Implement Call, Raise, Fold
//TODO Methods that creates card, tables, and blinds
public class GUI {
	private static JFrame mainFrame;
	private static JPanel tablePanel;
	private static JPanel cardPanel;
	private static JPanel buttonPanel;

	private static JLabel messageLabel;
	private static JLabel balanceLabel = new JLabel("Balance: $0.00");
	private static JLabel timeLeftLabel = new JLabel("Time Left: 0:00");
	private static HashMap<Integer, String> cardMap;
	private static PokerTable currTable;
	private static Deck theDeck;
	
	public static void initGUI(){
		mainFrame = new JFrame();
//		mainFrame.setLayout(null);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		mainFrame.setTitle("Moody Poker");
		
		cardMap = new HashMap<Integer, String>();
		for(int i = 1; i <= 52; i++){
			cardMap.put(i, "images/" + i + ".png");
		}
		
		tablePanel = new JPanel();
		cardPanel = new JPanel();
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JButton button = new JButton("Raise");
		button.addActionListener(new Raise());
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(button, c);

		button = new JButton("Call");
		button.addActionListener(new Call());
		c.gridx = 1;
		buttonPanel.add(button, c);
		
		button = new JButton("Fold");
		button.addActionListener(new Fold());
		c.gridx = 2;
		buttonPanel.add(button, c);
		
		JButton button2 = new JButton("New Game");
		button2.setBounds(rect(500, 300, button.getPreferredSize()));
		button2.addActionListener(new NewGame());
		mainFrame.add(button2);
		
		buttonPanel.setBounds(rect(500, 200, buttonPanel.getPreferredSize()));
		mainFrame.add(buttonPanel);
		messageLabel = new JLabel("\n\n\n\n");
		messageLabel.setBounds(rect(450,250, messageLabel.getPreferredSize()));
		messageLabel.setText("");
		
		balanceLabel = new JLabel("Balance: $999999.99");
		balanceLabel.setBounds(rect(475, 150, balanceLabel.getPreferredSize()));
		balanceLabel.setText("Balance: $0.00");
		
		timeLeftLabel = new JLabel("Time Left: 999:99");
//		timeLeftLabel.setBoudns();
//		timeLeftLabel.setText();
		
		balanceLabel.setVisible(true);
		messageLabel.setVisible(true);
		
		balanceLabel.repaint();
		messageLabel.repaint();
		
		mainFrame.add(balanceLabel);
		mainFrame.add(messageLabel);
		
		mainFrame.repaint();
		mainFrame.validate();
	}
	
	
	private static Rectangle rect(int x, int y, Dimension size) {
		Rectangle result = new Rectangle(new Point(x,y), size);
		return result;
	}

	private static JLabel iconizeCard(int cardID){
		JLabel label = new JLabel(new ImageIcon(cardMap.get(cardID)));
		label.setBorder(null);
		return label;
	}

	public static void fillTable(){
		ArrayList<Player> currPlayers = currTable.getPlayers();
		if(currPlayers.size() == 1){
			gameOver();
		}
		
		for(Player player : currPlayers){
			balanceLabel.setText("Balance: $" + centsToDollars(player.getCents()));
		}
//		timeLeftLabel.setText();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		tablePanel.removeAll();
		tablePanel.setLayout(new GridBagLayout());
		
		JPanel cardPanel = new JPanel();
//		cardPanel.add(iconizeCard(theDeck.draw().getCardID()));
//		cardPanel.add(iconizeCard(theDeck.draw().getCardID()));
		// add randomized cards with for loop
//		cardPanel.setBounds(rect(x, y, size));
		cardPanel.validate();
		mainFrame.add(cardPanel);
		mainFrame.validate();
		
	}
	
	private static String centsToDollars(int balance) {
		int cents = balance % 100;
		int dollars = (balance - cents) / 100;
		return dollars +"." + cents;
	}

	// method that creates two cards representing a hand for each player
	
	// method that creates an entire table
	
	// method that creates five cards in the middle of the table dealt by the dealer
	
	// method that creates a big blind and a small blind 
	
	private static class Raise implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO ask for how much to raise
			
		}
		
	}
	
	private static class Call implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class Fold implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class NewGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currTable = new PokerTable();
			buttonPanel.setVisible(true);
			//TODO Users fill up players
			currTable.initPlayers();
			mainFrame.validate();
			fillTable();
		}
		
	}
	
	private static void gameOver(){
		buttonPanel.setVisible(false);
		messageLabel.setText("Game Over");
		mainFrame.validate();
		mainFrame.repaint();
	}
}
