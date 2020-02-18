package Model;
import Model.Player;

public class PlayerFactory {
	
	private static PlayerFactory instance;
	
	private PlayerFactory() {
		
	}
	
	public static PlayerFactory getInstance() {
		if(instance == null) {
			instance = new PlayerFactory();
		}
		return instance;
	}
	
	public Player createPlayer(){
		Player p = new Player();
		return p;
	}

}
