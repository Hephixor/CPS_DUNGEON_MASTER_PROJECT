package display;

import impl.DungeonMaster;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import utils.Cell;
import utils.Node;

public class DisplayController{
	@FXML private VBox fxscoresfinal;
	@FXML private ImageView a;
	@FXML private ImageView b;
	@FXML private ImageView c;
	@FXML private ImageView d;
	@FXML private ImageView e;
	@FXML private ImageView f;
	@FXML private ImageView g;
	@FXML private ImageView h;
	@FXML private ImageView i;
	@FXML private ImageView j;
	@FXML private ImageView k;
	@FXML private ImageView l;
	@FXML private ImageView m;
	@FXML private ImageView n;
	@FXML private ImageView o;
	
	DungeonMaster dm = new DungeonMaster();

	public DisplayController() {}
	
	public void strafeLeft() {
		System.out.println("strafeL");
	}
	
	public void strafeRight() {
		System.out.println("strafeR");
	}
	
	public void forward() {
		System.out.println("forward");
	}
	
	public void right() {
		System.out.println("right");
	}
	
	public void left() {
		System.out.println("left");
	}
	
	public void backward() {
		System.out.println("backward");
	}
	
	public void hit() {
		System.out.println("hit");
	}
	
	public void inventory() {
		System.out.println("inventory");
	}
	
	public Cell getCellNature(int x, int y) {
		return dm.getCellNature(x, y);
	}
	
	public Node getPlayerPos() {
		return dm.getPlayerPos();
	}
	
	public void updateMap() {
		Node pp = getPlayerPos();
		try {
		Cell m = dm.getCellNature(pp.x-1, pp.y+3);
		Cell n = dm.getCellNature(pp.x, pp.y+3);
		Cell o = dm.getCellNature(pp.x+1, pp.y+3);
		Cell j = dm.getCellNature(pp.x-1, pp.y+2);
		Cell k = dm.getCellNature(pp.x, pp.y+2);
		Cell l = dm.getCellNature(pp.x+1, pp.y+2);
		Cell g = dm.getCellNature(pp.x-1, pp.y+1);
		Cell h = dm.getCellNature(pp.x, pp.y+1);
		Cell i = dm.getCellNature(pp.x+1, pp.y+1);
		Cell d = dm.getCellNature(pp.x-1, pp.y);
		Cell e = dm.getCellNature(pp.x, pp.y);
		Cell f = dm.getCellNature(pp.x+1, pp.y);
		Cell a = dm.getCellNature(pp.x-1, pp.y-1);
		Cell b = dm.getCellNature(pp.x, pp.y-1);
		Cell c = dm.getCellNature(pp.x+1, pp.y-1);
		Cell[][] view = new Cell[5][3];
		//Mauvais sens
//		view[0][0] = a;
//		view[0][1] = b;
//		view[0][2] = c;
//		view[1][0] = d;
//		view[1][1] = e;
//		view[1][2] = f;
//		view[2][0] = g;
//		view[2][1] = h;
//		view[2][2] = i;
//		view[3][0] = j;
//		view[3][1] = k;
//		view[3][2] = l;
//		view[4][0] = m;
//		view[4][1] = n;
//		view[4][2] = o;
	//	GameInterface gi = new GameInterface(view);	
		
		
		
		System.out.print(m.toString()+" ");
		System.out.print(n.toString()+" ");
		System.out.println(o.toString()+" ");
		System.out.print(j.toString()+" ");
		System.out.print(k.toString()+" ");
		System.out.println(l.toString()+" ");
		System.out.print(g.toString()+" ");
		System.out.print(h.toString()+" ");
		System.out.println(i.toString()+" ");
		System.out.print(d.toString()+" ");
		System.out.print(e.toString()+" ");
		System.out.println(f.toString()+" ");
		System.out.print(a.toString()+" ");
		System.out.print(b.toString()+" ");
		System.out.println(c.toString()+" ");
		
		
		
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
		
		
		
		
		
		Platform.runLater(() -> {
		
			
			
		});
	}
	
	public void init() {
		dm.init();
		Node start = getPlayerPos();
		System.out.println("Player start x:"+start.x+" y:"+start.y);
		updateMap();
	}
	
	@FXML
	public void closeApplication() {
//		dm.test();
		Platform.exit();
		System.exit(0);
	}


}
