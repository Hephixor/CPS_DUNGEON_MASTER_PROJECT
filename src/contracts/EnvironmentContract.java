package contracts;

import java.util.Arrays;

import services.EnvironmentService;
import services.MapService;
import services.MobService;
import utils.Cell;
import decorators.EnvironmentDecorator;
import errors.PostconditionError;
import errors.PreconditionError;

public class EnvironmentContract extends EnvironmentDecorator{

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}


	//include et non refine donc on n'hérite pas des invariants de MapService, il faut refaire des tests.
	public void checkInvariants() {

	}

	
	
	@Override
	public MobService getCellContent(int x, int y) {
		//pre
		if( Arrays.asList(Cell.WLL, Cell.DNC, Cell.DWC).contains(getCellNature(x,y)) )
			throw new PreconditionError("getCellContent d'une cellule WLL DNC ou DWC");
		
		//run
		return super.getCellContent(x, y);
	}

	@Override
	public void closeDoor(int x, int y) {
		//pre
		if(getCellContent(x,y)!=null) {
			throw new PreconditionError("Can't close door on a mob");
		}

		//inv pre
		checkInvariants();

		//run
		super.closeDoor(x, y);

		//inv post
		checkInvariants();

		//post

	}

	public void init(MapService map){
		//pre

		//run
		super.init(map);

		checkInvariants();

		//post

	}

	public void setCellContent(int x, int y, MobService mob){
		//pre
		
		Cell nature = getCellNature(x, y);
		//System.out.println("setCellContent "+nature);
		if(Arrays.asList(Cell.WLL, Cell.DNC, Cell.DWC).contains(nature)){
			throw new PreconditionError("cannot place mob in a wall or closed door.");
		}
		

//		if(getCellContent(x, y)!=null && mob!=null){
//			throw new PreconditionError("Cell is already occupied by a mob");
//		}
		
		//invariants
		checkInvariants();
		
		//capture
		MobService[][] map_atpre = new MobService[getHeight()][getWidth()];
				for(int i = 0 ; i < getWidth(); i++) {
					for(int j = 0; j < getHeight(); j++) {
						map_atpre[j][i]=getCellContent(i, j);	
					}
				}

		
		//run
		super.setCellContent(x, y, mob);
		
		//invariants
		checkInvariants();
		
		//post
		
		if(getCellContent(x, y)!=mob){
			throw new PostconditionError("Error placing mob");
		}
		
		//forall u,v in int 2 , u 6 = x or v 6 = y implies CellContent(setCellContent(M,x,y),u,v) = CellContent(M,u,v)
				for(int i = 0 ; i < getWidth(); i++) {
					for(int j = 0; j < getHeight(); j++) {
						if(i!=x || j!=y) {
							if(getCellContent(i, j)!=map_atpre[j][i]) {
								throw new PostconditionError("Error changed more than targeted cell's content.");
							}
						}
					}
				}
		
	}
}
