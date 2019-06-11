package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import model.interfaces.Player;
import model.interfaces.Slot;

public class Viewmodel implements PropertyChangeListener {

	private Player newPlayer;
	private Player deletedPlayer;
	private String next;
	private String result;
	private Slot winningSlot;
	private int nextSlotNumber;
	private HashMap<String, Integer> oldPlayers = new HashMap<String, Integer>();


	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Viewmodel() {
	}

	public void addPropertyChangeListener(PropertyChangeListener newListener) {
		pcs.addPropertyChangeListener(newListener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("newPlayerAdded")) {
			newPlayer = (Player) evt.getNewValue();
			pcs.firePropertyChange("newPlayerAdded", null, newPlayer);
		}

		if (evt.getPropertyName().equals("playerPlaceBet")) {
			pcs.firePropertyChange("playerPlaceBet", null, null);
		}
		if (evt.getPropertyName().equals("playerDeleted")) {
			deletedPlayer = (Player) evt.getNewValue();
			pcs.firePropertyChange("playerDeleted", null, deletedPlayer);
		}
		if (evt.getPropertyName().equals("nextSlot")) {
			next = (String) evt.getNewValue();
			pcs.firePropertyChange("nextSlot", null, next);
		}
		if (evt.getPropertyName().equals("gameResult")) {
			result = (String) evt.getNewValue();
			pcs.firePropertyChange("gameResult", null, result);
		}
		if (evt.getPropertyName().equals("wheelIsSpinning")) {
			pcs.firePropertyChange("wheelIsSpinning", null, null);
		}
		if (evt.getPropertyName().equals("nextSlotNumber")) {
			nextSlotNumber = (int) evt.getNewValue();
			pcs.firePropertyChange("nextSlotNumber", null, nextSlotNumber);
		}
		if (evt.getPropertyName().equals("winningSlot")) {
			winningSlot = (Slot) evt.getNewValue();
			oldPlayers = (HashMap<String, Integer>) evt.getOldValue();
			pcs.firePropertyChange("winningSlot", oldPlayers, winningSlot);
		}

	}

}
