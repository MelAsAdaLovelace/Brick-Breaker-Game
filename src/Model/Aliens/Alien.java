package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Model.Ball;
import Model.GameObject;
import Model.MovingObjects;
import Utils.Constants;

public abstract class Alien extends MovingObjects {
	//OVERVIEW: Abstract Alien Type for all aliens type to be extended.
	
	private String name;
	private AlienType type;
	private int health;
	private int count;
	private Point position = new Point();

	
	/**
	 * @param x
	 * @param y
	 * @param color
	 */
	public Alien(int x, int y, Color color) {
		super(x, y, Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT, 1, 1, 1, 5, color);
		position.x  = x;
		position.y = y;
		setHealth(1);
	}
	
	/**
	 * move method to change the position of the Alien on the X direction.
	 */
	@Override
	public void move() {
		//MODIFIES: modifies the position of the alien on the x direction.
		if(isMoving()) {
			setX_position(getX_position() + (getX_speed() * getDirectionX()));
			setX((int) (getX_position()));
		}
	}
	
	@Override
	public abstract void draw(Graphics g);
	
	/**
	 * If the health is 0, then destroy the alien.
	 */
	@Override
	public boolean toDestroy(){
		if (super.toDestroy())
			return true;
		return (getHealth() == 0);
	}

	/**
	 * 
	 * @param obj
	 * @return true if the health is 0
	 */
	public boolean hitted(GameObject obj) {
		//REQUIRES: a game object
		if(obj instanceof Ball)
			setHealth(getHealth() - 1);
		if (getHealth() == 0)
			return true;
		else{
			return false;
		}
	}
	/**
	 * 
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * 
	 * @return alien type
	 */
	public AlienType getType() {
		return type;
	}
	
	/**
	 * 
	 * @param type
	 */
	public void setType(AlienType type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @return whether representation invariant is correct.
	 */
	public boolean repOk() {
		if(count  >=  0 && position.x >= 0 && position.y >= 0 & health >= 0)
			return true;
		else {
			return false;
		}	
	}

	@Override
	public String toString() {
		return "Alien [name=" + name + ", type=" + type + ", health=" + health + ", count=" + count + ", position="
				+ position + "]";
	}
	


}
