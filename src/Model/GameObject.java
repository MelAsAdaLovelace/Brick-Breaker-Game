
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import Utils.Constants;

public abstract class GameObject implements Serializable, PropertyChangeListener{

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;

	public GameObject(int x, int y, int width, int height, Color color){
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setColor(color);

	}
	public void setCoordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Rectangle2D getObjectTop(){
		return new Rectangle(x, y, width, 1);
	}

	public Rectangle2D getObjectBottom(){

		return new Rectangle(x, y + height + 1, width, 1);
	}

	public Rectangle2D getObjectBoundary(){
		return new Rectangle(x, y, width, height);
	}

	public Rectangle2D getObjectLeft(){
		return new Rectangle(x, y, 1, height);
	}


	public Rectangle2D getObjectRight(){
		return new Rectangle(x + width - 1, y, 1, height);
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}


	public int getY(){
		return y;
	}


	public void setY(int y){
		this.y = y;
	}



	public int getWidth(){
		return width;
	}


	public void setWidth(int width){
		this.width = width;
	}

	public int getHeight(){
		return height;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public Color getColor(){
		return color;
	}


	public void setColor(Color color){
		this.color = color;
	}

	public abstract void draw(Graphics g);

	@Override
	public void propertyChange(PropertyChangeEvent evt){
	}


}
