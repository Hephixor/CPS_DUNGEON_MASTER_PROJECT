package impl;

import services.EnvironmentService;
import services.MapService;
import services.MobService;
import utils.Cell;

public class EnvironmentImpl implements EnvironmentService{
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

	
	public void init(int w, int h) {
		this.h=h;
		this.w=w;
		//this.cells = ;
		//Initialiser les cases
		
	}

	@Override
	public MapService openDoor(int x, int y) {
		return null;
	}

	@Override
	public MobService getCellContent(int x, int y) {
		return null;
	}

	@Override
	public EnvironmentService closeDoor(int x, int y) {
		return null;
	}


}
