package Model.Aliens;

import java.util.ArrayList;
import java.util.Random;

import Model.GameObject;
import Model.MovingObjects;
import Model.Bricks.Brick;
import Model.Bricks.SimpleBrick;
import Utils.Constants;

public class AlienFactory {
	//OVERVIEW: All Aliens will be created in this factory using composite pattern
	private static AlienFactory instance;
	private  ArrayList<GameObject> gameObjectList;
	private  ArrayList<MovingObjects> movingObjectList;
	private ArrayList<Alien> aliens;
	private ArrayList<DrunkAlien> drunkAliens;
	
	private ArrayList<Alien> repairing;
	private ArrayList<Alien> repairingDrunk;

	private AlienFactory(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList,  ArrayList<Alien> aliens,
			ArrayList<DrunkAlien> drunkAliens) {
		this.gameObjectList = gameObjectList;
		this.movingObjectList = movingObjectList;
		this.aliens = aliens;
		this.drunkAliens = drunkAliens;
		repairing = new ArrayList<Alien>();
		repairingDrunk = new ArrayList<Alien>();
	}

	public static AlienFactory getInstance(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList,
			ArrayList<Alien> aliens, ArrayList<DrunkAlien> drunkAliens) {
		if(instance == null) {
			instance = new AlienFactory(gameObjectList, movingObjectList, aliens, drunkAliens);
		}
		return instance;
	}

	public Alien getAlien(AlienType type, Brick brick) {
		Alien alien = null;
		if(type.equals(AlienType.Cooperative)) {
			alien = new CooperativeAlien(Constants.GAMEBOARD_WIDTH/2 , brick.getY());
		}else if(type.equals(AlienType.Protecting)) {
			alien = new ProtectingAlien(Constants.WALL_WIDTH , Constants.PLAYABLE);
		}else if(type.equals(AlienType.Repairing)) {
			alien = new RepairingAlien(Constants.WALL_WIDTH , Constants.PLAYABLE);
			repairing.add(alien);
		}
		gameObjectList.add(alien);
		movingObjectList.add(alien);
		aliens.add(alien);		
		return alien;
	}

	public synchronized Brick repairBrick(int loc_i, int loc_j) {
		return new SimpleBrick(loc_i, loc_j);
	}

	public DrunkAlien getDrunkAlien(AlienType type, Brick brick) {
		DrunkAlien alien = null;
		if(type.equals(AlienType.CooperativeDrunk)) {
			alien = new CooperativeDrunkAlien(Constants.GAMEBOARD_WIDTH/2 , brick.getY());
		}else if(type.equals(AlienType.ProtectingDrunk)) {
			alien = new ProtectingDrunkAlien(Constants.WALL_WIDTH , Constants.PLAYABLE);
		}else if(type.equals(AlienType.RepairingDrunk)) {
			alien = new RepairingDrunkAlien(Constants.WALL_WIDTH , Constants.PLAYABLE);
			repairingDrunk.add(alien);
		}
		gameObjectList.add(alien);
		movingObjectList.add(alien);
		aliens.add(alien);	
		drunkAliens.add(alien);
		return alien;
	}

	/**
	 * @return the gameObjectList
	 */
	public ArrayList<GameObject> getGameObjectList() {
		return gameObjectList;
	}

	/**
	 * @param gameObjectList the gameObjectList to set
	 */
	public void setGameObjectList(ArrayList<GameObject> gameObjectList) {
		this.gameObjectList = gameObjectList;
	}

	/**
	 * @return the movingObjectList
	 */
	public ArrayList<MovingObjects> getMovingObjectList() {
		return movingObjectList;
	}

	/**
	 * @param movingObjectList the movingObjectList to set
	 */
	public void setMovingObjectList(ArrayList<MovingObjects> movingObjectList) {
		this.movingObjectList = movingObjectList;
	}

	/**
	 * @return the aliens
	 */
	public ArrayList<Alien> getAliens() {
		return aliens;
	}

	/**
	 * @param aliens the aliens to set
	 */
	public void setAliens(ArrayList<Alien> aliens) {
		this.aliens = aliens;
	}

	/**
	 * @param instance the instance to set
	 */
	public static void setInstance(AlienFactory instance) {
		AlienFactory.instance = instance;
	}

	/**
	 * @return the drunkAliens
	 */
	public ArrayList<DrunkAlien> getDrunkAliens() {
		return drunkAliens;
	}

	/**
	 * @param drunkAliens the drunkAliens to set
	 */
	public void setDrunkAliens(ArrayList<DrunkAlien> drunkAliens) {
		this.drunkAliens = drunkAliens;
	}

	/**
	 * @return the repairing
	 */
	public ArrayList<Alien> getRepairing() {
		return repairing;
	}

	/**
	 * @param repairing the repairing to set
	 */
	public void setRepairing(ArrayList<Alien> repairing) {
		this.repairing = repairing;
	}

	/**
	 * @return the repairingDrunk
	 */
	public ArrayList<Alien> getRepairingDrunk() {
		return repairingDrunk;
	}

	/**
	 * @param repairingDrunk the repairingDrunk to set
	 */
	public void setRepairingDrunk(ArrayList<Alien> repairingDrunk) {
		this.repairingDrunk = repairingDrunk;
	}


}
