package Model.Bricks;

import java.awt.Color;
import java.awt.Graphics;

import Model.GameObject;
import Model.PowerUps.FallingPowerUp;
import Utils.Constants;

public class WrapperBrick extends Brick {
	int health;
	


	public WrapperBrick(int x, int y) {
		super(x, y, true, true, Constants.WRAPPER_BRICK_COLOR);
		// TODO Auto-generated constructor stub
		setHealth(1);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public boolean hitted(GameObject go){
		if (go instanceof FallingPowerUp)
			return false;
		setHealth(getHealth() - 1);
		if (getHealth() == 0)
			return true;
		else
		{
			return false;
		}

	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
