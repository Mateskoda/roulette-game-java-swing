package guiView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import viewmodel.Viewmodel;

@SuppressWarnings("serial")
public class WheelPanel extends JPanel implements PropertyChangeListener {

	private Image img;
	private Image scaled;
	private Graphics2D g2;
	private int circleRadius;
	private int i = 0;
	private int x = 0;
	private int y = 0;
	private int wheels = 38;
	private int x2 = 0;
	private int y2 = 0;
	private int r;
	private int r2;
	private double theta;
	private int wheelWidth = 0;
	private int wheelHeight = 0;
	private int nextSlotNumber;
	private int halfPanelWidth;
	private int halfPanelHeight;

	public WheelPanel(GameEngine gameEngine, Viewmodel viewmodel) {
		loadImage();
		this.scaled = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
	}

	private void loadImage() {
		try {
			img = ImageIO.read(new File("images\\Basic_roulette_wheel_1024x1024.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Dimension getPreferredSize() {
		if (scaled == null)
			return new Dimension(0, 0);
		else
			return new Dimension(scaled.getWidth(this), scaled.getHeight(this));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Dimension size = getSize();
		if (size.width > size.height) {
			wheelWidth = size.height;
			wheelHeight = size.height;

			x = size.width / 2 - wheelWidth / 2;
			y = 0;
		} else {
			wheelWidth = size.width;
			wheelHeight = size.width;

			y = size.height / 2 - wheelHeight / 2;
			x = 0;
		}
		g.drawImage(scaled, x, y, wheelWidth, wheelHeight, null);

		this.g2 = (Graphics2D) g.create();

		circleRadius = wheelWidth / 40;

		wheels = 38;
		x2 = 0;
		y2 = 0;

		halfPanelWidth = getWidth() / 2;
		halfPanelHeight = getHeight() / 2;
		r = (int) Math.round(wheelWidth * 0.47 - 5);
		r2 = r / 20;

		g2.setColor(Color.CYAN);

		theta = 2 * Math.PI * i / wheels;
		x2 = (int) Math.round(halfPanelWidth + r * Math.cos(theta - 1.5708));
		y2 = (int) Math.round(halfPanelHeight + r * Math.sin(theta - 1.5708));
		g2.fillOval(x2 - r2, y2 - r2, circleRadius, circleRadius);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("nextSlotNumber")) {

			nextSlotNumber = (int) evt.getNewValue();
			i = Math.abs(nextSlotNumber + 1);
			repaint();

		}

	}

}
