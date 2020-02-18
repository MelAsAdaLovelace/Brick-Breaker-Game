package Model.Bricks;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import Model.GameObject;
import Utils.Constants;


public abstract class Brick extends GameObject{
	//OVERVIES: An abstract Brick type for all Bricks to be defined.
	
	boolean isMetal;
	boolean hasSurprise;

	/**
	 * This constructs a brick with a specified x, y, type, isMetal,
	 * hasSurprise and color.
	 * @param x the x coordinate of the brick location
	 * @param y the y coordinate of the brick location
	 * @param type the type of the brick	
	 * @param isMetal the boolean whether the brick is metal or not
	 * @param hasSurprise the boolean whether the brick has surprise or not
	 * @param color the color of the brick
	 */
	public Brick(int x, int y, boolean isMetal, boolean hasSurprise, Color color){
		super(x, y, Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT, color);
		this.isMetal = isMetal;
		this.hasSurprise = hasSurprise;
	}

	/**
	 * This returns the boolean whether this Brick has surprise or not.
	 * @return this brick has surprise or not
	 */
	public boolean isHasSurprise(){
		return hasSurprise;
	}


	/**
	 * This sets whether this Brick has surprise or not.
	 * @param hasSurprise boolean to set whether this Brick has surprise or not.
	 */
	public void setHasSurprise(boolean hasSurprise) {
		this.hasSurprise = hasSurprise;
	}

	/**
	 * This returns the boolean whether this Brick is metal or not.
	 * @return the brick is metal or not
	 */
	public boolean isMetal(){
		return isMetal;
	}

	/**
	 * This sets whether this Brick is metal or not.
	 * @param isMetal boolean to set whether this Brick is metal or not.
	 */	
	public void setMetal(boolean isMetal){
		this.isMetal = isMetal;
	}

	/**
	 * This returns the boolean whether this Brick is hit or not.
	 * @param go Any game object to check whether is hit or not 
	 * @return boolean whether this Brick is hit or not.
	 */
	public boolean hitted(GameObject go){
		return false;
	}
	
	/**
	 * This return boolean whether representation invariants 
	 * checks the x and y values of the location are positive 
	 * @return boolean representation invariant check
	 */
	public boolean repOk() {
		// EFFECTS: Returns true if the rep. invariant holds for this;
		// otherwise returns false. 
		if(this.getX()<0 || this.getY()<0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brick [isMetal=" + isMetal + ", hasSurprise=" + hasSurprise + "]";
	}
	
	
	

}
