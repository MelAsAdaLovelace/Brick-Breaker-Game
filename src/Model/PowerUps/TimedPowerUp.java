package Model.PowerUps;

public class TimedPowerUp extends PowerUp{
	private int duration; 
	private long powerUpActivatonTime; // when powerUp is activated
	private long pauseStartTime; // when pause starts
	private long pauseTime; // time spent while pausing

	public TimedPowerUp(int duration, String name){
		super(name);
		setDuration(duration);
		setPauseTime(0);
		setPauseStartTime(0);
		setPowerUpActivatonTime(0);
	}
	
	
	public void start(){
		setPowerUpActivatonTime(System.currentTimeMillis());
	}

	public void startPause()
	{
		setPauseStartTime(System.currentTimeMillis());
	}

	public void stopPause()
	{
		setPauseTime(getPauseTime() + System.currentTimeMillis() - getPauseStartTime());
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int time)
	{
		this.duration = time;
	}

	public long getPowerUpActivatonTime() {
		return powerUpActivatonTime;
	}

	public void setPowerUpActivatonTime(long powerUpActivatonTime) {
		this.powerUpActivatonTime = powerUpActivatonTime;
	}

	public long getPauseStartTime(){
		return pauseStartTime;
	}

	public void setPauseStartTime(long pauseStartTime){
		this.pauseStartTime = pauseStartTime;
	}

	public long getPauseTime(){
		return pauseTime;
	}

	public void setPauseTime(long pauseTime){
		this.pauseTime = pauseTime;
	}


	public boolean deactivated(){
		return (System.currentTimeMillis() - getPowerUpActivatonTime() - getPauseTime() > duration);
	}


}
