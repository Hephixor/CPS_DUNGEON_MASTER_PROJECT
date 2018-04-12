package impl;

import services.EditMapService;
import services.MapService;
import utils.Cell;

public class EditMapImpl implements EditMapService{

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(int w, int h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapService openDoor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapService closeDoor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReachable(int px, int py, int ox, int oy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EditMapService setNature(int x, int y, Cell c) {
		// TODO Auto-generated method stub
		return null;
	}

}
