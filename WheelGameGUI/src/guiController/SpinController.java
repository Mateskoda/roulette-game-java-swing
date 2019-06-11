package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


import guiView.DialogMessage;
import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

public class SpinController implements ActionListener {
	
	private GameEngine gameEngine;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	boolean isAllBetPlaced;

	public SpinController(GameEngine gameEngine, Viewmodel viewmodel) {
		super();
		this.gameEngine = gameEngine;
		addPropertyChangeListener(viewmodel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(gameEngine.getAllPlayers().size() <= 0)
			new DialogMessage("please add player to spin");
		else
			spinOrNot();
		
	}
	
	private void spinOrNot() {

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

	
	public void addPropertyChangeListener(PropertyChangeListener newListener) {
        pcs.addPropertyChangeListener(newListener);
  }

}
