package Model;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import Model.PowerUps.LaserGun;
import Model.PowerUps.MagneticPaddle;
import Utils.Constants;

public class PaddleFactory implements PropertyChangeListener{
	private static PaddleFactory instance;
	private ArrayList<GameObject> gameObjectList;
	private ArrayList<MovingObjects> movingObjectList;
	private static PropertyChangeSupport property;
	private Paddle paddle = new Paddle(((int) (Constants.GAMEBOARD_WIDTH- Constants.PADDLE_WIDTH) / 2),
			Constants.PADDLE_Y, Constants.PADDLE_OFFSET);
	private boolean isMagnetic = false;
	private int magneticCount = 0;

	private PaddleFactory(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList,
			PropertyChangeSupport property) {
		setGameObjectList(gameObjectList);
		setMovingObjectList(movingObjectList);
		setProperty(property);
		getProperty().addPropertyChangeListener("DestructiveLaserGun", this);
		getProperty().addPropertyChangeListener("Magnet", this);

	}

	public static PaddleFactory getInstance (ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList,
			PropertyChangeSupport property) {
		if(instance == null) {
			instance = new PaddleFactory(gameObjectList, movingObjectList, property);
		}
		return instance;
	}

	public Paddle createPaddle(){

		if(isMagnetic ) {
			paddle = new MagneticPaddle(paddle.getX(),
					Constants.PADDLE_Y, Constants.PADDLE_OFFSET);
			isMagnetic = true;
		}else {
			paddle = new Paddle(paddle.getX(),
					Constants.PADDLE_Y, Constants.PADDLE_OFFSET);
			isMagnetic = false;
		}

		getGameObjectList().add(paddle);
		getProperty().addPropertyChangeListener("DoublePaddle", paddle);
		getProperty().addPropertyChangeListener("Magnet", paddle);
		setPaddle(paddle);
		return paddle;
	}

	public void createLaserGun(Paddle paddle){
		int count = 0;
		for (MovingObjects obj : getMovingObjectList())	{
			if (obj.getClass() == LaserGun.class && obj.isMoving() == false)
				count++;
			if (count >= Constants.BULLETS)
				return;
		}
		while (count < Constants.BULLETS){
			LaserGun laserGun1 = new LaserGun((int) (paddle.getX() + getPaddle().getWidth()/ 2 - 2*Constants.BALL_RADIUS),
					(int) (paddle.getY() - 2 * Constants.BALL_RADIUS),
					Constants.BALL_RADIUS * 2, Constants.BALL_RADIUS * 2,
					Constants.BULLET_SPEED);
			getGameObjectList().add(laserGun1);
			getMovingObjectList().add(laserGun1);
			count++;
		}
	}



	@Override
	public void propertyChange(PropertyChangeEvent prop) {
		// TODO Auto-generated method stub
		if (prop.getPropertyName() == "DestructiveLaserGun"){
			if ((Boolean) prop.getNewValue() == true)
				createLaserGun(getPaddle());
		}
		if (prop.getPropertyName() == "Magnet"){
			if ((Boolean) prop.getNewValue() == true) {
				setMagnetic(true);
//				if(isMagnetic) {
//					magneticCount++;
//					setMagnetic(false);
//				}
			}else {
				setMagnetic(false);
			}
		}
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

	public static PropertyChangeSupport getProperty() {
		return property;
	}

	public static void setProperty(PropertyChangeSupport property) {
		PaddleFactory.property = property;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	/**
	 * @return the isMagnetic
	 */
	public boolean isMagnetic() {
		return isMagnetic;
	}

	/**
	 * @param isMagnetic the isMagnetic to set
	 */
	public void setMagnetic(boolean isMagnetic) {
		this.isMagnetic = isMagnetic;
	}

	/**
	 * @return the magneticCount
	 */
	public int getMagneticCount() {
		return magneticCount;
	}

	/**
	 * @param magneticCount the magneticCount to set
	 */
	public void setMagneticCount(int magneticCount) {
		this.magneticCount = magneticCount;
	}





}
