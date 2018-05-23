package contracts;

import java.util.List;
import java.util.Random;

import decorators.EditMapDecorator;
import errors.InvariantError;
import errors.PostconditionError;
import errors.PreconditionError;
import services.EditMapService;
import utils.Cell;
import utils.Node;

public class EditMapContract extends EditMapDecorator{

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	public List<Node> getPath(){
		return super.getPath();
	}

	@Override
	public boolean isReachable(int inX, int inY, int outX, int outY){
		//pre
		if(getCellNature(inX, inY) == Cell.WLL)
			throw new InvariantError("isReachable start cell is a wall.");
		if(getCellNature(outX, outY) == Cell.WLL)
			throw new InvariantError("isReachable end cell is a wall.");

		//run
		return super.isReachable(inX, inY, outX, outY);
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

		/**
		 * isReachable
		 */

		Random rand = new Random();
		int randxi=0,randyi=0,randxo=0,randyo=0;
		Cell c = Cell.WLL;
		do{
			randxi = rand.nextInt(getWidth());
			randyi = rand.nextInt(getHeight());
			c = getCellNature(randxi, randyi);
		}while(c == Cell.WLL);

		do{
			randxo = rand.nextInt(getWidth());
			randyo = rand.nextInt(getHeight());
			c = getCellNature(randxo, randyo);
		}while(c == Cell.WLL);

		List<Node> path = null;

		/*
		 * isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
		 * 		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
		 * 		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) != WLL
		 * 
		 */

		if(isReachable(randxi, randyi, randxo, randyo)) {
			path=super.getPath();

			if(path==null) {
				throw new InvariantError("Path to exit is null");
			}

			for(int i = 1; i<path.size() ; i++) {
				int xb = path.get(i).x;
				int xa = path.get(i-1).x;
				int yb = path.get(i).y;
				int ya = path.get(i-1).y;

				if((Math.pow(xa-xb,2)+(Math.pow(yb-ya,2))!=1) || getCellNature(xa, ya)==Cell.WLL){
					throw new InvariantError("Path to destiantion has invalid move");
				}

			}

		}


		/**
		 * isReady
		 */

		/*
		 * CellNature(M,xi,yi) = IN and CellNature(M,xo,yo) = OUT
		 * 		and isReachable(M,xi,yi,xo,yo)
		 * 		and forall x,y in int 2 , x != xi or y != yi implies CellNature(M,x,y) ! = IN
		 * 		and forall x,y in int 2 , x != xo or y != yo implies CellNature(M,x,y) ! = OUT
		 * 
		 */

		if(isReady()){
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

					else if(getCellNature(i, j)==Cell.OUT) {
						out=true;
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
	}

	public EditMapService setNature(int x, int y, Cell c) {
		//pre
		if(!(x>=0 && x<getWidth())|| !(y>=0 && y<getHeight())) {
			throw new PreconditionError("Error illegal coordinates");
		}

		//inv pre
		checkInvariants();

		//capture
		Cell[][] map_atpre = new Cell[getHeight()][getWidth()];
		for(int i = 0 ; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				map_atpre[j][i]=getCellNature(i, j);	
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
					if(getCellNature(i, j)!=map_atpre[j][i]) {
						throw new PostconditionError("Error changed more than target cell.");
					}
				}
			}
		}

		return this;
	}

}
