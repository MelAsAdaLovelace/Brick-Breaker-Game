package Model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Model.Aliens.Alien;
import Model.Aliens.AlienFactory;
import Model.Aliens.AlienType;
import Model.Aliens.CooperativeAlien;
import Model.Aliens.CooperativeDrunkAlien;
import Model.Aliens.DrunkAlien;
import Model.Aliens.ProtectingAlien;
import Model.Aliens.ProtectingDrunkAlien;
import Model.Aliens.RepairingAlien;
import Model.Aliens.RepairingDrunkAlien;
import Model.Bricks.Brick;
import Model.Bricks.BrickFactory;
import Model.Bricks.BrickTypes;
import Model.Bricks.HalfMetalBrick;
import Model.Bricks.MiningBrick;
import Model.Bricks.SimpleBrick;
import Model.Controller.Controller;
import Model.PowerUps.FallingPowerUp;
import Model.PowerUps.LaserGun;
import Model.PowerUps.MagneticPaddle;
import Model.PowerUps.PowerUpFactory;
import Utils.Constants;
import Utils.FileManager;

public class Model implements Serializable{
	int brickWidth = Constants.BRICK_WIDTH;
	int brickHeight =  Constants.BRICK_HEIGHT;
	private PropertyChangeSupport property;
	private int paddleX;
	private int paddleDegree;
	private int currentDesign;
	private long collisionTime;
	private Controller controller;
	private BallFactory ballFactory;
	private PaddleFactory paddleFactory;
	private WallFactory wallFactory;
	private PlayerFactory playerFactory;
	private BrickFactory brickFactory;
	private PowerUpFactory powerUpFactory;

	private AlienFactory alienFactory;
	
	private Paddle paddle;
	private Player player;
	private String username;

	private ArrayList<GameObject> GameObjectList;
	private ArrayList<MovingObjects> MovingObjectList;
	private ArrayList<Brick> BrickList;
	private ArrayList<Alien> aliens;
	private ArrayList<DrunkAlien> drunkAliens;

	long curr;
	private static int a;
	private static int ab;
	private static int wrapperCount = 0;
	private static int brickCount = 0;
	private  int initBrickCount;
	private static int wrapperBroken = 0;
	static int rowX = Constants.WALL_WIDTH;
	private static int magnetCount = 0;
	
	private boolean alienSaved = false;


	public Model(){
		setCurrentDesign(0);
		setGameObjectList(new ArrayList<GameObject>());
		setMovingObjectList(new ArrayList<MovingObjects>());
		setBrickList(new ArrayList<Brick>());
		setAliens(new ArrayList<Alien>());
		setDrunkAliens(new ArrayList<DrunkAlien>());

		setProperty(new PropertyChangeSupport(this));

		ballFactory = BallFactory.getInstance(GameObjectList, MovingObjectList, property);
		paddleFactory = PaddleFactory.getInstance(GameObjectList, MovingObjectList, property);
		wallFactory = WallFactory.getInstance(GameObjectList, BrickList);
		playerFactory = PlayerFactory.getInstance();
		brickFactory = BrickFactory.getInstance(GameObjectList, BrickList);
		powerUpFactory =  PowerUpFactory.getInstance(GameObjectList, MovingObjectList);

		alienFactory = AlienFactory.getInstance(GameObjectList, MovingObjectList, aliens, drunkAliens);

		setPlayer(playerFactory.createPlayer());
		getPlayer().setProperty(getProperty());
		setPaddle(paddleFactory.createPaddle());
		wallFactory.createWalls();
		ballFactory.setShootable(true);
	}


