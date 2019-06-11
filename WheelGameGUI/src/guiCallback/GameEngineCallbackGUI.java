package guiCallback;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import guiView.DialogMessage;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	private HashMap<String, Integer> oldPlayers = new HashMap<String, Integer>();
	private String next;
	private String gameresult;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public GameEngineCallbackGUI(GameEngine gameEngine) {
		super();
	}

	@Override
	public void nextSlot(Slot slot, GameEngine engine) {

		next =  "Next slot: " + slot.toString();
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				
				pcs.firePropertyChange("nextSlot", null, next);
				pcs.firePropertyChange("nextSlotNumber", null, slot.getPosition());
			}
		});
		
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		
		gameresult = winningSlot.toString();
		engine.calculateResult(winningSlot);
		
		for (Player player : engine.getAllPlayers()) {
			oldPlayers.put(player.getPlayerId(), player.getBet());
			player.resetBet();
		}
		
		new DialogMessage(gameresult);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				pcs.firePropertyChange("gameResult", null, gameresult);
				pcs.firePropertyChange("winningSlot", oldPlayers, winningSlot);
			}
		});

		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener newListener) {
        pcs.addPropertyChangeListener(newListener);
    }

}
