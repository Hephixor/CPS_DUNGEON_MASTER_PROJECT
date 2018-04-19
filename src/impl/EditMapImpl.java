package impl;

import services.EditMapService;
import services.MapService;
import utils.Cell;
import utils.Pathfinder;

public class EditMapImpl implements EditMapService{

	int h;
	int w;
	Cell[][] cells;
	
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
		return cells[x][y];
	}

	@Override
	public void init(int w, int h) {
		this.w=w;
		this.h=h;
		
		//Mettre des cells
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
		Pathfinder pf = new Pathfinder();
		return pf.pathExists(cells, 3 ,4 ,0 ,1);
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public EditMapService setNature(int x, int y, Cell c) {
		cells[x][y]=c;
		return this;
	}

}
