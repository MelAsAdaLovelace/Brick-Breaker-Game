package Model.Bricks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Model.GameObject;
import Model.PowerUps.FallingPowerUp;
import Utils.Constants;

public class SimpleBrick extends Brick{
	private int health;
	public SimpleBrick(int x, int y){
		super(x, y,  true, false, Constants.SIMPLE_BRICK_COLOR);
		setHealth(1);
	}

	@Override
	public boolean hitted(GameObject go){
		if (go instanceof FallingPowerUp)
			return false;
		setHealth(getHealth() - 1);
		if (getHealth() == 0)
			return true;
		else{
			return false;
		}
	}
	

	public int getHealth(){
		return health;
	}

	public void setHealth(int health){
		this.health = health;
	}


	@Override
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight());

	}
}
