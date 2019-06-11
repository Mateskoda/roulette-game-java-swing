package guiView;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DialogMessage extends JOptionPane{
	
	public DialogMessage(String message) {
		showMessageDialog(null, message);
	}

}
