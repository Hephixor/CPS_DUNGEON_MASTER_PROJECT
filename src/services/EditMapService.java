package services;

import utils.Cell;

public interface EditMapService extends MapService{
	/* Observators */
	/**
	 * pre isReachable(M,x1,y1,x2,y2) requires CellNature(M,x1,y1) 6 = WLL
	 *	and CellNature(M,x2,y2) 6 = WLL
	 */
	public boolean isReachable(int px, int py, int ox, int oy);
	
	public boolean isReady();
	
	/* Operators */
	/**
	 * pre SetNature(M,x,y) requires 0 ≤ x < Width(M) and 0 ≤ y < Height(M)
	 */
	public EditMapService setNature(int x, int y, Cell c);
	
}
