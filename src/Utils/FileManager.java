package Utils;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;



import Model.Ball;
import Model.GameObject;
import Model.MovingObjects;
import Model.Paddle;
import Model.Player;
import Model.Aliens.Alien;
import Model.Bricks.Brick;


public abstract class FileManager implements Serializable{
	static Paddle paddle;
	static Ball ball;
	static int score;
	static Brick[][] brickMap = new Brick[Constants.rows][Constants.cols];

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int readMaxDesign(){
		int Design = 0;
		File f;
		do{
			Design++;
			String s = "Design_" + Design + ".ser";
			f = new File(s);
		} while (f.exists() && !f.isDirectory());
		return Design - 1;
	}

	public static ArrayList<Brick> loadDesign(String username){
		ArrayList<Brick> brickList = new ArrayList<Brick>();
		File f = new File("Design_" + username + ".ser");
		if (f.exists() && !f.isDirectory()){
			try{
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				brickList = (ArrayList<Brick>) ois.readObject();

				ois.close();
			}
			catch (FileNotFoundException e){
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return brickList;
		}
		else
			return null;
	}
	
	public static Alien loadAlien(String username){
		Alien aliens = null;
		File f = new File("Alien_" + username + ".ser");
		if (f.exists() && !f.isDirectory()){
			try{
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				aliens = (Alien) ois.readObject();

				ois.close();
			}
			catch (FileNotFoundException e){
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return aliens;
		}
		else
			return null;
	}

	public static long  loadScore(Player player, String username) {
		long score = 0;
		File f = new File("Score_" + username + ".ser");
		if (f.exists() && !f.isDirectory()){
			try{
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				score = ((long) ois.readObject());
				ois.close();
			}
			catch (FileNotFoundException e){
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}

		return score;
	} 	
	
	public static void  saveScore(Player player, String username) {
		long score = 0;

		try {
			ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Score_" +username+ ".ser"));
			ous.writeObject(player.getScore());
			ous.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	public static void  saveAliens(Alien alien, String username) {
		try {
			ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Alien_" +username+ ".ser"));
			ous.writeObject(alien);
			ous.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void saveDesign(ArrayList<Brick> bricks,  String username){
		try{
			ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Design_" + username + ".ser"));
			ous.writeObject(bricks);

			ous.close();

		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}


}
