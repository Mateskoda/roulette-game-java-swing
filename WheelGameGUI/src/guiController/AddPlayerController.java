package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import guiView.AddPlayer;
import guiView.AddPlayerFrame;
import guiView.DialogMessage;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

public class AddPlayerController implements ActionListener{
	
	private String playerName;
	private String points;
	private GameEngine gameEngine;
	private UUID uuid;
	private String playerId;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private boolean isPlayerExist;
	private AddPlayerFrame addPlayerFrame;
	private JTextField tfPlayerName;
	private JTextField tfPoints;

	
	public AddPlayerController() {

	}
	
	public AddPlayerController(AddPlayer addPlayer, GameEngine gameEngine, Viewmodel viewmodel, 
								AddPlayerFrame addPlayerFrame, JTextField tfPlayerName, JTextField tfPoints) {
	
		addPropertyChangeListener(viewmodel);
		this.gameEngine = gameEngine;
		this.addPlayerFrame = addPlayerFrame;
		this.tfPlayerName = tfPlayerName;
		this.tfPoints = tfPoints;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		playerName = tfPlayerName.getText().toString();
		points = tfPoints.getText().toString();
		
        if(playerName != null|| points != null) {
        	checkNameBeforeSave();
        }
        else
        	new DialogMessage("please fill all fields correctly");
		
	}
	
	private void checkNameBeforeSave() {
		
		
		
		isPlayerExist = false;
		
		if(gameEngine.getAllPlayers().size() != 0) {
    		for(Player player : gameEngine.getAllPlayers()) {
    			if(player.getPlayerName().equals(playerName)) {
    				isPlayerExist = true;
    				JOptionPane.showMessageDialog(null, "name already exist, pick other name");
    				addPlayerFrame.dispatchEvent(new WindowEvent(addPlayerFrame, WindowEvent.WINDOW_CLOSING));
    			}
    		}
    		if(!isPlayerExist)
    			savePlayer();
    	}
    	else 
    		savePlayer();
	}
	
	private void savePlayer() {
		
		uuid = UUID.randomUUID();
        playerId = uuid.toString();
        
        try {
        	Player player = new SimplePlayer(playerId, playerName, Integer.parseInt(points));
    		gameEngine.addPlayer(player);
    		pcs.firePropertyChange("newPlayerAdded", null, player);
    		new DialogMessage("player saved");
        }
        catch(NumberFormatException e) {
        	new DialogMessage("please valid point");
        } 
        addPlayerFrame.dispatchEvent(new WindowEvent(addPlayerFrame, WindowEvent.WINDOW_CLOSING));

	}

	 public void addPropertyChangeListener(PropertyChangeListener newListener) {
	        pcs.addPropertyChangeListener(newListener);
	  }
	 

}
