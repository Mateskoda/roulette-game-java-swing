package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot {
	
	private Color color;
	private int number;
	private int position;
	

	public SlotImpl(int position, Color color, int number) {
		this.position = position;
		this.color = color;
		this.number = number;
	}


	@Override
	public int getPosition() {
		
		return position;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Color getColor() {
		return color;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		result = prime * result + position;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this.equals((Slot)obj) && obj instanceof Slot)
			return true;
		else
			return false;
	}


	@Override
	public boolean equals(Slot slot) {
		if(this.color == slot.getColor() && this.number == slot.getNumber())
			return true;
		else
			return false;
	}


	@Override
	public String toString() {
		return "Position: " + position + ", Color: " + color + ", Number: " + number ;
	}
	
	
	
}
