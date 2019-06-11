package guiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import guiView.AddPlayerFrame;
import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

public class GoToAddPlayerController implements ActionListener{
	
	private GameEngine gameEngine;
	private Viewmodel viewmodel;
	
	public GoToAddPlayerController(GameEngine gameEngine, Viewmodel viewmodel) {
		this.gameEngine = gameEngine;
		this.viewmodel = viewmodel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("in2");
			
		AddPlayerFrame addPlayerFrame = new AddPlayerFrame(gameEngine, viewmodel);
		addPlayerFrame.setVisible(true);
		
	}

}
