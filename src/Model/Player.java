package Model;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

import Model.PowerUps.PowerUp;
import Model.PowerUps.TimedPowerUp;
import Utils.Constants;


public class Player implements Serializable{
	private PropertyChangeSupport property;
	private int live;
	private long score;
	private ArrayList<TimedPowerUp> powerUpList;
	private long powerUpTime;
	private long whenPaused;


	public Player() {
		this.live = Constants.LIVE;
		this.score = 0;
		setPowerUpList(new ArrayList<TimedPowerUp>());
		setPowerUpTime(0);
	}

	public boolean dieOnce() {
		setLive(getLive() - 1);
		return getLive() == 0;
	}

	public void addScore(long sec) {
		if(sec == 0) {
			sec = 1;
		}
		setScore(getScore() + (300 / sec));
	}

	public void collectPowerUp(PowerUp powerUp) {
		if ((powerUp instanceof TimedPowerUp)) {
			setPowerUpTime(System.currentTimeMillis() + ((TimedPowerUp) powerUp).getDuration());
			getProperty().firePropertyChange(powerUp.getType(), false, true);
			((TimedPowerUp) powerUp).start();
		}
		getProperty().firePropertyChange(powerUp.getType(), false, true);

	}

	public boolean updatePowerUps() {
		if (!getPowerUpList().isEmpty()) {
			int i;
			for (i = 0; i < getPowerUpList().size(); i++) {
				TimedPowerUp powerUp = getPowerUpList().get(i);
				if (powerUp.deactivated()) {
					getProperty().firePropertyChange(powerUp.getType(), true, false);
					getPowerUpList().remove(i);
					resetPowerUps();
					i--;
				}
			}
			if (i != 0)
				return true;
		}
		return false;
	}

	public void resetPowerUps() {
		getPowerUpList().clear();
		setPowerUpTime(0);
	}

	public void pauseGame() {
		if (!getPowerUpList().isEmpty())
			for (PowerUp powerUp : getPowerUpList()) {
				if (powerUp instanceof TimedPowerUp) {
					((TimedPowerUp) powerUp).startPause();
					setWhenPaused(System.currentTimeMillis());
				}
			}
	}

	public void resumeGame() {
		if (!getPowerUpList().isEmpty()) {
			for (PowerUp powerUp : getPowerUpList()) {
				if (powerUp instanceof TimedPowerUp)
					((TimedPowerUp) powerUp).stopPause();
			}
			setPowerUpTime(getPowerUpTime() + System.currentTimeMillis() - getWhenPaused());
		}
		setWhenPaused(0);
	}

	public String getTimeAsString() {
		long time;
		if (getWhenPaused() == 0){
			time = getPowerUpTime() - System.currentTimeMillis();
		} else {
			time = getPowerUpTime() - getWhenPaused();
		}
		if (time < 0)
			return "";
		else
			return ("PowerUp is active for " + time / 1000 + "." + time / 100 + "seconds");
	}

	public int getLive() {
		return live;
	}


	public void setLive(int health) {
		this.live = health;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long l) {
		this.score = l;
	}

	public ArrayList<TimedPowerUp> getPowerUpList() {
		return powerUpList;
	}

	public void setPowerUpList(ArrayList<TimedPowerUp> powerUpList) {
		this.powerUpList = powerUpList;
	}

	public PropertyChangeSupport getProperty() {
		return property;
	}


	public void setProperty(PropertyChangeSupport property) {
		this.property = property;
	}

	public long getWhenPaused() {
		return whenPaused;
	}

	public void setWhenPaused(long whenPaused) {
		this.whenPaused = whenPaused;
	}

	public long getPowerUpTime() {
		return powerUpTime;
	}

	public void setPowerUpTime(long powerUpTime) {
		this.powerUpTime = powerUpTime;
	}
}