package contracts;

import org.omg.CORBA.portable.Delegate;

import decorators.EditMapDecorator;
import javafx.util.Pair;
import services.EditMapService;

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
	
	public void checkInvariant() {
//		Pair<Integer, Integer> p;
//		Pair<Integer, Integer>[] P = new Pair<Integer, Integer>[1000];
//		P[0] = new 
//		if(!(this. instanceof Integer))
//		
//		
		
	}

}
