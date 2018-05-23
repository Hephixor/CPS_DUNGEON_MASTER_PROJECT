package services;

import utils.Cell;

public interface MapService {

	/*observators*/
	
	public int getHeight();
	
	public int getWidth();
	
	/**
	 * pre: 0 <= x < Width(M) and 0 <= y < Height(M)
	 */
	public Cell getCellNature(int x, int y);
	
	
	/*constructors*/
	
	/**
	 * pre: 0 < w and 0 < h
	 * post: Height(init(w,h)) = h
	 * post: Width(init(w,h)) = w
	 */
	public void init(int w, int h);
	
	
	/*operators*/
	
	
	/**
	 * pre: CellNature(M,x,y) in {DNC, DWC}
	 * post:	CellNature(M,x,y) = DWC implies CellNature(OpenDoor(M,x,y),x,y) = DWO
	 * post:	CellNature(M,x,y) = DNC implies CellNature(OpenDoor(M,x,y),x,y) = DNO
	 * post:	forall u in [0; Width(M)-1] 
	 *				forall v in [0; Height(M)-1] 
	 *					(u != x or v != y) implies CellNature(OpenDoor(M,x,y),u,v) = CellNature(M,u,v)
	 */
	public void openDoor(int x, int y);
	
	
	/**
	 * pre: CellNature(M,x,y) in {DNO, DWO}
	 * post:	CellNature(M,x,y) = DWO implies CellNature(CloseDoor(M,x,y),x,y) = DWC
	 * post:	CellNature(M,x,y) = DNO implies CellNature(CloseDoor(M,x,y),x,y) = DNC
	 * post:	forall u in [0; Width(M)-1] 
	 *				forall v in [0; Height(M)-1] 
	 *					(u != x or v != y) implies CellNature(CloseDoor(M,x,y),u,v) = CellNature(M,u,v)
	 */
	public void closeDoor(int x, int y);
		
	
	
}
