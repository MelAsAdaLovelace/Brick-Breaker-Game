package Model.PowerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Model.GameObject;
import Model.MovingObjects;
import Model.Paddle;
import Model.Aliens.Alien;
import Utils.Constants;

public class FallingPowerUp extends MovingObjects{
	/**
	 * OVERVIEW: This class is a public powerup class that creates a String PowerUp and provides methods for different types of powerups which extend this class.  
	 */
	private String type;
	private PowerUp powerUp;
	private Point position = new Point();
	private boolean isMagneticCollected = false;
	public FallingPowerUp(int x, int y, int width, int height, int dirX, int dirY,
			double ratio, PowerUp powerUp, Color color){
		super( x, y, width, height, dirX, dirY, ratio, Constants.POWERUP_SPEED, color);
		position.x = x;
		position.y = y;
		this.type = powerUp.getType();
		setPowerUp(powerUp);
	}
	
	/** 
	 * this method is in the fallingPowerUp class it checks whether powerup has collided with paddle or not
	 * @param  Any gameObject in this case paddle
	 * 
	 */
	@Override
	public void collideWith(GameObject go){
		// REQUIRES: GameObject
		// MODIFIES:FallingPowerUp value
		// EFFECTS: PowerUp is setLost()
		if (go instanceof Paddle) {
			setLost(true);
			if(type == "Magnet") {
				isMagneticCollected = true;
			}
			isMagneticCollected = false;
		}

	
	}
	
	/**
	 * @return whether toDestroy the PowerUp
	 */
	@Override
	public boolean toDestroy(){
		if (super.toDestroy())
			return true;
		return (getY() > Constants.GAMEBOARD_HEIGHT);
	}

	@Override
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * This method  returns type of the powerup.
	 *
	 */
	public String getType(){
		return type;
	}
		
	/**
	 * This sets type of the powerup.
	 * @param String Type to set the powerup
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * 
	 * @return powerup
	 */
	public PowerUp getPowerUp(){
		return powerUp;
	}
	
	/**
	 * 
	 * @param powerUp set powerUp
	 * 
	 */
	public void setPowerUp(PowerUp powerUp){
		this.powerUp = powerUp;
	}
	
	public boolean repOK() {
		return !this.type.equals(null);
	}

	/**
	 * @return the isMagneticCollected
	 */
	public boolean isMagneticCollected() {
		return isMagneticCollected;
	}

	/**
	 * @param isMagneticCollected the isMagneticCollected to set
	 */
	public void setMagneticCollected(boolean isMagneticCollected) {
		this.isMagneticCollected = isMagneticCollected;
	}


}
