package Model;

public class Animator extends Thread{
	private static Animator instance;

	private Animator() {

	}
	
	public static Animator getInstance() {
		if(instance == null) {
			instance = new Animator();
		}
		return instance;
	}
	
	public static void sleep(int ms){
		try{
			Thread.sleep(ms);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
