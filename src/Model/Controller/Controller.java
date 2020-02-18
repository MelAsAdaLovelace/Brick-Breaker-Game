package Model.Controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;

import Model.Animator;
//import Model.BuildingModel;
import Model.Model;
import Model.Aliens.AlienFactory;
import Model.Bricks.BrickFactory;
import Utils.Constants;
import Utils.FileManager;
import View.View;



public class Controller {
	private View view;
	private Model model;

	private InputController inputController;
	private volatile boolean isRunning;
	private boolean isGameOver;
	private boolean isBuildingMode;
	private boolean isPause;	
	private String username;
	private static long time;

	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;
		setInputController(new InputController(model, this, view));
		setRunning(true);
		setPause(false);
		setGameOver(false);
		getView().getLayeredPanel().getMainPanel().getPlayerPanel().setPlayer(getModel().getPlayer());
	}


	public void showMenu() {
		view.getMainFrame().setVisible(true);
		view.getBrickFrame().getMainFrame().setVisible(true);
	}

	public void startGame() {
		model.loadGame(getUsername());
		startBB();
	}

	public void startBB() {
		view.getMainFrame().setVisible(true);
		isPause = true;
		while (!isGameOver()) {
			time =  System.currentTimeMillis() / 1000;
			while (isRunning()) {
				Timer timer = new Timer();
				Timer timer2 = new Timer();
				Animator.getInstance().sleep((int) Constants.L/20);
				if (isPause())
					setRunning(false);
				if (isGameOver())
					break;
				long lastTime = System.nanoTime();
				if (model.update()) {
					view.render(getModel().getGameObjectList(), getModel().getPlayer());
				}
				if(model.getPaddleFactory().isMagnetic()) {
					view.getBrickFrame().getMessage().setText("Magnet Paddle is collected, press M");
				}else {
					view.getBrickFrame().getMessage().setText("");
				}
				if(model.getAlienFactory().getRepairing().size() == 1) {
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							// TODO Auto-generated method stub

							model.repairBrick();
						}
					}, 0, 5000);
					timer.cancel();
				}
				if(model.getAlienFactory().getRepairingDrunk().size() >0) {
					timer2.schedule(new TimerTask() {
						@Override
						public void run() {
							// TODO Auto-generated method stub

							model.repairRow();
						}
					}, 0, 5000);
					timer2.cancel();
				}
			}
			if (isPause()) {
				view.getLayeredPanel().getMainPanel().getPlayerPanel().setPaused("Game is Paused.");
				setPause(false);

			}
		}
		endOfGame();
	}


	public void endOfGame() {
		getView().getLayeredPanel().getPausePanel().goodbye();
		Animator.sleep(2000);
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


	public boolean isRunning() {
		return isRunning;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}


	public InputController getInputController() {
		return inputController;
	}

	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}


	/**
	 * @return the isBuildingMode
	 */
	public boolean isBuildingMode() {
		return isBuildingMode;
	}


	/**
	 * @param isBuildingMode the isBuildingMode to set
	 */
	public void setBuildingMode(boolean isBuildingMode) {
		this.isBuildingMode = isBuildingMode;
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