	public boolean tryToLoad(String username) {
		ArrayList<Brick> savedBricks = FileManager.loadDesign(username);
		if(savedBricks == null) {
			return false;
		}
		return true;
	}
	public void loadGame(String username){
		ArrayList<Brick> savedBricks = FileManager.loadDesign(username);
		long score = FileManager.loadScore(getPlayer(), username);
		Alien aliens = FileManager.loadAlien(username);
		if (savedBricks == null) {
			savedBricks = FileManager.loadDesign(username);
			setCurrentDesign(0);
			if (savedBricks == null) {
				getBrickFactory().createBricks(getController().getInputController().getSb(),
						getController().getInputController().getHmb(),
						getController().getInputController().getWb(), getController().getInputController().getMb() );
				
				setCurrentDesign(0);
				getGameObjectList().removeAll(getMovingObjectList());
				getMovingObjectList().clear();
				initBrickCount = getBrickList().size();
				return;
			}
		}

		getGameObjectList().removeAll(getMovingObjectList());
		getGameObjectList().removeAll(getBrickList());
		getGameObjectList().removeAll(alienFactory.getAliens());
		getMovingObjectList().removeAll(getAliens());

		setBrickList(savedBricks);
		if(aliens != null) {
			alienSaved = true;
			if(aliens instanceof DrunkAlien) {
				alienFactory.getDrunkAliens().add((DrunkAlien) aliens);
			}
			alienFactory.getAliens().add(aliens);
			getGameObjectList().add(aliens);
//			getMovingObjectList().add(aliens);
			aliens.setMoving(true);
			System.out.println(alienFactory.getAliens().size());
		}
		getGameObjectList().addAll(savedBricks);
		setCurrentDesign(0);
		getMovingObjectList().clear();
		getPlayer().resetPowerUps();
		getPlayer().setScore(score);
		ballFactory.setBalls(0);
		ballFactory.setShootable(true);

	}

	public boolean update(){

		boolean isUpdated = false;
		if (getPaddleX() != 0){
			getPaddle().move(getPaddleX());
			putOnPaddle();
			isUpdated = true;
		}
		if (getPaddle().getWidth() != getPaddle().getPaddleWidth()){
			getPaddle().doublePaddle();
			isUpdated = true;
		}
		if( magnetCount == 5) {
			paddleFactory.setMagnetic(false);
			remove(paddle);
			setPaddle(paddleFactory.createPaddle());
			magnetCount = 0;
		}


		if (getPlayer().updatePowerUps())
			isUpdated = true;
		if (ballFactory.isShootable()){
			ballFactory.createBall(paddle);
			ballFactory.setShootable(false);
			isUpdated = true;
		}

		for (int i = 0; i < getMovingObjectList().size(); i++){
			MovingObjects obj = getMovingObjectList().get(i);
			if (obj.isMoving()){
				isUpdated = true;
				if (obj.toDestroy()){
					remove(obj);
					i--;
					if (obj instanceof Ball && obj.getY() > Constants.GAMEBOARD_HEIGHT - 40){
						ballFactory.setBalls(ballFactory.getBalls() - 1);
						if (ballFactory.getBalls() == 0){ //end of the game
							if (getPlayer().dieOnce()) {
								getController().setGameOver(true);
								break;
							}
							ballFactory.setShootable(true);
						}
					}
					continue;
				}


				if (obj.isCollision(getPaddle())){
					obj.collideWith(getPaddle());
					if (obj instanceof FallingPowerUp){
						getPlayer().collectPowerUp(((FallingPowerUp) obj).getPowerUp());
						isUpdated = true;
					}
				}
				if (DesignEmpty()){
					getController().setGameOver(true);
					return true;
				}
				for (int j = 0; j < getBrickList().size(); j++){
					brickCount = getBrickList().size();
					Brick brick = getBrickList().get(j);
					if(brick instanceof MiningBrick) {
						((MiningBrick) brick).move();
					}
					if(obj instanceof Ball && obj.isCollision(brick) && getPaddle() instanceof MagneticPaddle && !(brick instanceof Wall)) {
						if(obj.isCollision(brick)) {
							obj.collideWith(brick);
							if(paddleFactory.isMagnetic()) {
								magnetCount++;
								System.out.println(magnetCount);
							}
							remove(brick);
							remove(obj);
							obj = ballFactory.createBall(getPaddle());
							obj.setDirectionY(-1);
							obj.setX(getPaddle().getX() + getPaddle().getWidth()/2 - obj.getWidth());
							obj.setY(getPaddle().getY() - obj.getHeight());

						}
					}
					if (obj.isCollision(brick)){
						obj.collideWith(brick);
						if(brick instanceof HalfMetalBrick) {
							if(obj.isCollisionFromTop(obj) && ((obj instanceof Ball) || 
									(obj instanceof CooperativeAlien) || (obj instanceof CooperativeDrunkAlien))
									|| (obj instanceof LaserGun)) {
								remove(brick);
							}

						}else if (brick.hitted(obj) && !(obj instanceof RepairingAlien)  && !(obj instanceof ProtectingAlien) 
								&& !(obj instanceof RepairingDrunkAlien)  && !(obj instanceof ProtectingDrunkAlien)){
							remove(brick);
							getPlayer().addScore(getCollisionTime() - controller.getTime() );
							if (brick.isHasSurprise()){
								wrapperBroken++;
								double r = Math.random();

								if(getDrunkAliens().size() == 0 || alienSaved) {
									if(wrapperBroken % 10 == 0) {
										if(initBrickCount * 0.7 < brickCount) {
											if(getDrunkAliens().size() > 0) {
												if(getDrunkAliens().get(0) instanceof RepairingDrunkAlien) {
													getDrunkAliens().get(0).setCount(0);
													remove(getDrunkAliens().get(0));
												}
											}
											alienFactory.getDrunkAlien(AlienType.CooperativeDrunk, ((Brick) brick));

										}else {
											if(getDrunkAliens().size() > 0) {
												if(getDrunkAliens().get(0) instanceof CooperativeDrunkAlien) {
													remove(getDrunkAliens().get(0));
												}
											}
											if(initBrickCount * 0.6< brickCount) {
												alienFactory.getDrunkAlien(AlienType.RepairingDrunk, ((Brick) brick));

											}else {

												alienFactory.getDrunkAlien(AlienType.ProtectingDrunk, ((Brick) brick));
											}
										}


									}
								}


								if(r < 0.7) {
									powerUpFactory.createPowerUp((Brick) brick);
								}else {
									if(getAliens().size() == 0) {
										double r2 = Math.random();
										if(r2 >0 && r2 < 0.25) {
											alienFactory.getAlien(AlienType.Cooperative, (Brick) brick);
										}else if(r2 >= 0.25 && r2 < 0.5) {
											alienFactory.getAlien(AlienType.Repairing, (Brick) brick);	

										}
										else if(r2 >= 0.5 && r2 < 1) {
											alienFactory.getAlien(AlienType.Protecting, (Brick) brick);	
										}

									}
								}

							}

							j--;
						}
					}
					if(!getAliens().isEmpty()) {
						Alien ca = getAliens().get(0);
						if(obj.isCollision(ca)) {
							obj.collideWith(ca);
							if(ca.hitted(obj)) {
								remove(ca);
							}
						}
					}
				}
				obj.move();
			}


		}
		return isUpdated;
	}
	public Brick repairBrick() {
		Brick b = null;
		Random rand = new Random();
		int i = rand.nextInt(Constants.rows);
		int j = rand.nextInt(Constants.cols);
		if(brickFactory.getBrickMap()[i][j] == null) {
			add(alienFactory.repairBrick(j * Constants.BRICK_WIDTH + Constants.WALL_WIDTH,
					i * Constants.BRICK_HEIGHT + Constants.WALL_WIDTH));
			brickFactory.getBrickMap()[i][j] = alienFactory.repairBrick(j * Constants.BRICK_WIDTH  + Constants.WALL_WIDTH,
					i * Constants.BRICK_HEIGHT  + Constants.WALL_WIDTH);
		}
		return b;
	}

