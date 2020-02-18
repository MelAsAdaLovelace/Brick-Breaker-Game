package Model.Aliens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Model.Ball;
import Model.GameObject;
import Model.Wall;
import Model.Bricks.Brick;
import Model.Bricks.SimpleBrick;
import Utils.Constants;

public class RepairingAlien extends Alien {
	private boolean isDestroy;
	private static int count;


	public RepairingAlien(int x, int y) {
		super(x, y, Color.BLACK);
		setMoving(true);
		setCount(1);
		setHealth(1);
		setHeight(2*Constants.ALIEN_HEIGHT);
	}	


	@Override
	public void draw(Graphics g) {

		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void collideWith(GameObject obj) {
		if(obj instanceof Wall || obj instanceof Brick) {
			this.changeDirX(obj);
		}else 
			this.hitted(obj);
	}


	public boolean hitted(GameObject obj) {
		if(obj instanceof Ball ) {
			setHealth(getHealth() - 1); 
			return getHealth() == 0;
		}
		return false;
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
		RepairingAlien.count = count;
	}
}
