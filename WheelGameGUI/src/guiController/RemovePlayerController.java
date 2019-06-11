package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JComboBox;

import guiView.DialogMessage;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

public class RemovePlayerController implements ActionListener {
	
	private GameEngine gameEngine;
	private Player player;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	JComboBox<Player> cbPlayerList;

	public RemovePlayerController(GameEngine gameEngine, Viewmodel viewmodel, JComboBox<Player> cbPlayerList) {
		super();
		this.gameEngine = gameEngine;
		addPropertyChangeListener(viewmodel);
		this.cbPlayerList = cbPlayerList;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		player = (Player)cbPlayerList.getSelectedItem();
		System.out.println("idk " + player.getPlayerName());
		if(player != null)
		{
			if(gameEngine.removePlayer(player)) {
				pcs.firePropertyChange("playerDeleted", null, player);
	        	new DialogMessage("player deleted");

			}
			else
        	new DialogMessage("failed to delete playe");

		}
		else
        	new DialogMessage("no player to delete");
			
	}

	public void addPropertyChangeListener(PropertyChangeListener newListener) {
	        pcs.addPropertyChangeListener(newListener);
	  }

}
