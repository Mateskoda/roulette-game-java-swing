package main;


import javax.swing.SwingUtilities;

import guiCallback.GameEngineCallbackGUI;
import guiView.WheelGameJFrame;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;
import viewmodel.Viewmodel;


public class Main {
	
	public static void main(String[] args) {
		
		final GameEngine gameEngine = new GameEngineImpl();
		
		GameEngineCallbackGUI gameEngineCallbackGUI  = new GameEngineCallbackGUI(gameEngine);
		gameEngine.addGameEngineCallback(gameEngineCallbackGUI );
		
		GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();
		gameEngine.addGameEngineCallback(gameEngineCallback);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				final Viewmodel viewmodel = new Viewmodel();
				gameEngineCallbackGUI.addPropertyChangeListener(viewmodel);
				new WheelGameJFrame(gameEngine, viewmodel);
			
			}
		});

		

	}

}
