package impl;

import java.util.List;

import services.EditMapService;
import utils.Cell;
import utils.Node;
import utils.Pathfinder;

public class EditMapImpl extends MapImpl implements EditMapService{
	//List<Node> path;
	
	@Override
	public void init(int w, int h) {
		super.init(w,h);
//		this.path=null;
//		MapGenerator mgen = new MapGenerator(w,h,150,20);
//		cells = mgen.getMap();
//		
//		for(int x=0; x<cells[0].length; x++){
//			for(int y=0; y<cells.length; y++){
//				if(cells[y][x] == Cell.IN){
//					in.x = x;
//					in.y = y;
//					//System.out.println("IN = ("+inx+","+iny+")");
//				}
//				if(cells[y][x] == Cell.OUT){
//					out.x = x;
//					out.y = y;
//					//System.out.println("OUT = ("+outx+","+outy+")");
//				}
//			}
//		}
//
//		Pathfinder pf = new Pathfinder(cells,in.x,in.y,out.x,out.y);
//		path = pf.path();
		
	}

	@Override
	public void openDoor(int x, int y) {
		return;
	}

	@Override
	public void closeDoor(int x, int y) {
		return;
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
	
	public Node getIn(){
		return in;
	}
	
	public Node getOut(){
		return out;
	}

}
