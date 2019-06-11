package guiView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel implements PropertyChangeListener {

	private JScrollPane playerList;
	private JPanel panel;
	private GameEngine gameEngine;
	private Border border;
	private JLabel lblName;
	private JLabel lblPoints;
	private JLabel lblBetType;
	private JLabel lblBet;
	private JLabel lblPlayerName;
	private JLabel lblPlayerPoints;
	private JLabel lblPlayerBetType;
	private JLabel lblPlayerBet;
	private JLabel lblPlayerWinLoss;
	private JLabel lblWinLoss;
	private JPanel panel2;
	private Slot winningSlot;
	private HashMap<String, Integer> oldPlayers = new HashMap<String, Integer>();


	public SummaryPanel(GameEngine gameEngine, Viewmodel viewmodel, int width, int height) {

		this.gameEngine = gameEngine;

		panel = new JPanel(new GridLayout(0, 1));

		populatePanel();
		playerList = new JScrollPane(panel);
		playerList.setPreferredSize(new Dimension(width / 4, height / 4));
		playerList.setBorder(border);
		
		add(playerList);

	}

	private void populatePanel() {

		border = BorderFactory.createLineBorder(Color.BLACK, 1);
		for (Player player : gameEngine.getAllPlayers()) {

			panel2 = new JPanel();
			panel2.setBorder(border);
			panel2.setLayout(new GridLayout(0, 2));

			lblName = new JLabel("name	: ");
			lblPoints = new JLabel("point	: ");
			lblBet = new JLabel("bet	: ");
			lblBetType = new JLabel("betType	: ");
			lblWinLoss = new JLabel("winLoss 	: ");


			if (winningSlot != null && player.getBetType() != null) {
				lblPlayerWinLoss = new JLabel(winLossStatus(player));
			}
				
			else
				lblPlayerWinLoss = new JLabel("not available");
			
			if (player.getBetType() != null)
				lblPlayerBetType = new JLabel(player.getBetType().name());

			else
				lblPlayerBetType = new JLabel("none");

			lblPlayerName = new JLabel(player.getPlayerName());
			lblPlayerBet = new JLabel(Integer.toString(player.getBet()));
			lblPlayerPoints = new JLabel(Integer.toString(player.getPoints()));

			panel2.add(lblName);
			panel2.add(lblPlayerName);
			panel2.add(lblPoints);
			panel2.add(lblPlayerPoints);
			panel2.add(lblBet);
			panel2.add(lblPlayerBet);
			panel2.add(lblBetType);
			panel2.add(lblPlayerBetType);
			panel2.add(lblWinLoss);
			panel2.add(lblPlayerWinLoss);

			panel.add(panel2);
		}

	}

	private String winLossStatus(Player player) {

		if (player.getBetType().name().equals(winningSlot.getColor().name()))
			return "WIN " + oldPlayers.get(player.getPlayerId());
		else
			return "LOST "+ oldPlayers.get(player.getPlayerId());

	}

	private void refreshData() {
		panel.removeAll();
		// playerList.removeAll();

		populatePanel();
		panel.revalidate();
		panel.repaint();
	}


	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		playerList.setPreferredSize(new Dimension(getParent().getWidth()/4, getParent().getHeight()/4));

	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("newPlayerAdded")) {
			refreshData();
		}

		if (evt.getPropertyName().equals("playerPlaceBet")) {
			refreshData();
		}
		if (evt.getPropertyName().equals("playerDeleted")) {

			refreshData();
		}
		if (evt.getPropertyName().equals("winningSlot")) {
			winningSlot = (Slot) evt.getNewValue();
			oldPlayers = (HashMap<String, Integer>) evt.getOldValue();
			refreshData();
			winningSlot = null;
		}

	}

}
