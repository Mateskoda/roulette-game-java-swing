package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import guiView.PlaceBetFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

public class GoToPlaceBetController implements ActionListener{
	
	private GameEngine gameEngine;
	private Viewmodel viewmodel;
	private Player player;
	JComboBox<Player> cbPlayerList;
	
	public GoToPlaceBetController(GameEngine gameEngine, Viewmodel viewmodel ,JComboBox<Player> cbPlayerList) {
		this.gameEngine = gameEngine;
		this.viewmodel = viewmodel;
		this.cbPlayerList = cbPlayerList;
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		player = (Player)cbPlayerList.getSelectedItem();
			
		PlaceBetFrame placeBetFrame = new PlaceBetFrame(gameEngine, viewmodel, player);
		placeBetFrame.setVisible(true);
		
	}

}
