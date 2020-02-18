//package Model;
//
//import java.awt.Point;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//
//import Model.Bricks.Brick;
//import Model.Bricks.BrickFactory;
//import Model.Bricks.BrickTypes;
//import Model.Controller.Controller;
//import Model.Controller.InputController;
//import Utils.Constants;
//import Utils.FileManager;
//
//
//public class BuildingModel{
//	private int maxDesign;
//	private int hitPoint;
//	private BrickFactory brickFactory;
//	private ArrayList<Brick> brickList = new ArrayList<Brick>();
//
//	int brickWidth = Constants.BRICK_WIDTH;
//	int brickHeight =  Constants.BRICK_HEIGHT;
//	int cols = (Constants.GAMEBOARD_WIDTH - 2* Constants.WALL_WIDTH - Constants.BRICK_WIDTH) / brickWidth;
//	int rows = (int) ((Constants.GAMEBOARD_HEIGHT - Constants.WALL_WIDTH) / brickHeight );
//
//
//	public BuildingModel(BrickFactory brickFactory){
//		setBrickFactory(brickFactory);
//		setMaxDesign(FileManager.readMaxDesign());
//		setHitPoint(1);
//	}
//
//	public Point mouseClickPoint(MouseEvent me){
//		int wall = Constants.WALL_WIDTH;
//		int x_mouse = me.getX();
//		int y_mouse = me.getY();
//		Point p = null;
//		if (!(x_mouse <= wall || x_mouse >= Constants.GAMEBOARD_WIDTH - wall|| wall >= y_mouse
//				|| y_mouse >= wall + (rows * brickHeight) || y_mouse >= Constants.PLAYABLE)){
//			int col = (x_mouse - wall) / brickWidth;
//			int row = (y_mouse - wall) / brickHeight;
//			int x = col * brickWidth + wall;
//			int y = row * brickHeight + wall;
//			p = new Point(x,y);
//		}
//		return p;
//	}
//
//	public Brick createBrick( Point coordinates){
//		return brickFactory.createBrick(BrickTypes.SIMPLE, coordinates.x, coordinates.y);
//	}
//
//	public boolean addBrick(MouseEvent me){
//		Brick b = createBrick(mouseClickPoint(me));
//		if (!getBrickList().contains(b)){
//			getBrickList().add(b);
//			return true;
//		}
//		return false;
//	}
//
//	public boolean removeBrick(MouseEvent me){
//		Brick b = createBrick(mouseClickPoint(me));
//		if (b!=null && getBrickList().contains(b)){
//			getBrickList().remove(b);
//			return true;
//		}
//		return false;
//
//	}
//
//
//	public void removeAll(){
//		getBrickList().subList(3, getBrickList().size()).clear();
//	}
//
//
//	public void putBricks(int sbcount, int wbcount){
//		removeAll();
//		int d = Constants.WALL_WIDTH;
//		double arena_width = Constants.GAMEBOARD_WIDTH - 2*d;
//		int maxBrickPerRow =  (int) Math.ceil(arena_width / brickWidth);
//		double c = Math.floor(arena_width / brickWidth) ;
//		int r =  (int) Math.ceil(sbcount /maxBrickPerRow);
//		int t  = 0;
//		int x = 0;
//		int y = d;
//		int j = 0;
//		for (int row = 0; row < 100 ; row++) {
//			for (int col = 0; col <c ; col++){
//				t++;
//				if(t > sbcount ) 
//					break;
//				x = col * brickWidth + d;
//				y = row * brickHeight + d;
//				getBrickList().add(createBrick(new Point(x, y)));
//			}
//
//		}
//	}
//
//
//	public boolean saveDesign(int Design){
//		FileManager.saveDesign(getBrickList(), Design);
//		return false;
//	}
//
//	public void loadDesign(int selectedIndex){
//		if (selectedIndex == getMaxDesign()){
//			removeAll();
//			return;
//		}
//	}
//
//	public ArrayList<Brick> getBrickList(){
//		return brickList;
//	}
//
//	public void setBrickList(ArrayList<Brick> brickList){
//		this.brickList = brickList;
//	}
//
//
//	public void setBrickFactory(BrickFactory brickFactory) {
//		this.brickFactory = brickFactory;
//	}
//
//	public int getMaxDesign(){
//		return maxDesign;
//	}
//
//	public void setMaxDesign(int maxDesign){
//		this.maxDesign = maxDesign;
//	}
//
//	public int getHitPoint(){
//		return hitPoint;
//	}
//
//	public void setHitPoint(int hitPoint){
//		this.hitPoint = hitPoint;
//	}
//
//
//}
