package Model;
import java.awt.Dimension;
import java.util.ArrayList;

import Model.GameObject;
import Model.MovingObjects;
import Model.Wall;
import Model.Bricks.Brick;
import Utils.Constants;

public class WallFactory {
	private static WallFactory instance;
	private ArrayList<GameObject> gameObjectList;
	private ArrayList<Brick> brickList;
	
	private WallFactory(ArrayList<GameObject> gameObjectList, ArrayList<Brick> brickList ) {
		setGameObjectList(gameObjectList);
		setBrickList(brickList);
	}

	public static WallFactory getInstance(ArrayList<GameObject> gameObjectList, ArrayList<Brick> brickList ) {
		if(instance == null) {
			instance = new WallFactory(gameObjectList, brickList);
		}
		return instance;
	}
	

	public void createWalls(){
		Dimension dim = new Dimension(Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT);

		Wall wallLeft = new Wall( 0, 0, Constants.WALL_WIDTH, (int) dim.getHeight());
		Wall wallUp = new Wall(0, 0, (int) dim.getWidth(), Constants.WALL_WIDTH);
		Wall wallRight = new Wall(((int) dim.getWidth() - Constants.WALL_WIDTH), 0, Constants.WALL_WIDTH, (int) dim.getHeight());

		getGameObjectList().add(wallLeft);
		getGameObjectList().add(wallUp);
		getGameObjectList().add(wallRight);

		getBrickList().add(wallUp);
		getBrickList().add(wallRight);
		getBrickList().add(wallLeft);
	}


	public void createWalls(ArrayList<Brick> list){
		Dimension dim = new Dimension(Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT);
		Wall wallLeft = new Wall( 0, 0, Constants.WALL_WIDTH, (int) dim.getHeight());
		Wall wallUp = new Wall( 0, 0, (int) dim.getWidth(), Constants.WALL_WIDTH);
		Wall wallRight = new Wall(((int) dim.getWidth() - Constants.WALL_WIDTH), 0, Constants.WALL_WIDTH, (int) dim.getHeight());

		list.add(wallLeft);
		list.add(wallUp);
		list.add(wallRight);

	}

	public ArrayList<GameObject> getGameObjectList() {
		return gameObjectList;
	}

	public void setGameObjectList(ArrayList<GameObject> gameObjectList) {
		this.gameObjectList = gameObjectList;
	}

	public ArrayList<Brick> getBrickList() {
		return this.brickList;
	}

	public void setBrickList(ArrayList<Brick> brickList) {
		this.brickList = brickList;
	}
	
	
	

}
