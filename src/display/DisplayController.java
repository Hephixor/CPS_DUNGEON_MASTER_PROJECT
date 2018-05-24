package display;

import impl.DungeonMaster;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import contracts.CowContract;
import contracts.EntityContract;
import contracts.PlayerContract;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import utils.*;

public class DisplayController implements javafx.fxml.Initializable{
	@FXML private BorderPane borderPane;
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

	private double xOffset;
	private double yOffset;
	DungeonMaster dm;

	public DisplayController() {
		//Nothing to do
	}

	public void strafeLeft() {
		dm.setPlayerCommand(Command.LL);
		System.out.println("strafeL");
	}

	public void strafeRight() {
		dm.setPlayerCommand(Command.RR);
		System.out.println("strafeR");
	}

	public void forward() {
		dm.setPlayerCommand(Command.FF);
		System.out.println("forward");
	}

	public void right() {
		dm.setPlayerCommand(Command.TR);
		System.out.println("right");
	}

	public void left() {
		dm.setPlayerCommand(Command.TL);
		System.out.println("left");
	}

	public void backward() {
		dm.setPlayerCommand(Command.BB);
		System.out.println("backward");
	}

	public void hit() {
		System.out.println("hit");
	}

	public void inventory() {
		System.out.println("step ---------------------");
	
		step();

	}

	public Cell getCellNature(int x, int y) {
		return dm.getCellNature(x, y);
	}

	public Node getPlayerPos() {
		return dm.getPlayerPos();
	}

	public void updateMap() {

		Platform.runLater(() -> {
			Node pp = getPlayerPos();
			File img;
			Image i;
			try {
				//Retrieve cells nature in fov 
				Cell ac = dm.getCellNature(pp.x-1, pp.y+3);
				Cell bc = dm.getCellNature(pp.x, pp.y+3);
				Cell cc = dm.getCellNature(pp.x+1, pp.y+3);
				Cell dc = dm.getCellNature(pp.x-1, pp.y+2);
				Cell ec = dm.getCellNature(pp.x, pp.y+2);
				Cell fc = dm.getCellNature(pp.x+1, pp.y+2);
				Cell gc = dm.getCellNature(pp.x-1, pp.y+1);
				Cell hc = dm.getCellNature(pp.x, pp.y+1);
				Cell ic = dm.getCellNature(pp.x+1, pp.y+1);
				Cell jc = dm.getCellNature(pp.x-1, pp.y);
				Cell kc = dm.getCellNature(pp.x, pp.y);
				Cell lc = dm.getCellNature(pp.x+1, pp.y);
				Cell mc = dm.getCellNature(pp.x-1, pp.y-1);
				Cell nc = dm.getCellNature(pp.x, pp.y-1);
				Cell oc = dm.getCellNature(pp.x+1, pp.y-1);
				
				Node an = new Node(pp.x-1, pp.y+3);
				Node bn = new Node(pp.x, pp.y+3);
				Node cn = new Node(pp.x+1, pp.y+3);
				Node dn = new Node(pp.x-1, pp.y+2);
				Node en = new Node(pp.x, pp.y+2);
				Node fn = new Node(pp.x+1, pp.y+2);
				Node gn = new Node(pp.x-1, pp.y+1);
				Node hn = new Node(pp.x, pp.y+1);
				Node in = new Node(pp.x+1, pp.y+1);
				Node jn = new Node(pp.x-1, pp.y);
				Node kn = new Node(pp.x, pp.y);
				Node ln = new Node(pp.x+1, pp.y);
				Node mn = new Node(pp.x-1, pp.y-1);
				Node nn = new Node(pp.x, pp.y-1);
				Node on = new Node(pp.x+1, pp.y-1);
				
				
				//Update graphics
				img = new File("src/resources/images/"+getFilePath(ac,an));
				i = new Image(img.toURI().toString());
				this.a.setImage(i);

				img = new File("src/resources/images/"+getFilePath(bc,bn));
				i = new Image(img.toURI().toString());
				this.b.setImage(i);

				img = new File("src/resources/images/"+getFilePath(cc,cn));
				i = new Image(img.toURI().toString());
				this.c.setImage(i);

				img = new File("src/resources/images/"+getFilePath(dc,dn));
				i = new Image(img.toURI().toString());
				this.d.setImage(i);

				img = new File("src/resources/images/"+getFilePath(ec,en));
				i = new Image(img.toURI().toString());
				this.e.setImage(i);

				img = new File("src/resources/images/"+getFilePath(fc,fn));
				i = new Image(img.toURI().toString());
				this.f.setImage(i);

				img = new File("src/resources/images/"+getFilePath(gc,gn));
				i = new Image(img.toURI().toString());
				this.g.setImage(i);

				img = new File("src/resources/images/"+getFilePath(hc,hn));
				i = new Image(img.toURI().toString());
				this.h.setImage(i);

				img = new File("src/resources/images/"+getFilePath(ic,in));
				i = new Image(img.toURI().toString());
				this.i.setImage(i);

				img = new File("src/resources/images/"+getFilePath(jc,jn));
				i = new Image(img.toURI().toString());
				this.j.setImage(i);

				img = new File("src/resources/images/"+getFilePath(kc,kn));
				i = new Image(img.toURI().toString());
				this.k.setImage(i);

				img = new File("src/resources/images/"+getFilePath(lc,ln));
				i = new Image(img.toURI().toString());
				this.l.setImage(i);

				img = new File("src/resources/images/"+getFilePath(mc,mn));
				i = new Image(img.toURI().toString());
				this.m.setImage(i);

				img = new File("src/resources/images/"+getFilePath(nc,nn));
				i = new Image(img.toURI().toString());
				this.n.setImage(i);

				img = new File("src/resources/images/"+getFilePath(oc,on));
				i = new Image(img.toURI().toString());
				this.o.setImage(i);

				//Build fov matrix
				//			view[0][0] = ac;
				//			view[0][1] = bc;
				//			view[0][2] = cc;
				//			view[1][0] = dc;
				//			view[1][1] = ec;
				//			view[1][2] = fc;
				//			view[2][0] = gc;
				//			view[2][1] = hc;
				//			view[2][2] = ic;
				//			view[3][0] = jc;
				//			view[3][1] = kc;
				//			view[3][2] = lc;
				//			view[4][0] = mc;
				//			view[4][1] = nc;
				//			view[4][2] = oc;
				//	GameInterface gi = new GameInterface(view);	

				//			//Naive display
				//			System.out.print(ac.toString()+" ");
				//			System.out.print(bc.toString()+" ");
				//			System.out.println(cc.toString()+" ");
				//			System.out.print(dc.toString()+" ");
				//			System.out.print(ec.toString()+" ");
				//			System.out.println(fc.toString()+" ");
				//			System.out.print(gc.toString()+" ");
				//			System.out.print(hc.toString()+" ");
				//			System.out.println(ic.toString()+" ");
				//			System.out.print(jc.toString()+" ");
				//			System.out.print(kc.toString()+" ");
				//			System.out.println(lc.toString()+" ");
				//			System.out.print(mc.toString()+" ");
				//			System.out.print(nc.toString()+" ");
				//			System.out.println(oc.toString()+" ");



			}
			catch(ArrayIndexOutOfBoundsException e) {

			}
		});
	}

