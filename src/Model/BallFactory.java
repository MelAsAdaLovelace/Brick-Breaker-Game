package Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import Model.Bricks.Brick;
import Utils.Constants;
import View.GamePanel;

public class BallFactory implements PropertyChangeListener{
	private static BallFactory instance;

	
	private static ArrayList<GameObject> gameObjectList;
	private static ArrayList<MovingObjects> movingObjectList;
	
	private static PropertyChangeSupport property;
	private boolean isShootable;
	private int balls;
	private static Ball ball;
	
	private BallFactory(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList,
			PropertyChangeSupport property) {
		setGameObjectList(gameObjectList);
		setMovingObjectList(movingObjectList);
		setProperty(property);
		getProperty().addPropertyChangeListener("GangOfBalls", this);
		getProperty().addPropertyChangeListener("ChemicalBall",this);
		this.setShootable(true);
		setBalls(0);
	}


	public static BallFactory getInstance(ArrayList<GameObject> gameObjectList, ArrayList<MovingObjects> movingObjectList,
			PropertyChangeSupport property) {
		if(instance == null) {
			instance = new BallFactory(gameObjectList, movingObjectList, property);
		}
		return instance;
	}

	public Ball createBall(Paddle paddle){
		ball = new Ball(
				(int) (paddle.getX() + (Constants.PADDLE_WIDTH / 2)) - (Constants.BALL_RADIUS),
				(int) (paddle.getY() - 2 * Constants.BALL_RADIUS),(-1 - ((int) Math.random())), -1,
				Math.random() * 0.8, Constants.BALL_SPEED);
		getGameObjectList().add(ball);
		getMovingObjectList().add(ball);
		getProperty().addPropertyChangeListener("ChemicalBall", ball);
		setBalls(1);
		ball.setShootable(this.isShootable);
		getProperty().addPropertyChangeListener("GangOfBalls", ball);
		return ball;
	}
	
	private void createGangOfBalls(Ball b, int numBalls) {
		for (int i = 1; i <= numBalls; i++){
			int dirX = 1;
			int dirY = -1;
			double angle = i * 36;
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			if (x < 0)
				dirX = -1;
			if (y < 0)
				dirY = 1;
			Ball ball = new Ball(b.getX(), b.getY(),  dirX, dirY,
					Math.pow(x, 2), Constants.BALL_SPEED);
			ball.setMoving(true);
			getGameObjectList().add(ball);
			getMovingObjectList().add(ball);
			getProperty().addPropertyChangeListener("GangOfBalls", ball);
			getProperty().addPropertyChangeListener("ChemicalBall", ball);
			setBalls(getBalls() + 1);
		}
	}
	
	
	private void magneticBall(Paddle paddle) {
		int dirX = 0;
		int dirY = -1;
		Ball ball = new Ball((int) (paddle.getX() + Constants.PADDLE_WIDTH/2 - Constants.BALL_RADIUS),(int) (paddle.getY() - 2 * Constants.BALL_RADIUS),  dirX, dirY,
				0, Constants.BALL_SPEED);
		ball.setMoving(false);
		ball.setShootable(true);
		getGameObjectList().add(ball);
		getMovingObjectList().add(ball);
	
		setBalls(1);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent prop) {
		// TODO Auto-generated method stub
		if (prop.getPropertyName() == "GangOfBalls"){
			if ((Boolean) prop.getNewValue() == true){
				if (getBalls() > 10)
					return;
				int k = getMovingObjectList().size();
				for (int i = 0; i < k; i++){
					MovingObjects obj = getMovingObjectList().get(i);
					if (obj.getClass() == Ball.class && obj.isMoving()){
						createGangOfBalls((Ball) obj, 9);
					}
				}
			}
		}
	
	}

	
	public void setInstance(BallFactory instance) {
		this.instance = instance;
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

	public PropertyChangeSupport getProperty() {
		return property;
	}

	public void setProperty(PropertyChangeSupport property) {
		this.property = property;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	/**
	 * @return the isShootable
	 */
	public boolean isShootable() {
		return isShootable;
	}


	/**
	 * @param isShootable the isShootable to set
	 */
	public void setShootable(boolean isShootable) {
		this.isShootable = isShootable;
		Ball.setShootable(this.isShootable);
	}


	/**
	 * @return the ball
	 */
	public static Ball getBall() {
		return ball;
	}


	/**
	 * @param ball the ball to set
	 */
	public void setBall(Ball ball) {
		ball = ball;
	}


	/**
	 * @return the instance
	 */
	public static BallFactory getInstance() {
		return instance;
	}




	
	

}
