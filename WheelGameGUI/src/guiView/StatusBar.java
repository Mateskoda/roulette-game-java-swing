package guiView;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements PropertyChangeListener{
	
	private JLabel statusLabel;
	private String next;
	private Border border;
	
	public StatusBar(GameEngine gameEngine, Viewmodel viewmodel) {
		
		border = BorderFactory.createLineBorder(Color.BLACK	, 1);
		
		setBorder(border);
		
		statusLabel = new JLabel("game status", JLabel.CENTER);
		add(statusLabel);
	}
	
	private void setStatus(String status)
	{
		statusLabel.setText(status);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName().equals("nextSlot")) {
			next = (String) evt.getNewValue();
			setStatus(next);
	    }
		
		if (evt.getPropertyName().equals("gameResult")) {

			System.out.println("viewmodel propertychange gameResult");
			next = (String) evt.getNewValue();
			setStatus("FINAL POSITION: " + next);
		}
		
	}

}
