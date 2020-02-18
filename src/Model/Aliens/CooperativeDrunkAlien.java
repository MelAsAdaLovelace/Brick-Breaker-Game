package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Model.Ball;
import Model.GameObject;
import Model.Wall;

public class CooperativeDrunkAlien extends DrunkAlien {
	private  int count;
	public CooperativeDrunkAlien(int x, int y) {
		super(x, y);
		setMoving(true);
		setCount(1);
	}



	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}
	@Override
	public boolean hitted(GameObject go) {
		if(go instanceof Ball)
			setHealth(getHealth() - 1);
		if (getHealth() == 0)
			return true;
		else{
			return false;
		}
	}

	@Override
	public void collideWith(GameObject go) {
		if(go instanceof Wall) {
			this.changeDirX(go);
		}
		else this.hitted(go);// TODO Auto-generated method stub
		
	}

	public  int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	
	public  void setCount(int count) {
		this.count = count;
	}


	

}
