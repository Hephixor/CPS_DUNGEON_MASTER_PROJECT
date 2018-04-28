package impl;

import java.util.List;

import display.GameInterface;
import services.MapService;
import utils.Cell;
import utils.MapGenerator;
import utils.Node;
import utils.Pathfinder;

public class MapImpl implements MapService {
	int h;
	int w;
	Cell[][] cells;
	List<Node> path;
	public Node in;
	public Node out;
	
	@Override
	public int getHeight() {
		return h;
	}

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return cells[y][x];
	}

	@Override
	public void init(int w, int h) {
		cells = new Cell[h][w];
		this.h = cells.length;
		this.w = cells[0].length;
		this.path=null;
		MapGenerator mgen = new MapGenerator( w, h, ((w+h)), (w/4)+(h/4) );
		cells = mgen.getMap();
		//GameInterface gi = new GameInterface(cells);
		
		for(int x=0; x<cells[0].length; x++){
			for(int y=0; y<cells.length; y++){
				if(cells[y][x] == Cell.IN){
					in = new Node(x,y);
					//System.out.println("IN = ("+inx+","+iny+")");
				}
				if(cells[y][x] == Cell.OUT){
					out = new Node(x,y);
					//System.out.println("OUT = ("+outx+","+outy+")");
				}
			}
		}

		Pathfinder pf = new Pathfinder(cells,in.x,in.y,out.x,out.y);
		path = pf.path();
	}

	@Override
	public MapService openDoor(int x, int y) {
		return null;
	}

	@Override
	public MapService closeDoor(int x, int y) {
		return null;
	}

}
