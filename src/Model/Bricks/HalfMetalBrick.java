package Model.Bricks;

import java.awt.Color;
import java.awt.Graphics;

import Model.GameObject;
import Model.PowerUps.FallingPowerUp;
import Utils.Constants;

public class HalfMetalBrick extends Brick {
	private int health;
	public HalfMetalBrick(int x, int y) {
		super(x, y,  true, false, Constants.METAL_COLOR);
		this.health = 1;
	}

	@Override
	public boolean hitted(GameObject go){
		setHealth(getHealth() - 1);
		if (getHealth() == 0)
			return true;
		else{
			return false;
		}
	}
	

	@Override
	public void draw(Graphics g) {	
		g.setColor(this.getColor());
		g.fillRect(getX(), getY() + getHeight()/2, getWidth(), getHeight()/2);
		g.setColor(Constants.SIMPLE_BRICK_COLOR);
		g.fillRect(getX(), getY(), getWidth(), getHeight()/2);
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight()/2);
		g.drawRect(getX(), getY() + getHeight()/2, getWidth(), getHeight()/2);
	

	}	

	public int getHealth(){
		return health;
	}

	public void setHealth(int health){
		this.health = health;
	}


}
