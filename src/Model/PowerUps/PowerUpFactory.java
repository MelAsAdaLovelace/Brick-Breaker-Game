package Model.PowerUps;

import java.awt.Color;
import java.util.ArrayList;

import Model.GameObject;
import Model.MovingObjects;
import Model.Bricks.Brick;
import Utils.Constants;

public class PowerUpFactory {
	private static PowerUpFactory instance;

	private ArrayList<GameObject> gameObjectList;
	private ArrayList<MovingObjects> movingObjectList;
	private Brick brick;

	private PowerUpFactory(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList) {
		setGameObjectList(gameObjectList);
		setMovingObjectList(movingObjectList);
	}

	public static PowerUpFactory getInstance(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList) {
		if(instance == null) {
			instance = new PowerUpFactory(gameObjectList, movingObjectList);
		}
		return instance;
	}


	public void createPowerUp(Brick b){
		double prob = Math.random();
		Color color;
		PowerUp powerUp;
		if (prob >= 0 && prob < 0.2){
			color = new Color(130, 66, 125);
			powerUp = new PowerUp("GangOfBalls");
			if (getMovingObjectList().size() > 2) 
				return;
		}
		else if (prob >= 0.2 && prob < 0.4){
			color = new Color(59, 103, 182);
			powerUp = new PowerUp("DestructiveLaserGun");
		}

		else if (prob >= 0.4 && prob < 0.6){
			color = new Color(248, 174, 182);
			powerUp = new TimedPowerUp(Constants.TALLER_PADDLE_DURATION, "DoublePaddle");
		}
		else if (prob >= 0.6 && prob < 0.8){
			color = new Color(145, 230, 182);
			powerUp = new TimedPowerUp(Constants.CHEMBALL_DURATION, "ChemicalBall");
		}
		else{
			color = Color.ORANGE;
			powerUp = new PowerUp("Magnet");
		}

		FallingPowerUp fallingPowerUp = new FallingPowerUp(b.getX() + (b.getWidth() - Constants.POWERUP_SIZE) / 2,
				b.getY(), Constants.POWERUP_SIZE, Constants.POWERUP_SIZE, 0, 1, 0.0,
				powerUp, color);

		fallingPowerUp.setMoving(true);
		getMovingObjectList().add(fallingPowerUp);
		getGameObjectList().add(fallingPowerUp);
	}

	public ArrayList<GameObject> getGameObjectList() {
		return gameObjectList;
	}

	public void setGameObjectList(ArrayList<GameObject> gameObjectList) {
		this.gameObjectList = gameObjectList;
	}

	public ArrayList<MovingObjects> getMovingObjectList() {
		return movingObjectList;
	}

	public void setMovingObjectList(ArrayList<MovingObjects> movingObjectList) {
		this.movingObjectList = movingObjectList;
	}

}
