package guiView;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import guiController.PlaceBetController;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class PlaceBet extends JPanel{
	
	private JLabel lblBet;
	private JLabel lblBetType;
	private JTextField tfBet = new JTextField(4);
	private JButton btnPlaceBet = new JButton();
	private JComboBox<BetType> cbBetType;
	private BetType[] betType;
	private PlaceBetController placeBetController;
	
	public PlaceBet(GameEngine gameEngine, Viewmodel viewmodel, PlaceBetFrame placeBetFrame, Player player) {
		
		setLayout(new GridLayout(0,2));
		
		btnPlaceBet.setText("set bet");
		lblBet = new JLabel("bet: ");
		lblBetType = new JLabel("bet type: ");
		
		betType = BetType.values();
		cbBetType = new JComboBox<BetType>(betType);
		
		placeBetController = new PlaceBetController(this, gameEngine,viewmodel, placeBetFrame, player, tfBet, cbBetType);
		
		add(lblBet);
		add(tfBet);
		add(lblBetType);
		add(cbBetType);
		add(btnPlaceBet);
		
		btnPlaceBet.addActionListener(placeBetController);
		
	}

}
