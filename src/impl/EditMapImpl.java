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