	private void step(){
		dm.step();
		updateMap();

	}


	@FXML
	public void closeApplication() {
		Platform.exit();
		System.exit(0);
	}

	private String getFilePath(Cell c, Node n){
		String fp="";
		switch(c){
		case WLL:
			fp = "wall.png";
			break;
		case EMP:
			if(dm.getEnv().getCellContent(n.x,n.y) == null) {
				fp = "ground.png";
			}
			else if(dm.getEnv().getCellContent(n.x,n.y) instanceof CowContract) {
				fp = "dice.png";

			}
			else if(dm.getEnv().getCellContent(n.x,n.y) instanceof PlayerContract) {
				fp = "axe.gif";

			}
			else if(dm.getEnv().getCellContent(n.x,n.y) instanceof EntityContract) {
				fp = "close.png";

			}
			else{
				//Impossible
			}
			break;
		case IN:
			fp = "in.png";
			break;
		case OUT:
			fp = "out.png";
			break;
		case DNC:
			fp = "dnc.png";
			break;
		case DWC:
			fp = "dwc.png";
			break;
		default:
			fp = "dice.png";
			break;
		}
		return fp;

	}

	public void initialize(URL location, ResourceBundle resources) {

		/* Drag and Drop */
		borderPane.setOnMousePressed(event -> {
			xOffset = MainLauncher.getPrimaryStage().getX() - event.getScreenX();
			yOffset = MainLauncher.getPrimaryStage().getY() - event.getScreenY();
			borderPane.setCursor(Cursor.CLOSED_HAND);
		});

		borderPane.setOnMouseDragged(event -> {
			MainLauncher.getPrimaryStage().setX(event.getScreenX() + xOffset);
			MainLauncher.getPrimaryStage().setY(event.getScreenY() + yOffset);

		});

		borderPane.setOnMouseReleased(event -> {
			borderPane.setCursor(Cursor.DEFAULT);
		});

		dm = new DungeonMaster();
		dm.init();
		updateMap();


	}


}
