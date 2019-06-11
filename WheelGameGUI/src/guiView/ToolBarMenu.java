package guiView;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import guiController.GoToAddPlayerController;
import guiController.GoToPlaceBetController;
import guiController.RemovePlayerController;
import guiController.SpinController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class ToolBarMenu extends JPanel implements PropertyChangeListener{
	
	private JButton btnGoToAddPlayer = new JButton();
	private JButton btnSpin = new JButton();
	
	private JComboBox<Player> cbPlayerList;
	private GameEngine gameEngine;
	private Player newPlayer;
	private JButton btnRemovePlayer = new JButton();
	private JButton btnPlaceBet = new JButton();
	private GoToPlaceBetController goToPlaceBetController;
	private RemovePlayerController removePlayerController;
	private JToolBar toolBar;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem addPlayerMenu, spinMenu, placeBetMenu, removePlayerMenu;  
	
	public ToolBarMenu(GameEngine gameEngine,  Viewmodel viewmodel) {
		
		setLayout(new GridLayout(2, 1));
		
		toolBar = new JToolBar();
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		
		addPlayerMenu = new JMenuItem("add player"); 
		spinMenu = new JMenuItem("spin"); 
		placeBetMenu = new JMenuItem("place bet"); 
		removePlayerMenu = new JMenuItem("remove player"); 
		
		menu.add(addPlayerMenu);
		menu.add(placeBetMenu);
		menu.add(removePlayerMenu);
		menu.add(spinMenu);
		menuBar.add(menu);		
		btnGoToAddPlayer.setText("Add Player");
		btnSpin.setText("Spin");
		
		addPlayerMenu.addActionListener(new GoToAddPlayerController(gameEngine,  viewmodel ));
		btnGoToAddPlayer.addActionListener(new GoToAddPlayerController(gameEngine,  viewmodel ));
		btnSpin.addActionListener(new SpinController(gameEngine, viewmodel));
		spinMenu.addActionListener(new SpinController(gameEngine, viewmodel));
		
		this.gameEngine = gameEngine;

		
		btnRemovePlayer.setText("remove");
		btnPlaceBet.setText("place bet");
		
		cbPlayerList = new JComboBox<Player>();
		
		if(gameEngine.getAllPlayers().size() != 0) {
			for(Player player: gameEngine.getAllPlayers()) {
				cbPlayerList.addItem(player);		
			}
		}
		cbPlayerList.setSelectedIndex(-1);
		
		cbPlayerList.setRenderer(new PlayerListRenderer());
	
		goToPlaceBetController = new GoToPlaceBetController(gameEngine, viewmodel, cbPlayerList);
		removePlayerController = new RemovePlayerController(gameEngine, viewmodel, cbPlayerList);
		
		removePlayerController.addPropertyChangeListener(this);

		btnPlaceBet.addActionListener(goToPlaceBetController);
		btnRemovePlayer.addActionListener(removePlayerController);
		placeBetMenu.addActionListener(goToPlaceBetController);
		removePlayerMenu.addActionListener(removePlayerController);
		
		cbPlayerList.setMaximumSize(new Dimension(200, 42));

		toolBar.add(cbPlayerList);
		toolBar.add(btnGoToAddPlayer);
		toolBar.add(btnRemovePlayer);
		toolBar.add(btnPlaceBet);
		toolBar.add(btnSpin);
		
		this.add(toolBar);
		this.add(menuBar);
		
		isPlayerNull();
		
	}
	
		private void isPlayerNull() {
		
			if(gameEngine.getAllPlayers().size() <= 0) {
				btnPlaceBet.setEnabled(false);
				btnRemovePlayer.setEnabled(false);
				addPlayerMenu.setEnabled(false);
				placeBetMenu.setEnabled(false);
				removePlayerMenu.setEnabled(false);
			}
			
			else {
				btnPlaceBet.setEnabled(true);
				btnRemovePlayer.setEnabled(true);
				addPlayerMenu.setEnabled(true);
				placeBetMenu.setEnabled(true);
				removePlayerMenu.setEnabled(true);
			}
			
		}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName().equals("newPlayerAdded")) {

	        newPlayer = (Player) evt.getNewValue();
	        cbPlayerList.addItem(newPlayer);
	        
	        isPlayerNull();
	     }
		if (evt.getPropertyName().equals("playerDeleted")) {

	        Player deletedPlayer = (Player) evt.getNewValue();
	           
	        cbPlayerList.removeItem(deletedPlayer);
	        isPlayerNull();
	     }

		if (evt.getPropertyName().equals("wheelIsSpinning")) {
			
			btnGoToAddPlayer.setEnabled(false);
			btnSpin.setEnabled(false);
			
			btnRemovePlayer.setEnabled(false);
			btnPlaceBet.setEnabled(false);
			cbPlayerList.setEnabled(false);
			menu.setEnabled(false);
	    }
		if (evt.getPropertyName().equals("gameResult")) {
			
			btnGoToAddPlayer.setEnabled(true);
			btnSpin.setEnabled(true);
			
			btnRemovePlayer.setEnabled(true);
			btnPlaceBet.setEnabled(true);
			cbPlayerList.setEnabled(true);
			menu.setEnabled(true);
	    }
		
	}
	
	

	
}
