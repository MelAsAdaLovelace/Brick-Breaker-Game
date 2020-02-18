package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Model.Bricks.Brick;
import Utils.Constants;

public class Wall extends Brick{


	public Wall(int x, int y, int width, int height){
		super(x, y, false, false, Color.RED);
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public boolean hitted(GameObject go){
		return false;
	}

	@Override
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());

	}

}
