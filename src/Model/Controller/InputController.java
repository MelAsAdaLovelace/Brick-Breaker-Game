package Model.Controller;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import Model.Model;
import Model.Paddle;
import Model.Bricks.BrickTypes;
import Utils.Constants;
import View.PausePanel;
import View.View;

public class InputController {

	private boolean cantPlay;
	private int keyCode;

	private Model model;
	private View view;
	private Controller controller;
	private String player;

	private static int brickType = 0;
	private int sb = 0;
	private int hmb = 0;
	private int wb= 0;
	private int mb= 0;
	private static int brickCount = 0;


	public InputController(Model model, Controller controller, View view) {
		this.setModel(model);
		this.setController(controller);
		setView(view);
		setCanPlay(false);
		this.keyCode = 0;
	}


	public void handleMouseInput2(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1) {
			getModel().shootBall();

		}
	}
	public void handleMouseInput3(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1) {
			if(isCantPlay() == true) {
				addModelBrick(me, getBrickType());
			}
		}if(me.getButton() ==  MouseEvent.BUTTON3) {
			if(isCantPlay() == true)
				deleteModelBrick(me, getBrickType());

		}
	}


	public void handleInput(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_P) {
			if ((getController().isRunning() == true)) {
				cantPlay = true;
				getModel().getPlayer().pauseGame();
				getController().setPause(true);
				view.getLayeredPanel().getMainPanel().getPlayerPanel().setPaused("Game is Paused.");

			} else {
				cantPlay = false;
				getView().getLayeredPanel().getPausePanel().resumeGame();
				getModel().getPlayer().resumeGame();
				getController().setRunning(true);
				view.getLayeredPanel().getMainPanel().getPlayerPanel().setPaused("");
			}
		}

		if (!cantPlay) {
			if (ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_LEFT) {
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT && !(getModel().getPaddleX() == 1)) {
					getModel().setPaddleX(1);
					setKeyCode(getKeyCode() + 1);
				} else if (ke.getKeyCode() == KeyEvent.VK_LEFT && !(getModel().getPaddleX() == -1)) {
					getModel().setPaddleX(-1);
					setKeyCode(getKeyCode() + 1);
				}
			}
			if (ke.getKeyCode() == KeyEvent.VK_W) {
				getModel().shootBall();
			}

			if (ke.getKeyCode() == KeyEvent.VK_A) {
				getModel().getPaddle().setIsRotatedToLeft(true);
			}

			if (ke.getKeyCode() == KeyEvent.VK_D) {
				getModel().getPaddle().setIsRotatedToRight(true);
			}


			if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
				getModel().fireLaserGun();
			}
			if(ke.getKeyCode() == KeyEvent.VK_M) {
				getModel().changePaddle();
			}
		}
		else
			if (ke.getKeyCode() == KeyEvent.VK_Q) {
				getView().getBrickFrame().returnToMenu();
				getView().getLayeredPanel().getPausePanel().resumeGame();
				getView().getMainFrame().setVisible(false);
			}
	}

	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_LEFT) {
			setKeyCode(getKeyCode() - 1);
			if (getKeyCode() == 1 && ke.getKeyCode() == KeyEvent.VK_RIGHT)
				getModel().setPaddleX(-1);
			else if (getKeyCode() == 1 && ke.getKeyCode() == KeyEvent.VK_LEFT)
				getModel().setPaddleX(1);
			else
				getModel().setPaddleX(0);
		}

		if(ke.getKeyCode() == KeyEvent.VK_A) {
			getModel().getPaddle().setIsRotatedToLeft(false);
		}

		if(ke.getKeyCode() == KeyEvent.VK_D) {
			getModel().getPaddle().setIsRotatedToRight(false);
		}

	}

	public void simpleBrCount(String n) {
		try {
			sb = Integer.parseInt(n);
		}catch(IllegalArgumentException a ) {
			sb = 75;
		}
		updateTotalBricks();
	}

	public void halfMetalBrCount(String n) {
		try {
			hmb = Integer.parseInt(n);
		}catch(IllegalArgumentException a ) {
			hmb = 10;
		}
		updateTotalBricks();
	}

	public void mineBrCount(String n) {
		try {
			mb = Integer.parseInt(n);
		}catch(IllegalArgumentException a ) {
			mb = 10;
		}
		updateTotalBricks();
	}

	public void wrapperBrCount(String n) {
		try {
			wb = Integer.parseInt(n);
		}catch(IllegalArgumentException a ) {
			wb = 10;
		}
		updateTotalBricks();

	}

	public void updateTotalBricks() {
		brickCount = getSb() + getHmb() + getMb() + getWb();
	}

	public String totalBricks() {
		return "" + getBrickCount();
	}


	public void addModelBrick(MouseEvent me, int i) {
		if(!(me.getX() <= Constants.WALL_WIDTH  || me.getX() >= Constants.GAMEBOARD_WIDTH - Constants.WALL_WIDTH - Constants.BRICK_WIDTH||
				Constants.WALL_WIDTH + Constants.PLAYER_PANEL_HEIGHT >= me.getY()  || me.getY() >= Constants.PLAYABLE)) {
			if (getModel().addBrick(me, i)) {
				getView().getLayeredPanel().render(getModel().getGameObjectList(), getModel().getPlayer());
			}
		}
	}



	private void deleteModelBrick(MouseEvent me, int i) {
		if(getModel().removeBrick(me, i)) {
			getView().getLayeredPanel().render(getModel().getGameObjectList(), getModel().getPlayer());
		}

	}

	public void saveDesign(String username) {
		if (getModel().saveDesign(username)) {
			//	getView().getBuilderView().getBrickPanel().changeMaxDesign(Design);
		}

	}


	public void menuStart() {
		cantPlay = true;
		getController().startGame();
	}


	public void menuPlay() {
		getView().getMainFrame().setVisible(true);
		getController().setPause(true);
		getView().getBrickFrame().getMainFrame().setVisible(true);
	}



	public static int getBrickCount() {
		return brickCount;
	}

	public static void setBrickCount(int brickCount) {
		InputController.brickCount = brickCount;
	}

	public int getSb() {
		return sb;
	}

	public void setSb(int sb) {
		this.sb = sb;
	}

	public int getHmb() {
		return hmb;
	}

	public void setHmb(int hmb) {
		this.hmb = hmb;
	}

	public int getWb() {
		return wb;
	}

	public void setWb(int wb) {
		this.wb = wb;
	}

	public int getMb() {
		return mb;
	}

	public void setMb(int mb) {
		this.mb = mb;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}


	public boolean isCantPlay() {
		return cantPlay;
	}


	public void setCanPlay(boolean cantPlay) {
		this.cantPlay = cantPlay;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}



	public void setCantPlay(boolean cantPlay) {
		this.cantPlay = cantPlay;
	}


	/**
	 * @return the BrickTypes
	 */

	public int getBrickType() {
		return brickType;
	}


	/**
	 * @param bt the BrickTypes to set
	 */
	public void setBrickType(int bt) {
		this.brickType = bt;
	}

	/**
	 * @return the player
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(String player) {
		this.player = player;
	}




}
