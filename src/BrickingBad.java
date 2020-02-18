
import Model.Model;
import Model.Controller.Controller;
import View.View;

public class BrickingBad{
	public static void main(String[] args){
		View view = new View();
		Model model = new Model();
		Controller bb = new Controller(view, model);
		
		view.setController(bb);
		model.setController(bb);
		
		
		
		bb.showMenu();
	}

}
