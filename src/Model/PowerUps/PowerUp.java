package Model.PowerUps;

import java.io.Serializable;

public class PowerUp implements Serializable{
	private String type;

	public PowerUp(String type){
		// REQUIRES: 
		// MODIFIES:
		// EFFECTS: returns type of powerup
		this.type = type;
	}
	
	/**
	 * This method  returns type of the powerup.
	 *
	 */
	public String getType(){
		// REQUIRES: 
		// MODIFIES:
		// EFFECTS: returns type of powerup
		return type;
	}

	
	/**
	 * This sets type of the powerup.
	 * @param String Type to set the powerup
	 */
	public void setType(String type)
	{
		// REQUIRES: String value
		// MODIFIES:PowerUp value
		// EFFECTS: Sets type of powerup
		this.type = type;
	}

}
