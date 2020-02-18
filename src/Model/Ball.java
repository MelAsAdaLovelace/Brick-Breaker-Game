package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;

import Model.Aliens.Alien;
import Model.Bricks.MiningBrick;
import Model.PowerUps.MagneticPaddle;
import Utils.Constants;

public class Ball extends MovingObjects{

	/**
	 * OVERVIEW: This class is the Ball class that extends MovingObjects
	 * and provides methods for the Ball class.
	 */

	private final static int radius =  Constants.BALL_RADIUS;
	private boolean chemical;
	private static boolean isShootable;
	Image img = Toolkit.getDefaultToolkit().createImage("ball.gif");
	Image chem = Toolkit.getDefaultToolkit().createImage("comet.png");


	/**
	 * This constructs a ball with a specified x, y, directionX, directionY, ratio and velocity
	 * @param x the x coordinate of the ball location
	 * @param y the y coordinate of the ball location
	 * @param directionX the x coordinate of the ball direction
	 * @param directionY the y coordinate of the ball direction
	 * @param ratio the speed constant of the ball
	 * @param velocity the velocity of the ball
	 */
	public Ball(int x, int y,  int directionX, int directionY, double ratio, double velocity){
		super(x, y, radius * 2, radius * 2, directionX, directionY, ratio, velocity, Constants.BALL_COLOR);
		this.setChemical(false);
		this.setShootable(true);
	}

	/**
	 * This returns a Rectangle2D corresponds to boundaries of the ball
	 * @return boundaries of the ball
	 */
	@Override
	public Rectangle2D getObjectBoundary(){
		return new Rectangle((int) (getX() + getX_speed() * getDirectionX()),
				(int) (getY() + getY_speed() * getDirectionY()), getWidth(), getHeight());
	}

	/**
	 * This adjusts the direction and the speed of the ball after it collide with the paddle
	 * @param paddle Paddle to check collision between ball and paddle
	 */
	public void bounce(Paddle paddle){
		// REQUIRES: Paddle as a parameter.
		// MODIFIES: directionX, ratio and velocity.
		// EFECTS: If ball's x position is greater than paddle's x position set directionX to 1
		//else set directionX to -1
		Rectangle2D paddleBoundary = paddle.getObjectBoundary();
		if(paddle.getIsRotatedToLeft()) {
			setDirectionX(-1);
			setDirectionY(-1);
		}	
		else if(paddle.getIsRotatedToRight()) {
			setDirectionX(1);
			setDirectionY(-1);
		}
		else {
			double i = getX() + (getWidth()) / 2;
			double j = paddleBoundary.getX() + paddleBoundary.getWidth() / 2;
			double diff = i - j;
			double ratio = diff / (paddleBoundary.getWidth() / 2 + 2 * getRadius());
			if (ratio >= 0)
				setDirectionX(1);
			else
				setDirectionX(-1);
			calculateSpeed(ratio);
		}
	}

	/**
	 * This adjusts the direction and the speed of the ball after it collide with an alien
	 * @param alien Alien to check collision between ball and alien
	 */	
	public void bounce(Alien alien){
		// REQUIRES: Alien as a parameter.
		// MODIFIES: directionX, ratio and velocity.
		// EFFECTS: If ball's x position is greater than aliens's x position set directionX to 1
		//	else set directionX to -1
		Rectangle2D alienBoundary = alien.getObjectBoundary();
		double i = getX() + (getWidth()) / 2;
		double j = alienBoundary.getX() + alienBoundary.getWidth() / 2;
		double diff = i - j;
		double ratio = diff / (alienBoundary.getWidth() / 2 + 2 * getRadius());
		if (ratio >= 0)
			setDirectionX(1);
		else
			setDirectionX(-1);
		calculateSpeed(ratio);
	}

