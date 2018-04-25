package impl;

import services.MapService;
import utils.Cell;

public class MapImpl implements MapService {
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
		return cells[y][x];
	}

	@Override
	public void init(int w, int h) {
		cells = new Cell[h][w];
		this.h = cells.length;
		this.w = cells[0].length;
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
