
package Model;

import java.awt.Color;


public abstract class MovingObjects extends GameObject{
	private double speed;
	private double speedConstant;
	private double x_position;
	private double y_position;
	private double x_speed;
	private double y_speed;
	
	private int dirX;
	private int dirY;
	
	private boolean isMoving;
	private boolean isLost;
	
	
	public MovingObjects(int x, int y, int width, int height, int dirX, int dirY, double speedConstant,
			double velocity, Color color){
		super(x, y, width, height, color);
		this.dirX = dirX;
		this.dirY = dirY;
		this.speed = velocity;
		calculateSpeed(speedConstant);
		this.x_position = x;
		this.y_position = y;
		this.isMoving = false;
		this.setLost(false);

	}
	
	public void changeDirX(GameObject obj){
		if (isCollisionFromLeft(obj))
			setDirectionX(-1);
		else if (isCollisionFromRight(obj))
			setDirectionX(1);
	}

	public void changeDirY(GameObject obj){
		if (isCollisionFromTop(obj))
			setDirectionY(-1);
		else if (isCollisionFromBottom(obj))
			setDirectionY(1);
	}

	public void changeDirY(){
		setDirectionY(getDirectionY() * (-1));

	}

	public void changeDirX(){
		setDirectionY(getDirectionX() * (-1));

	}

	public abstract void collideWith(GameObject obj);

	public boolean isCollision(GameObject obj){
		return getObjectBoundary().intersects(obj.getObjectBoundary());
	}

	public boolean isCollisionFromTop(GameObject obj){
		return (getObjectBoundary().intersects(obj.getObjectTop()));
	}


	public boolean isCollisionFromBottom(GameObject obj){
		return (getObjectBoundary().intersects(obj.getObjectBottom()));
	}

	public boolean isCollisionFromRight(GameObject obj){
		return (getObjectBoundary().intersects(obj.getObjectRight()));
	}


	public boolean isCollisionFromLeft(GameObject obj){
		return (getObjectBoundary().intersects(obj.getObjectLeft()));
	}

	@Override
	public void setCoordinates(int x, int y){
		super.setCoordinates(x, y);
		this.x_position = x;
		this.y_position = y;
	}
	public void move(){	
		if (isMoving){
			setX_position(getX_position() + (getX_speed() * getDirectionX()));
			setX((int)getX_position());
			setY_position((getY_position() + (getY_speed() * getDirectionY())));
			setY((int)getY_position());
		}
	}

	public void calculateSpeed(double speedConstant){
		setSpeedConstant(Math.abs(speedConstant));
		setX_speed(Math.sqrt(getSpeedConstant()) * getSpeed());
		setY_speed(Math.sqrt(1 - getSpeedConstant()) * getSpeed());	
	}

	public double getSpeed(){
		return speed;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public double getSpeedConstant(){
		return speedConstant;
	}

	public void setSpeedConstant(double speedConstant){
		this.speedConstant = speedConstant;
	}

	public double getX_position(){
		return x_position;
	}

	public void setX_position(double x_position){
		this.x_position = x_position;
	}

	public double getY_position(){
		return y_position;
	}


	public void setY_position(double x_position){
		this.y_position = x_position;
	}

	public double getX_speed(){
		return x_speed;
	}

	public void setX_speed(double x_speed){
		this.x_speed = x_speed;
	}

	public double getY_speed(){
		return y_speed;
	}

	public void setY_speed(double y_speed){
		this.y_speed = y_speed;
	}

	public int getDirectionX(){
		return dirX;
	}


	public void setDirectionX(int directionX){
		this.dirX = directionX;
	}

	public int getDirectionY(){
		return dirY;
	}

	public void setDirectionY(int directionY)
	{
		this.dirY = directionY;
	}

	public boolean isMoving(){
		return isMoving;
	}

	
	public void setMoving(boolean isMoving){
		this.isMoving = isMoving;
	}

	public boolean isLost(){
		return isLost;
	}


	public void setLost(boolean isLost){
		this.isLost = isLost;
	}

	public boolean toDestroy(){
		return isLost();
	}

	
}