	/**
	 * This 
	 * @param obj
	 */
	public void collideWith(GameObject obj){
		// REQUIRES: A GameOBject as a parameter.
		// MODIFIES: directionX, directionY, ratio and velocity
		// EFFECTS: If ball is chemical and collision occurs with a game object other than
		// wall or paddle nothing happens
		//If ball is not chemical and collision occurs with a game object from top, left,
		// right or bottom and moreAtSide is TRUE multiply direction x with -1
		// f ball is not chemical and collision occurs with a game object from top, left,
		// right or bottom and moreAtSide is FALSE apply bounce method according to type of the object

		if(isChemical()) {
			if (!(obj instanceof Wall || obj.getClass() == Paddle.class))
				return;
		}
		if(obj instanceof MiningBrick) {
			obj.setWidth(2*getWidth());
		}
		if ((isCollisionFromRight(obj) || isCollisionFromLeft(obj))){
			if (isCollisionFromTop(obj) || isCollisionFromBottom(obj)) {

				if(moreAtSide(obj)) {
					changeDirX(obj);
				}else {
					if (obj.getClass() == Paddle.class)
						bounce((Paddle) (obj));
					if(obj.getClass() == Alien.class)
						bounce((Alien) (obj)); 
					changeDirY(obj);
				}
			}else{
				changeDirX(obj);		
			}
		}else{
			if (obj instanceof Paddle) 
				bounce((Paddle) (obj));
			if(obj instanceof MagneticPaddle) {
				setDirectionX(0);
				setDirectionY(-1);
				setY(Constants.PADDLE_Y - 2*Constants.BALL_RADIUS);
				setMoving(false);
			}		
			if(obj instanceof Alien)
				bounce((Alien) (obj));
			changeDirY(obj);
		}
	}

	/**
	 * This 
	 * @param go a GameObject to check how collision occurs
	 * @return 
	 */
	public boolean moreAtSide(GameObject go){
		// REQUIRES: A GameObject as a parameter.
		// EFFECTS: 
		Point brick;
		Point ballCenterPoint = new Point(getX() + getRadius(), getY() + getRadius());
		if (isCollisionFromLeft(go))
			brick =
			new Point((go.getX() + (go.getHeight() / 2)), (go.getY() + (go.getHeight() / 2)));
		else
			brick = new Point((go.getX() + go.getWidth() - (go.getHeight() / 2)),
					(go.getY() + (go.getHeight() / 2)));
		if (Math.abs(ballCenterPoint.x - brick.x) < Math
				.abs(ballCenterPoint.y - brick.y))
			return false;
		else
			return true;
	}

	/**
	 * This returns a boolean whether the ball is out of the boundaries of the game board or not
	 * @return ball is not in the boundaries of the game board
	 */
	@Override
	public boolean toDestroy(){
		return (getY() >= Constants.GAMEBOARD_HEIGHT - Constants.PADDLE_HEIGHT);
	}

	@Override
	public void draw(Graphics g){
		if(isChemical()) {
			g.drawImage(chem, getX(), getY(), getWidth(), getHeight(), null);
		}else {
			g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
		}
//		g.setColor(getColor());
//		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * This adds the ball new features according to acquired power up
	 * @param evt PropertyChangeEvent to change the features of the ball
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt)	{
		// REQUIRES: A PropertyChangeEvent as a parameter.
		// MODIFIES: isLost, chemical and color
		// EFFECTS: If name of evt is GangOfBalls and boolean value of evt is false set isLost TRUE.
		// 	If name of evt is ChemicalBall and boolean value of evt is true set chemical TRUE and color to Color.YELLOW
		//	else set chemical FALSE and set color BALL_COLOR.
		if (evt.getPropertyName() == "GangOfBalls"){
			if ((Boolean) evt.getNewValue() == false)
				setLost(true);
		}
		if (evt.getPropertyName() == "ChemicalBall"){
			if ((Boolean) evt.getNewValue() == true){
				setChemical(true);
				setColor(Color.YELLOW);
			}
			else{
				setChemical(false);
				setColor(Constants.BALL_COLOR);
			}
		}
	}

	public int getRadius(){
		return radius;
	}


	public boolean isChemical(){
		return chemical;
	}

	public void setChemical(boolean chemical){
		this.chemical = chemical;
	}


	/**
	 * This returns a boolean whether representation invariants
	 * checks the x and y values of the locations are positive
	 * @return boolean representation invariant check
	 */
	public boolean repOk() {
		// EFFECTS: Returns TRUE if the representation invariants hold for this
		// otherwise returns false
		if(this.getX() < 0 || this.getY() < 0) {
			return false;
		}
		return true;
	}
	/**
	 * @return the isShootable
	 */
	public static boolean isShootable() {
		return isShootable;
	}

	/**
	 * @param isShootable the isShootable to set
	 */
	public static void setShootable(boolean isShootable) {
		isShootable = isShootable;
	}


}
