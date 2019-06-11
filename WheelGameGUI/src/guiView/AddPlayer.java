package guiView;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import guiController.AddPlayerController;
import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class AddPlayer extends JPanel {



	private JLabel lblName;
	private JLabel lblBet;

	private JTextField tfPlayerName = new JTextField(20);
	private JTextField tfPoints = new JTextField(3);
	private JButton btnAdd = new JButton();


	public AddPlayer(GameEngine gameEngine, Viewmodel viewmodel, AddPlayerFrame addPlayerFrame) {

		setLayout(new GridLayout(0, 2));

		btnAdd.setText("Add Player");
		lblName = new JLabel("Name: ");
		lblBet = new JLabel("Initial Points: ");

		add(lblName);
		add(tfPlayerName);
		add(lblBet);
		add(tfPoints);
		add(btnAdd);


		AddPlayerController addPlayerController = new AddPlayerController(this, gameEngine, viewmodel, addPlayerFrame, tfPlayerName , tfPoints);
		btnAdd.addActionListener(addPlayerController);

	}

}
