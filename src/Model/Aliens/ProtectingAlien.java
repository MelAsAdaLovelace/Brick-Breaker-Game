/**
 * 
 */
package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Model.Ball;
import Model.GameObject;
import Model.Wall;
import Model.Bricks.Brick;
import Utils.Constants;

/**
 * @author melike.kavcioglu
 *
 */
public class ProtectingAlien extends Alien {
	private static int count;

	/**
	 * @param x
	 * @param y
	 * @param color
	 */
	public ProtectingAlien(int x, int y) {
		super(x, y, Color.GREEN.darker());
		setHealth(1);
		setWidth(2 * Constants.PADDLE_WIDTH);
		setHeight(3*Constants.ALIEN_HEIGHT);
		setX_speed(5);
		setHealth(1);
		setMoving(true);
		setCount(0);
	
	}
	

	@Override
	public void draw(Graphics g) {


		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	

	}

	@Override
	public boolean hitted(GameObject go){
		if(go instanceof Ball) {
			if(go.getY() + go.getHeight() - 10 <= this.getY()) {
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

	/**
	 * @return the count
	 */
	public static int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public static void setCount(int count) {
		ProtectingAlien.count = count;
	}
	
	

}
