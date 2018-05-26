package display;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import contracts.CowContract;
import contracts.EntityContract;
import contracts.PlayerContract;
import impl.DungeonMaster;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import utils.Cell;
import utils.Command;
import utils.Node;

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
		step();
		//	System.out.println("strafeL");
	}

	public void strafeRight() {
		dm.setPlayerCommand(Command.RR);
		step();
		//	System.out.println("strafeR");
	}

	public void forward() {
		dm.setPlayerCommand(Command.FF);
		step();
		//	System.out.println("forward");
	}

	public void right() {
		dm.setPlayerCommand(Command.TR);
		step();
		//	System.out.println("right");
	}

	public void left() {
		dm.setPlayerCommand(Command.TL);
		step();
		//	System.out.println("left");
	}

	public void backward() {
		dm.setPlayerCommand(Command.BB);
		step();
		//	System.out.println("backward");
	}

	public void hit() {
		dm.setPlayerCommand(Command.HIT);
		step();
		//	System.out.println("hit");
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

				Node anf = new Node(-1, 3);
				Node bnf = new Node(0, 3);
				Node cnf = new Node(1, 3);
				Node dnf = new Node(-1, 2);
				Node enf = new Node(0, 2);
				Node fnf = new Node(1, 2);
				Node gnf = new Node(-1, 1);
				Node hnf = new Node(0, 1);
				Node inf = new Node(1, 1);
				Node jnf = new Node(-1, 0);
				Node knf = new Node(0, 0);
				Node lnf = new Node(1, 0);
				Node mnf = new Node(-1, -1);
				Node nnf = new Node(0, -1);
				Node onf = new Node(1, -1);

				//Update graphics
				img = new File("src/resources/images/"+getFilePath(ac,an,anf));
				i = new Image(img.toURI().toString());
				this.a.setImage(i);

				img = new File("src/resources/images/"+getFilePath(bc,bn,bnf));
				i = new Image(img.toURI().toString());
				this.b.setImage(i);

				img = new File("src/resources/images/"+getFilePath(cc,cn,cnf));
				i = new Image(img.toURI().toString());
				this.c.setImage(i);

				img = new File("src/resources/images/"+getFilePath(dc,dn,dnf));
				i = new Image(img.toURI().toString());
				this.d.setImage(i);

				img = new File("src/resources/images/"+getFilePath(ec,en,enf));
				i = new Image(img.toURI().toString());
				this.e.setImage(i);

				img = new File("src/resources/images/"+getFilePath(fc,fn,fnf));
				i = new Image(img.toURI().toString());
				this.f.setImage(i);

				img = new File("src/resources/images/"+getFilePath(gc,gn,gnf));
				i = new Image(img.toURI().toString());
				this.g.setImage(i);

				img = new File("src/resources/images/"+getFilePath(hc,hn,hnf));
				i = new Image(img.toURI().toString());
				this.h.setImage(i);

				img = new File("src/resources/images/"+getFilePath(ic,in,inf));
				i = new Image(img.toURI().toString());
				this.i.setImage(i);

				img = new File("src/resources/images/"+getFilePath(jc,jn,jnf));
				i = new Image(img.toURI().toString());
				this.j.setImage(i);

				img = new File("src/resources/images/"+getFilePath(kc,kn,knf));
				i = new Image(img.toURI().toString());
				this.k.setImage(i);

				img = new File("src/resources/images/"+getFilePath(lc,ln,lnf));
				i = new Image(img.toURI().toString());
				this.l.setImage(i);

				img = new File("src/resources/images/"+getFilePath(mc,mn,mnf));
				i = new Image(img.toURI().toString());
				this.m.setImage(i);

				img = new File("src/resources/images/"+getFilePath(nc,nn,nnf));
				i = new Image(img.toURI().toString());
				this.n.setImage(i);

				img = new File("src/resources/images/"+getFilePath(oc,on,onf));
				i = new Image(img.toURI().toString());
				this.o.setImage(i);

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
	private void handleKeyPressed(KeyEvent ke){
		switch(ke.getCode()) {
		case Z: forward();break;
		case S: backward();break;
		case Q: strafeLeft();break;
		case D: strafeRight();break;
		case A: left();break;
		case E: right();break;
		case ENTER: hit();break;
		//more controls..
		default:break;
		}
	}

	@FXML
	public void closeApplication() {
		Platform.exit();
		System.exit(0);
	}

	private String getFilePath(Cell c, Node n, Node fov){
		String fp="";
		if(c == null) return "black.png";

		if(dm.getPlayer().getViewable(fov.x,fov.y)) {
			switch(c){
			case WLL:
				fp = "wall.png";
				break;
			case EMP:
				if(dm.getEnv().getCellContent(n.x,n.y) == null) {
					fp = "ground.png";
				}
				else if(dm.getEnv().getCellContent(n.x,n.y) instanceof CowContract) {
					fp = "cow.png";

				}
				else if(dm.getEnv().getCellContent(n.x,n.y) instanceof PlayerContract) {
					switch(dm.getEnv().getCellContent(n.x,n.y).getFace()) {
					case N:
						fp="player_n.png";
						break;
					case S:
						fp="player_s.png";
						break;
					case E:
						fp="player_e.png";
						break;
					case W:
						fp="player_w.png";
						break;
					}

				}
				else if(dm.getEnv().getCellContent(n.x,n.y) instanceof EntityContract) {
					switch(dm.getEnv().getCellContent(n.x,n.y).getFace()) {
					case N:
						fp="mob_w.png";
						break;
					case S:
						fp="mob_e.png";
						break;
					case E:
						fp="mob_e.png";
						break;
					case W:
						fp="mob_w.png";
						break;
					}

				}

				break;
			case IN:

				if(dm.getEnv().getCellContent(n.x,n.y) instanceof PlayerContract) {
					switch(dm.getEnv().getCellContent(n.x,n.y).getFace()) {
					case N:
						fp="in_player_n.png";
						break;
					case S:
						fp="in_player_s.png";
						break;
					case E:
						fp="in_player_e.png";
						break;
					case W:
						fp="in_player_w.png";
						break;
					}

				}
				else if(dm.getEnv().getCellContent(n.x,n.y) instanceof EntityContract) {
					switch(dm.getEnv().getCellContent(n.x,n.y).getFace()) {
					case N:
						fp="in_mob_e.png";
						break;
					case S:
						fp="in_mob_w.png";
						break;
					case E:
						fp="in_mob_e.png";
						break;
					case W:
						fp="in_mob_w.png";
						break;
					}

				}
				else if(dm.getEnv().getCellContent(n.x,n.y) instanceof CowContract) {
					fp="in_cow.png";
				}
				else{
					fp = "in.png";
				}
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

		else {
			return "black.png";
		}


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
