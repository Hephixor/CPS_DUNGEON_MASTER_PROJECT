package impl;

import java.util.List;

import services.EditMapService;
import services.MapService;
import utils.Cell;
import utils.Node;
import utils.Pathfinder;
import utils.MapGenerator;

public class EditMapImpl implements EditMapService{
	int c = 0;
	int h;
	int w;
	Cell[][] cells;
	List<Node> path;
	
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
		this.w=w;
		this.h=h;
		this.path=null;
		MapGenerator mgen = new MapGenerator(w,h,200,10);
		cells = mgen.getMap();
		int inx = 0;
		int iny = 0;
		int outx = 0;
		int outy = 0;

		for(int x=0; x<cells[0].length; x++){
			for(int y=0; y<cells.length; y++){
				if(cells[y][x] == Cell.IN){
					inx = x;
					iny = y;
					System.out.println("IN = ("+inx+","+iny+")");
				}
				if(cells[y][x] == Cell.OUT){
					outx = x;
					outy = y;
					System.out.println("OUT = ("+outx+","+outy+")");
				}
			}
		}

		Pathfinder pf = new Pathfinder(cells,inx,iny,outx,outy);
		path = pf.path();
		
	}

	@Override
	public MapService openDoor(int x, int y) {
		return this;
	}

	@Override
	public MapService closeDoor(int x, int y) {
		return this;
	}

	@Override
	public boolean isReachable(int px, int py, int ox, int oy) {
		Pathfinder pf = new Pathfinder(cells,px,py,ox,oy);
		return pf.hasPath();
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public EditMapService setNature(int x, int y, Cell c) {
		cells[y][x]=c;
		return this;
	}

	@Override
	public List<Node> getPath() {
		return path;
	}

}
