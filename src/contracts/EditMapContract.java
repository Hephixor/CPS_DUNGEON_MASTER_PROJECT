package contracts;

import decorators.EditMapDecorator;
import errors.PostconditionError;
import errors.PreconditionError;
import javafx.util.Pair;
import services.EditMapService;
import utils.Cell;

public class EditMapContract extends EditMapDecorator{

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}
	
	/**
	 * isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
	 * 		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
     * 		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) != WLL
     * isReady(M) = exists xi,yi,xo,yo in int 4 ,
     * 		CellNature(M,xi,yi) = IN and CellNature(M,xo,yo) = OUT
     * 		and isReachable(M,xi,yi,xo,yo)
     * 		and forall x,y in int 2 , x != xi or y != yi implies CellNature(M,x,y) ! = IN
     * 		and forall x,y in int 2 , x != xo or y != yo implies CellNature(M,x,y) ! = OUT
     *   forall x,y in int, CellNature(M,x,y) ∈ { DNO, DNC} implies
     * 		CellNature(M,x+1,y) = CellNature(M,x-1,y) = EMP and
     * 		CellNature(M,x,y-1) = CellNature(M,x,y+1) = WLL
     * 	 forall x,y in int, CellNature(M,x,y) ∈ { DWO, DWC} implies
     * 		CellNature(M,x+1,y) = CellNature(M,x-1,y) = WLL and
     * 		CellNature(M,x,y-1) = CellNature(M,x,y+1) = EMP
	 */
	
	public void checkInvariants() {
//		Pair<Integer, Integer> p;
//		Pair<Integer, Integer>[] P = new Pair<Integer, Integer>[1000];
//		P[0] = new 
//		if(!(this. instanceof Integer))
//		
//			
	}
	
	public boolean isReady() {
		//pre 
		
		checkInvariants();
		
		return super.isReady();
	}
	/**
	 * pre SetNature(M,x,y) requires 0 ≤ x < Width(M) and 0 ≤ y < Height(M)
	 */
	public EditMapService setNature(int x, int y, Cell c) {
		//pre
		if(!(x>=0 && x<super.getWidth())|| !(y>=0 && y<super.getHeight())) {
			throw new PreconditionError("Error illegal coordinates");
		}
		
		checkInvariants();
		
		//capture
		
		
		super.setNature(x, y, c);
		
		checkInvariants();
		
		//Post
		//CellNature(SetNature(M,x,y,Na),x,y) = Na
		if(getCellNature(x, y)!=c) {
			throw new PostconditionError("Error while setting cell nature");
		}
		//TODO
		//forall u,v in int 2 , u 6 = x or v 6 = y implies CellNature(SetNature(M,x,y),u,v) = CellNature(M,u,v)
		
		return this;
	}

}
