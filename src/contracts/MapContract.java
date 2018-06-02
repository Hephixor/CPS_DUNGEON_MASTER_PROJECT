package contracts;

import services.MapService;
import utils.Cell;
import decorators.MapDecorator;
import errors.PostconditionError;
import errors.PreconditionError;

public class MapContract extends MapDecorator {

	public MapContract(MapService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {

	}

	/*========== preconditions des operateurs ==========*/
	
	public void preGetCellNature(int x, int y) {
		if( !( 0<=x && x<getWidth() && 0<= y && y<getHeight() ) ) {
			throw new PreconditionError("Illegal coordinates:"+x+"!<"+getWidth()+" "+y+"!<"+getHeight());
		}
	}
	
	public void preInit(int w, int h) {
		if( !( 0<w && 0<h) )
			throw new PreconditionError("Map height and width should be greater than 0.");
	}
	
	public void preOpenDoor(int x, int y) {
		//dependances
		preGetCellNature(x, y);
		
		//pre
		if(!(getCellNature(x,y)== Cell.DWC || getCellNature(x, y)==Cell.DNC))
			throw new PreconditionError("Target cell is not a closed door.");
	}
	
	public void preCloseDoor(int x, int y) {
		//dependances
		preGetCellNature(x, y);
		
		//pre
		if(!(getCellNature(x,y)== Cell.DWO || getCellNature(x, y)==Cell.DNO))
			throw new PreconditionError("Target cell is not a closed door.");
	}
	
	
	/*========== postconditions des operateurs ==========*/
	
	public void postInit(int w, int h) {
		//Height(init(w,h)) = h
		if( !(getHeight()==h) )
			throw new PostconditionError("Could not set map height properly.");
		
		//Width(init(w,h)) = w
		if( !(getWidth()==w) )
			throw new PostconditionError("Could not set map width properly.");
	}
	
	public void postOpenDoor(int x, int y, Cell cell_atpre, 
			Cell cellA_atpre, Cell cellB_atpre, Cell cellC_atpre, Cell cellD_atpre) {
		if(cell_atpre == Cell.DWC) if( !(getCellNature(x, y) == Cell.DWO) ) 
			throw new PostconditionError("openDoor(door DWC) != door DWO");
		
		if(cell_atpre == Cell.DNC) if( !(getCellNature(x, y) == Cell.DNO) ) 
			throw new PostconditionError("openDoor(door DNC) != door DNO");
		
		if(x-1 >= 0) if( !(cellA_atpre == getCellNature(x-1, y)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
		if(x+1 < getWidth()) if( !(cellB_atpre == getCellNature(x+1, y)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
		if(y-1 >= 0) if( !(cellC_atpre == getCellNature(x, y-1)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
		if(y+1 < getHeight()) if( !(cellD_atpre == getCellNature(x, y+1)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
	}
	
	public void postCloseDoor(int x, int y, Cell cell_atpre, 
			Cell cellA_atpre, Cell cellB_atpre, Cell cellC_atpre, Cell cellD_atpre) {
		if(cell_atpre == Cell.DWO) if( !(getCellNature(x, y) == Cell.DWC) ) 
			throw new PostconditionError("openDoor(door DWC) != door DWO");
		
		if(cell_atpre == Cell.DNO) if( !(getCellNature(x, y) == Cell.DNC) ) 
			throw new PostconditionError("openDoor(door DNC) != door DNO");
		
		if(x-1 >= 0) if( !(cellA_atpre == getCellNature(x-1, y)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
		if(x+1 < getWidth()) if( !(cellB_atpre == getCellNature(x+1, y)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
		if(y-1 >= 0) if( !(cellC_atpre == getCellNature(x, y-1)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
		if(y+1 < getHeight()) if( !(cellD_atpre == getCellNature(x, y+1)) )
			throw new PostconditionError("openDoor also changed another cell's nature.");
	}
	
	
	/*========== operateurs ==========*/
	
	@Override
	public Cell getCellNature(int x, int y) {
		//pre
		preGetCellNature(x, y);
		
		//run
		return super.getCellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		System.out.println("aa");
		//pre
		preInit(w, h);
		
		//inv pre
		checkInvariant();
		
		//run
		super.init(w,h);
		
		//inv post
		checkInvariant();
		
		//post
		postInit(w, h);
	}

	@Override
	public void openDoor(int x, int y) {
		//pre
		preOpenDoor(x, y);
		
		//inv pre
		checkInvariant();

		//capture
		Cell cell_atpre = getCellNature(x, y);
		
		Cell cellA_atpre=null; 
		Cell cellB_atpre=null;
		Cell cellC_atpre=null;
		Cell cellD_atpre=null;
		if(x-1 >= 0) cellA_atpre = getCellNature(x-1, y);
		if(x+1 < getWidth()) cellB_atpre = getCellNature(x+1, y);
		if(y-1 >= 0) cellC_atpre = getCellNature(x, y-1);
		if(y+1 < getHeight()) cellD_atpre = getCellNature(x, y+1);
		
		//run
		super.openDoor(x, y);
		
		//inv post
		checkInvariant();
		
		//post
		postOpenDoor(x, y, cell_atpre, cellA_atpre, cellB_atpre, cellC_atpre, cellD_atpre);
		
	}

	@Override
	public void closeDoor(int x, int y) {
		//pre
		preCloseDoor(x, y);
		
		//inv pre
		checkInvariant();

		//capture
		Cell cell_atpre = getCellNature(x, y);
		
		Cell cellA_atpre=null; 
		Cell cellB_atpre=null;
		Cell cellC_atpre=null;
		Cell cellD_atpre=null;
		if(x-1 >= 0) cellA_atpre = getCellNature(x-1, y);
		if(x+1 < getWidth()) cellB_atpre = getCellNature(x+1, y);
		if(y-1 >= 0) cellC_atpre = getCellNature(x, y-1);
		if(y+1 < getHeight()) cellD_atpre = getCellNature(x, y+1);
		
		//run
		super.closeDoor(x, y);
		
		//inv post
		checkInvariant();
		
		//post
		postCloseDoor(x, y, cell_atpre, cellA_atpre, cellB_atpre, cellC_atpre, cellD_atpre);
	}
}
