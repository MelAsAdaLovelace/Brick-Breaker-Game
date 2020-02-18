package Model.Bricks;

import java.awt.Color;
import java.awt.Graphics;

import Model.GameObject;
import Model.MovingObjects;
import Model.PowerUps.FallingPowerUp;
import Utils.Constants;

public class MiningBrick extends Brick{
	private final static double radius = Constants.MINING_BRICK_RADIUS;
	private final static double velocity = Constants.MINING_BRICK_VELOCITY;
	double angularX;
	double angularY;
	int health = 2;
	
	public MiningBrick(int x, int y) {
		super(x, y, false, false, Constants.MINING_BRICK_COLOR);
		this.setWidth((int) (2 * radius));
		this.setHeight((int) (2 * radius));
		angularX = x;
		angularY = y;
	}
	
	public void move() {
		 double radian = velocity * 1000;
		 this.setX( (int) (angularX + radius * Math.cos(radian)));
		 this.setY((int) (angularY + radius * Math.sin(radian)));
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public boolean hitted(GameObject go){
		if (go instanceof FallingPowerUp)
			return false;
		setHealth(getHealth() - 1);
		if (getHealth() == 0) {
			return true;
		}

		else{
			return false;
		}
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}


}
