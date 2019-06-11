package guiView;

import java.awt.BorderLayout;


import javax.swing.JFrame;

import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class AddPlayerFrame extends JFrame{
	
	
	public AddPlayerFrame(GameEngine gameEngine, Viewmodel viewmodel) {
		
		
		super("add player");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(500,200);
		
		setLayout(new BorderLayout());
		
		AddPlayer addPlayer = new AddPlayer(gameEngine, viewmodel, this);
		
		add(addPlayer, BorderLayout.CENTER);
		
		
	}




	


}
