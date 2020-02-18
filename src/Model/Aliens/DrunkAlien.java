package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Model.GameObject;

public abstract class DrunkAlien extends Alien {
	private   int count;
	private ArrayList<DrunkAlien> da;

	public DrunkAlien(int x, int y) {
		super(x, y, Color.GREEN.brighter());
		setHealth(1);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 5, 5);
	
	}

	@Override
	public
	abstract boolean hitted(GameObject go);
	
	public void add(DrunkAlien da) {
		getDrunkAliens().add(da);
	}

	@Override
	public abstract void collideWith(GameObject obj);


	public ArrayList<DrunkAlien> getDrunkAliens() {
		return da;
	}

	public void setDrunkAliens(ArrayList<DrunkAlien> da) {
		this.da = da;
	}

	public void setCount(int i) {
		this.count = i;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	
	

}
