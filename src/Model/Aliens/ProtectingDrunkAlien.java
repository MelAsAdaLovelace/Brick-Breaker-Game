package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Model.Ball;
import Model.GameObject;
import Model.Wall;
import Model.Bricks.Brick;

public class ProtectingDrunkAlien extends DrunkAlien {
	private  int count;

	public ProtectingDrunkAlien(int x, int y) {
		
		super(x, y);
		setMoving(true);
		setCount(0);
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public boolean hitted(GameObject go) {
		if(go instanceof Ball) {
			if(go.getY() + go.getHeight() <= this.getY()) {
				setHealth(getHealth() - 1);
			}
		}
		
		if (getHealth() == 0)
			return true;
		else{
			return false;
		}
	}

	@Override
	public void collideWith(GameObject obj) {
		if(obj instanceof Wall || obj instanceof Brick) {
			this.changeDirX(obj);
		}else 
			this.hitted(obj);

	}

	public  int getCount() {
		return count;
	}

	public  void setCount(int count) {
		this.count = count;
	}
	
	

}
