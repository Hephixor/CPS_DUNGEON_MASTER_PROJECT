package display;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import utils.Cell;

public class GameInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cell[][] map;
	
	/** The list of tile images to render the map */
	private Image[] tiles = new Image[6];
	/** The offscreen buffer used for rendering in the wonder world of Java 2D */
	private Image buffer;
	
	public static final int WLL = 0;
	/** Indicate water terrain at a given location */
	public static final int EMP = 1;
	/** Indicate trees terrain at a given location */
	public static final int DNC = 2;
	/** Indicate a plane is at a given location */
	public static final int DWC = 3;
	/** Indicate a boat is at a given location */
	public static final int IN = 4;
	/** Indicate a tank is at a given location */
	public static final int OUT = 5;
	
	
	public GameInterface(Cell[][] map) {
		super("Dungeon Master");
		this.map=map;
		try {
			tiles[WLL] = ImageIO.read(getResource("src/resources/images/wall.png"));
			tiles[EMP] = ImageIO.read(getResource("src/resources/images/ground.png"));
			tiles[DNC] = ImageIO.read(getResource("src/resources/images/dnc.png"));
			tiles[DWC] = ImageIO.read(getResource("src/resources/images/dwc.png"));
			tiles[IN] = ImageIO.read(getResource("src/resources/images/in.png"));
			tiles[OUT] = ImageIO.read(getResource("src/resources/images/out.png"));
		} catch (IOException e) {
			System.err.println("Failed to load resources: "+e.getMessage());
			System.exit(0);
		}
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setSize(map[0].length*32,map.length*32);
		setResizable(true);
		setVisible(true);
	}
	
	/**
	 * Load a resource based on a file reference
	 * 
	 * @param ref The reference to the file to load
	 * @return The stream loaded from either the classpath or file system
	 * @throws IOException Indicates a failure to read the resource
	 */
	private InputStream getResource(String ref) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref);
		if (in != null) {
			return in;
		}
		
		return new FileInputStream(ref);
	}


	 
	public void paint(Graphics graphics) {	
		// create an offscreen buffer to render the map

		if (buffer == null) {
			buffer = new BufferedImage(map[0].length*32, map.length*32, BufferedImage.TYPE_INT_ARGB);			
		}
		Graphics g = buffer.getGraphics();
		
		g.clearRect(0,0,map[0].length*32,map.length*32);
		g.translate(0,0);
		
		// cycle through the tiles in the map drawing the appropriate

		// image for the terrain and units where appropriate
		int currCellType;
		int heigth = map.length;
		int width = map[0].length;
		for (int y=0;y<heigth;y++) {
			for (int x=width-1;x>=0;x--) {
				switch(map[y][x]) {
				case WLL:
					currCellType = WLL;
					break;
				case EMP:
					currCellType = EMP;
					break;
				case DNC:
					currCellType = DNC;
					break;
				case DWC:
					currCellType = DWC;
					break;
				case IN:
					currCellType = IN;
					break;
				case OUT:
					currCellType = OUT;
					break;	
				default:
					currCellType = WLL; 
					break;
				}
				g.drawImage(tiles[currCellType],x*32,y*32,null);
			}
		}

		// finally draw the buffer to the real graphics context in one

		// atomic action

		graphics.drawImage(buffer, 0, 0, null);
	}
	

}