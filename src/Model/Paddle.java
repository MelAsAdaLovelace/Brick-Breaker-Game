package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Utils.Constants;


public class Paddle extends MovingObjects implements PropertyChangeListener{
	//OVERVIEW: Paddle object for game
	private int speed;
	private int paddleWidth;
	private static final int paddleHeight =  Constants.PADDLE_HEIGHT;
	private int degree;
	private boolean isRotatedToLeft;
	private boolean isRotatedToRight;

	/**
	 * Consturctor for paddle
	 * @param x
	 * @param y
	 * @param x_speed
	 */
	public Paddle(int x, int y, int x_speed){
		super(x, y, Constants.PADDLE_WIDTH, paddleHeight, x_speed, x_speed, Constants.PADDLE_OFFSET, Constants.PADDLE_OFFSET, Constants.PADDLE_COLOR);
		this.speed = x_speed;
		paddleWidth = Constants.PADDLE_WIDTH;
		setColor(Constants.PADDLE_COLOR);
		setIsRotatedToLeft(false);
		setIsRotatedToRight(false);
	}

	/**
	 * 
	 * @param x
	 * Paddle moves on x direction, this function checks the boundaries
	 */
	public void move(int x){
		setX((int) (getX() + x * getSpeed()));
		if (getX() > Constants.GAMEBOARD_WIDTH - Constants.WALL_WIDTH - getWidth())
			setX(Constants.GAMEBOARD_WIDTH - Constants.WALL_WIDTH - getWidth());
		if (getX() < Constants.WALL_WIDTH)
			setX(Constants.WALL_WIDTH);

	}

	/**
	 * When the Double Paddle PowerUp is collected, paddle's width doubles
	 */

	public void doublePaddle(){
		// MODIFIES: Width of the paddle doubles
		// EFECTS: If the paddle is on the edges, it reset its position.
		
		if (getX() > Constants.GAMEBOARD_WIDTH - Constants.WALL_WIDTH - 2*getWidth()){
			setX(Constants.GAMEBOARD_WIDTH - Constants.WALL_WIDTH - 2*getWidth());
			setWidth((int) (getWidth() *2));
		}
		else {
			setX(getX());
			setWidth((int) (getWidth() *2 ));
		}

		if (getX() < Constants.WALL_WIDTH){
			setX(Constants.WALL_WIDTH);
			setWidth((int) (getWidth() *2));
		}
	}

	/**
	 * @param evt
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if (evt.getPropertyName() == "DoublePaddle")
		{
			if ((Boolean) evt.getNewValue() == true)
			{
				setPaddleWidth(Constants.L *2);
			}else{
				setPaddleWidth(Constants.L);
			}
		}
//		if (evt.getPropertyName() == "Magnet")
//		{
//			if ((Boolean) evt.getNewValue() == true)
//			{
//				setColor(Color.GRAY);
//			}else{
//				setColor(Constants.PADDLE_COLOR);
//			}
//		}
	}
	

	public Rectangle2D getObjectBoundary() {
		//EFFECTS: if the paddle is rotated, it changes its shape.
		if(isRotatedToLeft == false && isRotatedToRight == false) {
			return super.getObjectBoundary();
		}
		else {
			Rectangle2D paddleBounds = new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
			AffineTransform at1 = AffineTransform.getRotateInstance(Math.toRadians(315), getX()+getWidth()/2, getY()+getHeight()/2);
			AffineTransform at2 = AffineTransform.getRotateInstance(Math.toRadians(45), getX()+getWidth()/2, getY()+getHeight()/2);
			if(isRotatedToLeft == true) {
				return at1.createTransformedShape(paddleBounds).getBounds2D();
			}
			else {
				return at2.createTransformedShape(paddleBounds).getBounds2D();
			}
		}
	}

	/**
	 * 
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		if(isRotatedToLeft == false && isRotatedToRight == false) {
			g2d.setColor(getColor());
			g2d.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
		}
		else {
			if(isRotatedToLeft == true) {
				g2d.rotate(Math.toRadians(315), getX()+getWidth()/2 , getY()+getHeight()/2);
				g2d.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
				g2d.setColor(getColor());
				g2d.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
			}
			else {
				g2d.rotate(Math.toRadians(45), getX()+getWidth()/2 , getY()+getHeight()/2);
				g2d.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
				g2d.setColor(getColor());
				g2d.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
			}
		}

	}


	public int getPaddleWidth(){
		return paddleWidth;
	}

	public void setPaddleWidth(int paddleWidth){
		this.paddleWidth = paddleWidth;
	}

	public boolean getIsRotatedToLeft() {
		return isRotatedToLeft;
	}

	public void setIsRotatedToLeft(boolean isRotatedToLeft) {
		this.isRotatedToLeft = isRotatedToLeft;
	}

	public boolean getIsRotatedToRight() {
		return isRotatedToRight;
	}

	public void setIsRotatedToRight(boolean isRotatedToRight) {
		this.isRotatedToRight = isRotatedToRight;
	}	

	public boolean repOk() {
		return speed > 0 && paddleWidth > 0 && degree > 0 && this.getX() > 0 && this.getY() > 0 && this.getX() <= Constants.GAMEBOARD_WIDTH
				&& this.getY() <= Constants.GAMEBOARD_HEIGHT;
	}

	@Override
	public void collideWith(GameObject obj) {
		// TODO Auto-generated method stub
		
	}


}
