package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import guiView.DialogMessage;
import guiView.PlaceBet;
import guiView.PlaceBetFrame;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

public class PlaceBetController implements ActionListener{

	private GameEngine gameEngine;
	private String bet;
	private Player player;
	private String betTypeString;
	private boolean isAllBetPlaced;
	private PlaceBetFrame placeBetFrame;
	private JTextField tfBet;
	private JComboBox<BetType> cbBetType;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);


	public PlaceBetController(PlaceBet placeBet,GameEngine gameEngine, Viewmodel viewmodel, 
							PlaceBetFrame placeBetFrame, Player player, JTextField tfBet, JComboBox<BetType> cbBetType) {
		this.player = player;
		this.gameEngine = gameEngine;
		this.placeBetFrame = placeBetFrame;
		addPropertyChangeListener(viewmodel);
		this.tfBet = tfBet;
		this.cbBetType = cbBetType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		bet = tfBet.getText();
		betTypeString = cbBetType.getSelectedItem().toString();
		System.out.println("BET " + bet);
		try {
        	if(gameEngine.placeBet(gameEngine.getPlayer(player.getPlayerId()), Integer.parseInt(bet), BetType.valueOf(betTypeString))) {
        		new DialogMessage("bet successfully added");
        		pcs.firePropertyChange("playerPlaceBet", null, null);
        	}
        	else
        		new DialogMessage("bet has to be smaller than initial point, please valid bet");
        }
        catch(Exception e1) {
        	new DialogMessage("bet has to be a number");
        } 
		
		spinOrNot();
		placeBetFrame.dispatchEvent(new WindowEvent(placeBetFrame, WindowEvent.WINDOW_CLOSING));
		
	}
	
	
	private void spinOrNot() {

		isAllBetPlaced = true;
		
		for(Player player : gameEngine.getAllPlayers()) {
			if(player.getBet() == 0 || player.getBetType() == null)
				isAllBetPlaced = false;
		}
		
		if(isAllBetPlaced) {

			new Thread()
			{
				@Override
				public void run()
				{
					pcs.firePropertyChange("wheelIsSpinning", null, null);
					gameEngine.spin(1, 200, 4);
				}
			}.start();
		
		}
	
	}
	
	public void addPropertyChangeListener(PropertyChangeListener newListener) {
        pcs.addPropertyChangeListener(newListener);
	}
	

}
