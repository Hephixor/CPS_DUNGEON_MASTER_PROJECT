package contracts;

import java.util.Random;

import decorators.EditMapDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EditMapService;
import utils.Cell;

public class EditMapContract extends EditMapDecorator{

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	/**
	 * 
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
	 * 
	 */

	public void checkInvariants() {
		
		
		//isReachable
		Random rand = new Random();
		int randxi = rand.nextInt(getWidth());
		int randyi = rand.nextInt(getHeight());
		int randxo = rand.nextInt(getWidth());
		int randyo = rand.nextInt(getHeight());
		/*
		 * isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
		 * 		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
		 * 		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) != WLL
		 * 
		 */
		
		if(isReachable(randxi, randyi, randxo, randyo)) {
			
		}


		//isReady

		/*
		 * CellNature(M,xi,yi) = IN and CellNature(M,xo,yo) = OUT
		 * 		and isReachable(M,xi,yi,xo,yo)
		 * 		and forall x,y in int 2 , x != xi or y != yi implies CellNature(M,x,y) ! = IN
		 * 		and forall x,y in int 2 , x != xo or y != yo implies CellNature(M,x,y) ! = OUT
		 * 
		 */
		boolean in=false;
		boolean out=false;
		int inX=0,inY=0,outX=0,outY=0;

		for(int i = 0 ; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(getCellNature(i, j)==Cell.IN) {
					in=true;
					inX=i;
					inY=j;
				}

				if(getCellNature(i, j)==Cell.OUT) {
					out=false;
					outX=i;
					outY=j;
				}
			}
		}

		if(!in) {
			throw new InvariantError("Error missing entry on map.");
		}

		if(!out) {
			throw new InvariantError("Error missing exit on map.");
		}

		if(!isReachable(inX, inY, outX, outY)) {
			throw new InvariantError("Error no path from entry to exit.");
		}

		/*
		 *   forall x,y in int, CellNature(M,x,y) ∈ { DNO, DNC} implies
		 * 		CellNature(M,x+1,y) = CellNature(M,x-1,y) = EMP and
		 * 		CellNature(M,x,y-1) = CellNature(M,x,y+1) = WLL
		 * 	forall x,y in int, CellNature(M,x,y) ∈ { DWO, DWC} implies
		 *		CellNature(M,x+1,y) = CellNature(M,x-1,y) = WLL and
		 * 		CellNature(M,x,y-1) = CellNature(M,x,y+1) = EMP
		 */

		for(int i = 0 ; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {

				if(getCellNature(i, j)==Cell.DNO || getCellNature(i, j)==Cell.DNC) {

					if(getCellNature(i+1, j)!=getCellNature(i-1, j)|| getCellNature(i+1, j)!=Cell.EMP) {
						throw new InvariantError("Error door innaccessible.");
					}

					if(getCellNature(i , j-1)!=getCellNature(i, j+1)|| getCellNature(i, j-1)!=Cell.WLL) {
						throw new InvariantError("Error door is not attached to a wall.");
					}
				}

				if(getCellNature(i, j)==Cell.DWO || getCellNature(i, j)==Cell.DWC) {

					if(getCellNature(i+1, j)!=getCellNature(i-1, j)|| getCellNature(i+1, j)!=Cell.WLL) {
						throw new InvariantError("Error door is not attached to a wall.");
					}

					if(getCellNature(i , j-1)!=getCellNature(i, j+1)|| getCellNature(i, j-1)!=Cell.EMP) {
						throw new InvariantError("Error door innaccessible.");
					}
				}
			}
		}
	}

	public EditMapService setNature(int x, int y, Cell c) {
		//pre
		if(!(x>=0 && x<getWidth())|| !(y>=0 && y<getHeight())) {
			throw new PreconditionError("Error illegal coordinates");
		}

		//inv pre
		checkInvariants();

		//capture
		Cell[][] map_atpre = new Cell[getWidth()][getHeight()];
		for(int i = 0 ; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				map_atpre[i][j]=getCellNature(i, j);	
			}
		}


		//run
		super.setNature(x, y, c);

		//inv post
		checkInvariants();

		//post
		//CellNature(SetNature(M,x,y,Na),x,y) = Na
		if(getCellNature(x, y)!=c) {
			throw new PostconditionError("Error while setting cell nature");
		}
		
		//forall u,v in int 2 , u 6 = x or v 6 = y implies CellNature(SetNature(M,x,y),u,v) = CellNature(M,u,v)
		for(int i = 0 ; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(i!=x || j!=y) {
					if(getCellNature(i, j)!=map_atpre[i][j]) {
						throw new PostconditionError("Error changed more than target cell.");
					}
				}
			}
		}

		return this;
	}

}
