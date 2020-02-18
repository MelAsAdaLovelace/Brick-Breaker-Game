package Model.Bricks;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import Model.GameObject;
import Model.Aliens.AlienFactory;
import Utils.Constants;

public class BrickFactory {
	private static BrickFactory instance;
	private static Brick[][] brickMap = new Brick[Constants.rows][Constants.cols];

	private ArrayList<GameObject> gameObjectList;
	private ArrayList<Brick> brickList;

	private BrickFactory(ArrayList<GameObject> gameObjectList, ArrayList<Brick> brickList) {
		super();
		this.gameObjectList = gameObjectList;
		this.brickList = brickList;
	}
	public static BrickFactory getInstance(ArrayList<GameObject> gameObjectList, ArrayList<Brick> brickList) {
		if(instance == null) {
			instance = new BrickFactory(gameObjectList, brickList);
		}
		return instance;
	}

	public Brick createBrick(BrickTypes type,int x, int y){
		Brick brick =  new SimpleBrick( x, y);
		if(type.equals(BrickTypes.SIMPLE))		
			brick = new SimpleBrick( x, y);
		if(type.equals(BrickTypes.HALFMETAL))
			brick = new HalfMetalBrick(x, y);
		if(type.equals(BrickTypes.WRAPPER))
			brick = new WrapperBrick(x, y);
		if(type.equals(BrickTypes.MINING))
			brick = new MiningBrick(x, y);
		return brick;
	}

	public void createBricks(int sb, int m, int w, int mine){

		int rows = Constants.rows;
		int cols = Constants.cols;
		int brickWidth = Constants.BRICK_WIDTH;
		int brickHeight = Constants.BRICK_HEIGHT;

		Brick b = null;
		for(int s = 0; s<sb; s++) {
			boolean flag = true;
			while(flag) {
				Random rand = new Random();
				int simple_i =rand.nextInt(rows-1);
				int simple_j = rand.nextInt(cols);
				if(brickMap[simple_i][simple_j] == null) {
					b = createBrick(BrickTypes.SIMPLE,(simple_j * brickWidth) + Constants.WALL_WIDTH,
							(simple_i * brickHeight) + Constants.WALL_WIDTH);
					brickMap[simple_i][simple_j] = b;
					getGameObjectList().add(b);
					getBrickList().add(b);
					flag = false;
				}
			}
		}

		for(int s = 0; s<m; s++) {
			boolean flag = true;
			while(flag) {
				Random rand = new Random();
				int metal_j = rand.nextInt(cols);
				if(brickMap[rows-1][metal_j] == null) {
					b = createBrick(BrickTypes.HALFMETAL,metal_j * brickWidth + Constants.WALL_WIDTH,
							Constants.PLAYABLE - brickHeight);
					brickMap[rows-1][metal_j] = b;
					
					getGameObjectList().add(b);
					getBrickList().add(b);
					flag = false;					
				}
			}
		}

		for(int s = 0; s<w; s++) {
			boolean flag = true;
			while(flag) {
				Random rand = new Random();
				int wrapper_i = rand.nextInt(rows-2);
				int wrapper_j = rand.nextInt(cols);
				if(brickMap[wrapper_i][wrapper_j] == null) {
					b = createBrick(BrickTypes.WRAPPER,wrapper_j * brickWidth + Constants.WALL_WIDTH,
							wrapper_i * brickHeight + Constants.WALL_WIDTH);
					brickMap[wrapper_i][wrapper_j] = b;
					getGameObjectList().add(b);
					getBrickList().add(b);
					flag = false;
				}
			}
		}
		
		for(int s = 0; s<mine; s++) {
			boolean flag = true;
			while(flag) {
				Random rand = new Random();
				int mining_i = rand.nextInt(rows-2);
				int mining_j = rand.nextInt(cols);
				if(brickMap[mining_i][mining_j] == null) {
					b = createBrick(BrickTypes.MINING,mining_j * brickWidth + Constants.WALL_WIDTH,
							mining_i * brickHeight + Constants.WALL_WIDTH);
					brickMap[mining_i][mining_j] = b;
					getGameObjectList().add(b);
					getBrickList().add(b);
					flag = false;
				}
			}
		}
	}
	
	
	

	
	public void setInstance(BrickFactory instance) {
		this.instance = instance;
	}
	public ArrayList<GameObject> getGameObjectList() {
		return gameObjectList;
	}
	public void setGameObjectList(ArrayList<GameObject> gameObjectList) {
		this.gameObjectList = gameObjectList;
	}
	public ArrayList<Brick> getBrickList() {
		return brickList;
	}
	public void setBrickList(ArrayList<Brick> brickList) {
		this.brickList = brickList;
	}
	/**
	 * @return the brickMap
	 */
	public Brick[][] getBrickMap() {
		return brickMap;
	}
	/**
	 * @param brickMap the brickMap to set
	 */
	public void setBrickMap(Brick[][] brickMap) {
		this.brickMap = brickMap;
	}



}
