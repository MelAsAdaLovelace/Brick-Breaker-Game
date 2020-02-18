package Model.PowerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import Model.GameObject;
import Model.MovingObjects;
import Utils.Constants;


public class LaserGun extends MovingObjects{

	private boolean destroy;
	private int counter;
	Image img = Toolkit.getDefaultToolkit().createImage("beam.png");

	public LaserGun(int x, int y, int width, int height, double velocity){
		super(x, y, width, height, 1, -1, 0, 25, Color.RED);
		setDestroy(false);
		setCounter(0);
	}

	@Override
	public void collideWith(GameObject go){
		if (!isDestroy()){
			setX(getX());
			setY(0);
			setWidth(1);
			setHeight(Constants.GAMEBOARD_HEIGHT);
		}
	}

	@Override
	public Rectangle2D getObjectBoundary(){
		if (getCounter() > 0) // to ensure that bricks around target will be
			// harmed only once by 'destroy'
			return new Rectangle(0, 0, 0, 0);
		else
			return super.getObjectBoundary();
	}

	@Override
	public void move(){
		if (!isDestroy()){
			if (isMoving())	{
				setX_position(getX_position() + (getX_speed() * getDirectionX()));
				setX((int) (getX_position()));
				setY_position((getY_position() + (getY_speed() * getDirectionY())));
				setY((int)getY_position());

			}
		}
		else{
			if (getCounter() == 5)
				setLost(true);
			else
				setCounter(getCounter() + 1);
		}

	}

	@Override
	public void draw(Graphics g){
		g.drawImage(img, getX(), getY(),getWidth(), getHeight(), null);
//		g.setColor(getColor());
//		g.fillRect(getX(), getY(), getWidth(), getHeight());


	}

	public boolean isDestroy()
	{
		return destroy;
	}

	public void setDestroy(boolean destroy)
	{
		this.destroy = destroy;
	}

	public int getCounter(){
		return counter;
	}

	public void setCounter(int counter){
		this.counter = counter;
	}


}
