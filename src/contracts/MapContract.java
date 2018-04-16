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

	@Override
	public Cell getCellNature(int x, int y) {
		//pre: 0 <= x < Width(M) and 0 <= y < Height(M)
		if( !( 0<=x && x<getHeight() && 0<= y && y<getWidth() ) )
			throw new PreconditionError("Illegal coordinates");
		
		//run
		return super.getCellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		//pre
		if( !( 0<w && 0<h) )
			throw new PreconditionError("Map height and width should be greater than 0.");
		
		//inv pre
		checkInvariant();
		
		//run
		super.init(w,h);
		
		//inv post
		checkInvariant();
		
		//post
		
		//Height(init(w,h)) = h
		if( !(getHeight()==h) )
			throw new PostconditionError("Could not set map height properly.");
		
		//Width(init(w,h)) = w
		if( !(getWidth()==w) )
			throw new PostconditionError("Could not set map width properly.");
	}

	@Override
	public MapService openDoor(int x, int y) {
		//pre
		if(!(getCellNature(x,y)== Cell.DWC || getCellNature(x, y)==Cell.DNC))
			throw new PreconditionError("Target cell is not a closed door.");
		
		//inv pre
		checkInvariant();
		
		//capture
		Cell cell_atpre = getCellNature(getHeight()-1, getWidth()-1);
		
		Cell cellb_atpre = null;
		if((x-1)>=0 && (y-1)>=0) {
		cellb_atpre = getCellNature(x-1, y-1);
		}
		else {
		cellb_atpre= getCellNature(x+1, y+1);	
		}
		
		//run
		super.openDoor(x, y);
		
		//inv post
		checkInvariant();
		
		//post
		if(!(getCellNature(x,y)== Cell.DWO || getCellNature(x, y)==Cell.DNO))
			throw new PostconditionError("Target cell didn't open correctly.");
		
		if((x-1)>=0 && (y-1)>=0) {
			if(getCellNature(x-1,y-1)!=cellb_atpre) {
				throw new PostconditionError("Open door modified a cell other than the door.");
			}
		}
		else {
			if(getCellNature(x+1,y+1)!=cellb_atpre) {
				throw new PostconditionError("Open door modified a cell other than the door.");
			}
		}
		
		if(getCellNature(getHeight()-1, getWidth()-1)!=cell_atpre) {
			throw new PostconditionError("Open door modified a cell other than the door.");
		}
		
		return this;
		
	}

	@Override
	public MapService closeDoor(int x, int y) {
		//pre
		if(!(getCellNature(x,y)== Cell.DWO || getCellNature(x, y)==Cell.DNO))
			throw new PreconditionError("Target cell is not an opened door.");
		
		//inv pre
		checkInvariant();
		
		//capture
		Cell cell_atpre = getCellNature(getHeight()-1, getWidth()-1);
		
		Cell cellb_atpre = null;
		if((x-1)>=0 && (y-1)>=0) {
		cellb_atpre = getCellNature(x-1, y-1);
		}
		else {
		cellb_atpre= getCellNature(x+1, y+1);	
		}
		
		//run
		super.closeDoor(x, y);
		
		//inv post
		checkInvariant();
		
		//post
		if(!(getCellNature(x,y)== Cell.DWC || getCellNature(x, y)==Cell.DNC))
			throw new PostconditionError("Target cell didn't close correctly.");
		
		if((x-1)>=0 && (y-1)>=0) {
			if(getCellNature(x-1,y-1)!=cellb_atpre) {
				throw new PostconditionError("Close door modified a cell other than the door.");
			}
		}
		else {
			if(getCellNature(x+1,y+1)!=cellb_atpre) {
				throw new PostconditionError("Close door modified a cell other than the door.");
			}
		}
		
		if(getCellNature(getHeight()-1, getWidth()-1)!=cell_atpre) {
			throw new PostconditionError("Close door modified a cell other than the door.");
		}
		
		return this;
	}
}
