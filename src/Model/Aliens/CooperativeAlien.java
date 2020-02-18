package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Model.Ball;
import Model.GameObject;
import Model.Wall;
import Utils.Constants;

public class CooperativeAlien extends Alien{
	private boolean isDestroy;
	private static int count;



	public CooperativeAlien(int x, int y) {
		super(x, y, Color.YELLOW);
		setMoving(true);
		setHealth(1);
		setCount(1);
		setWidth(100);
		setHeight(Constants.BRICK_HEIGHT);
	}

	
	@Override
	public void collideWith(GameObject go){
		if(go instanceof Wall) {
			this.changeDirX(go);
		}
		else this.hitted(go);
	}

	public boolean hitted(GameObject go){
		if(go instanceof Ball)
			setHealth(getHealth() - 1);
		if (getHealth() == 0)
			return true;
		else{
			return false;
		}
	}


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(Color.YELLOW);
		g.fillRect(getX(), getY(), getWidth(), getHeight());

	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		CooperativeAlien.count = count;
	}






}