	public Brick repairRow() {
		Brick b = null;
		Random rand = new Random();
		int i = Constants.rows-2;
		int j = rand.nextInt(Constants.cols);
		if(brickFactory.getBrickMap()[i][j] == null) {
			add(alienFactory.repairBrick(j * Constants.BRICK_WIDTH  + Constants.WALL_WIDTH,
					i * Constants.BRICK_HEIGHT + Constants.WALL_WIDTH));
			brickFactory.getBrickMap()[i][j] =alienFactory.repairBrick(j * Constants.BRICK_WIDTH  + Constants.WALL_WIDTH,
					i * Constants.BRICK_HEIGHT + Constants.WALL_WIDTH);
		}
		return b;
	}
	public void changePaddle() {
		if(paddleFactory.isMagnetic()&& magnetCount < 5) {
			remove(paddle);
			setPaddle(paddleFactory.createPaddle());
			if(magnetCount == 5) {
				magnetCount = 0;
				paddleFactory.setMagnetic(false);
				remove(paddle);
				setPaddle(paddleFactory.createPaddle());
			}
		}

	}


	private boolean DesignEmpty(){
		if (getBrickList().size() <= 3) //walls are bricks.
			return true;
		return false;
	}
	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();

	}

	public void add(Brick b) {
		getGameObjectList().add(b);
		getBrickList().add(b);
	}

	public void remove(GameObject obj){
		if(obj instanceof Alien) {
			getAliens().remove(obj);
			getMovingObjectList().remove(obj);
			getGameObjectList().remove(obj);
			if(obj instanceof RepairingAlien) {
				getAlienFactory().getRepairing().remove(obj);
			}
			if(obj instanceof RepairingDrunkAlien) {
				getAlienFactory().getRepairingDrunk().remove(obj);
			}

			if(getDrunkAliens().size() > 0)
				getDrunkAliens().get(0).setCount(0);
			getDrunkAliens().remove(obj);

		}
		else if (obj instanceof Brick){
			collisionTime = System.currentTimeMillis() / 1000;
			getBrickList().remove(obj);

			getGameObjectList().remove(obj);
		}else if (obj instanceof MovingObjects){
			getMovingObjectList().remove(obj);
			getGameObjectList().remove(obj);

		}else if (obj instanceof Paddle){
			getGameObjectList().remove(obj);
		}
	}




	public void putOnPaddle(){
		for (int i = 0; i < getMovingObjectList().size(); i++){
			MovingObjects obj = getMovingObjectList().get(i);
			if (!obj.isMoving()){
				obj.setCoordinates(
						(int) (getPaddle().getX() + (getPaddle().getWidth() / 2)) - (obj.getWidth() / 2),
						(int) (getPaddle().getY() - obj.getHeight()));

			}
		}
	}

	public void shootBall(){ //when on the paddle
		for (int i = 0; i < getMovingObjectList().size(); i++){
			MovingObjects obj = getMovingObjectList().get(i);
			if (obj.getClass() == Ball.class && !obj.isMoving()){
				obj.setMoving(true);
				break;
			}
		}
	}

	public void fireLaserGun(){
		for (int i = 0; i < getMovingObjectList().size(); i++){
			MovingObjects obj = getMovingObjectList().get(i);
			if (obj.getClass() == LaserGun.class && !obj.isMoving()){
				obj.setMoving(true);
				break;
			}
		}
	}



	public Brick createBrick( Point coordinates, int i){
		BrickTypes type;
		BrickTypes[] BTArray = BrickTypes.values();
		if(i < BTArray.length) {
			type = BTArray[i];
		}else {
			type = BTArray[0];
		}
		return brickFactory.createBrick(type, coordinates.x, coordinates.y);
	}

	public boolean addBrick(MouseEvent me, int i){
		Brick b = createBrick(mouseClickPoint(me), i);
		//x = (simple_j * brickWidth) + Constants.WALL_WIDTH
		int j1 =  (int) (mouseClickPoint(me).getX()/ brickWidth);
		int i1 = (int) (mouseClickPoint(me).getY()/ brickHeight);
		int r = (me.getY() - Constants.WALL_WIDTH)/Constants.BRICK_HEIGHT;
		int c = (me.getX() - Constants.WALL_WIDTH)/Constants.BRICK_WIDTH;
		if (getBrickFactory().getBrickMap()[r][c] == null){ 
			getBrickFactory().getBrickMap()[r][c] = b;
			getBrickList().add(b);
			getGameObjectList().add(b);
			return true;
		}
		return false;
	}
	public boolean removeBrick(MouseEvent me, int i) {
		Brick b = createBrick(mouseClickPoint(me), i);
		int r = (me.getY() - Constants.WALL_WIDTH)/Constants.BRICK_HEIGHT;
		int c = (me.getX() - Constants.WALL_WIDTH)/Constants.BRICK_WIDTH;
		if(getBrickFactory().getBrickMap()[r-1][c] != null) {
			b = getBrickFactory().getBrickMap()[r-1][c];
			remove(getBrickFactory().getBrickMap()[r-1][c]);
			getBrickFactory().getBrickMap()[r-1][c] = null;
			return true;
		}

		return false;
	}

	public boolean saveDesign(String username){
		FileManager.saveDesign(getBrickList(),  username);
		FileManager.saveScore(getPlayer(), username);
		if(getAliens().size() == 1)
			FileManager.saveAliens(getAliens().get(0), username);
		if (username != null){
			return true;
		}
		return false;
	}

	public Point mouseClickPoint(MouseEvent me){
		int wall = Constants.WALL_WIDTH;
		int x_mouse = me.getX();
		int y_mouse = me.getY();
		int col = (x_mouse - wall) / brickWidth;
		int row = (y_mouse - wall) / brickHeight;
		int x = col * brickWidth + wall ;
		int y = row * brickHeight + wall - Constants.PLAYER_PANEL_HEIGHT;
		return new Point(x,y);
	}


	public BallFactory getBallFactory() {
		return ballFactory;
	}


	public int getBrickCount() {
		return brickCount;
	}

	public void setBrickCount(int brickCount) {
		this.brickCount = brickCount;
	}

	public int getPaddleDegree() {
		return paddleDegree;
	}

	public void setPaddleDe(int paddleDegree) {
		this.paddleDegree = paddleDegree;
	}

	public int getPaddleX(){
		return paddleX;
	}

	public void setPaddleX(int paddleX){
		this.paddleX = paddleX;
	}

	public ArrayList<GameObject> getGameObjectList(){
		return GameObjectList;
	}

	public void setGameObjectList(ArrayList<GameObject> objectList){
		this.GameObjectList = objectList;
	}

	public ArrayList<MovingObjects> getMovingObjectList(){
		return MovingObjectList;
	}

	public void setMovingObjectList(ArrayList<MovingObjects> movingObjectList){
		this.MovingObjectList = movingObjectList;
	}

	public ArrayList<Brick> getBrickList(){
		return BrickList;
	}

	public void setBrickList(ArrayList<Brick> BrickList){
		this.BrickList = BrickList;
	}

	public Controller getController(){
		return controller;
	}

	public void setController(Controller controller){
		this.controller = controller;
	}

	public Paddle getPaddle(){
		return paddle;
	}

	public void setPaddle(Paddle paddle){
		this.paddle = paddle;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player){
		this.player = player;
	}



	public int getCurrentDesign(){
		return currentDesign;
	}

	public void setCurrentDesign(int currentDesign){
		this.currentDesign = currentDesign;
	}

	public PropertyChangeSupport getProperty(){

		return property;
	}

	public void setProperty(PropertyChangeSupport property){
		this.property = property;
	}


	public long getCollisionTime() {
		return collisionTime;
	}


	public void setCollisionTime(long collisionTime) {
		this.collisionTime = collisionTime;
	}


	public void setPaddleDegree(int paddleDegree) {
		this.paddleDegree = paddleDegree;
	}


	public PaddleFactory getPaddleFactory() {
		return paddleFactory;
	}


	public void setPaddleFactory(PaddleFactory paddleFactory) {
		this.paddleFactory = paddleFactory;
	}


	public WallFactory getWallFactory() {
		return wallFactory;
	}


	public void setWallFactory(WallFactory wallFactory) {
		this.wallFactory = wallFactory;
	}


	public PlayerFactory getPlayerFactory() {
		return playerFactory;
	}


	public void setPlayerFactory(PlayerFactory playerFactory) {
		this.playerFactory = playerFactory;
	}


	public BrickFactory getBrickFactory() {
		return brickFactory;
	}


	public void setBrickFactory(BrickFactory brickFactory) {
		this.brickFactory = brickFactory;
	}


	public void setBallFactory(BallFactory ballFactory) {
		this.ballFactory = ballFactory;
	}


	public PowerUpFactory getPowerUpFactory() {
		return powerUpFactory;
	}


	public void setPowerUpFactory(PowerUpFactory powerUpFactory) {
		this.powerUpFactory = powerUpFactory;
	}

	public ArrayList<Alien> getAliens() {
		return aliens;
	}


	public void setAliens(ArrayList<Alien> aliens) {
		this.aliens = aliens;
	}

	public ArrayList<DrunkAlien> getDrunkAliens() {
		return drunkAliens;
	}


	public void setDrunkAliens(ArrayList<DrunkAlien> drunkAliens) {
		this.drunkAliens = drunkAliens;
	}


	public long getCurr() {
		return curr;
	}


	public void setCurr(long curr) {
		this.curr = curr;
	}


	public static int getA() {
		return a;
	}


	public static void setA(int a) {
		Model.a = a;
	}


	public static int getAb() {
		return ab;
	}


	public static void setAb(int ab) {
		Model.ab = ab;
	}


	/**
	 * @return the alienFactory
	 */
	public AlienFactory getAlienFactory() {
		return alienFactory;
	}


	/**
	 * @param alienFactory the alienFactory to set
	 */
	public void setAlienFactory(AlienFactory alienFactory) {
		this.alienFactory = alienFactory;
	}




	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}






}
