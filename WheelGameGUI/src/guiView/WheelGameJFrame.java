package guiView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class WheelGameJFrame extends JFrame {
	
	ComponentListener listener;
	SummaryPanel summaryPanel;
	StatusBar statusBar;
	ToolBarMenu toolBarMenu;
	WheelPanel wheelPanel;
	GameEngine gameEngine;
	Viewmodel viewmodel;
	int width;
int height;
	
	public WheelGameJFrame(GameEngine gameEngine, Viewmodel viewmodel)  {
		
		super("wheelgame");
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(900,600);
		
		setLayout(new BorderLayout());
		
		this.viewmodel = viewmodel;
		this.gameEngine = gameEngine;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
		setMinimumSize(new Dimension(screenSize.width/2, screenSize.height/2));
		
		summaryPanel = new SummaryPanel(gameEngine, viewmodel, screenSize.width/2, screenSize.height/2);
		statusBar = new StatusBar(gameEngine,viewmodel);
		toolBarMenu = new ToolBarMenu(gameEngine, viewmodel);
		wheelPanel = new WheelPanel(gameEngine, viewmodel);

		viewmodel.addPropertyChangeListener(summaryPanel);
		viewmodel.addPropertyChangeListener(statusBar);
		viewmodel.addPropertyChangeListener(toolBarMenu);
		viewmodel.addPropertyChangeListener(wheelPanel);

		add(toolBarMenu, BorderLayout.NORTH);
		add(summaryPanel, BorderLayout.WEST);
		add(wheelPanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		pack();
		
	}
	

}
