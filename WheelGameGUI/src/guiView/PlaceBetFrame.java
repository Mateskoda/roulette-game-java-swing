package guiView;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class PlaceBetFrame extends JFrame{
	
	
	public PlaceBetFrame(GameEngine gameEngine, Viewmodel viewmodel, Player player) {
		
		
		super("place bet");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(500,200);
		
		setLayout(new BorderLayout());
		
		PlaceBet placeBet = new PlaceBet(gameEngine, viewmodel, this, player);
		
		add(placeBet, BorderLayout.CENTER);
		
		System.out.println("in place bet frame");
		
		
	}




	


}
