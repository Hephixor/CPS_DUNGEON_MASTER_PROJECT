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

	
	/*========== invariants ==========*/
	
	public void checkInvariants() {
		//inv dependances
		MapContract map = new MapContract(this);
		map.checkInvariant();
	}
	
	
	/*========== preconditions ==========*/
	
	public void preOpenDoor(int x, int y) {
		//dependances
		MapContract map = new MapContract(this);
		map.preOpenDoor(x, y);
	}
	
	public void preCloseDoor(int x, int y) {
		//dependances
		MapContract map = new MapContract(this);
		map.preCloseDoor(x, y);
		
		//pre
		if(getCellContent(x,y)!=null)
			throw new PreconditionError("Can't close door on a mob");
	}
	
	public void preSetCellContent(int x, int y, MobService mob) {
		//pre dependances
		MapContract map = new MapContract(this);
		map.preGetCellNature(x, y);
		
		//pre
		Cell nature = getCellNature(x, y);

		if(Arrays.asList(Cell.WLL, Cell.DNC, Cell.DWC).contains(nature))
			throw new PreconditionError("cannot place mob in a wall or closed door.");
		
		if(getCellContent(x, y)!=null && mob!=null)
			throw new PreconditionError("Cell is already occupied by a mob");
	}
	
	/*========== postconditions ==========*/
	
	public void postOpenDoor(int x, int y, Cell cell_atpre, 
			Cell cellA_atpre, Cell cellB_atpre, Cell cellC_atpre, Cell cellD_atpre) {
		//dependances
		MapContract map = new MapContract(this);
		map.postOpenDoor(x, y, cell_atpre, cellA_atpre, cellB_atpre, cellC_atpre, cellD_atpre);
	}
	
	public void postCloseDoor(int x, int y, Cell cell_atpre, 
			Cell cellA_atpre, Cell cellB_atpre, Cell cellC_atpre, Cell cellD_atpre) {
		//dependances
		MapContract map = new MapContract(this);
		map.postCloseDoor(x, y, cell_atpre, cellA_atpre, cellB_atpre, cellC_atpre, cellD_atpre);
	}
	
	public void postSetCellContent(int x, int y, MobService mob, MobService[][] map_atpre) {
		//post
		if(getCellContent(x, y)!=mob){
			throw new PostconditionError("Error placing mob");
		}
		
		//forall u,v in int^2 , u != x or v != y implies CellContent(setCellContent(M,x,y),u,v) = CellContent(M,u,v)
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
	
	/*========== observateurs ==========*/
	
	@Override
	public MobService getCellContent(int x, int y) {
		//pre

		//run
		return super.getCellContent(x, y);
	}
	
	/*========== constructeurs ==========*/
	
	@Override
	public void init(MapService map){
		//pre

		//run
		super.init(map);

		checkInvariants();

		//post

	}
	
	/*========== operateurs ==========*/

	@Override
	public void openDoor(int x, int y) {
		//pre
		preOpenDoor(x, y);

		//inv pre
		checkInvariants();
		
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
		checkInvariants();

		//post
		postOpenDoor(x, y, cell_atpre, cellA_atpre, cellB_atpre, cellC_atpre, cellD_atpre);

	}
	
	@Override
	public void closeDoor(int x, int y) {
		//pre
		preCloseDoor(x, y);

		//inv pre
		checkInvariants();
		
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
		checkInvariants();

		//post
		postCloseDoor(x, y, cell_atpre, cellA_atpre, cellB_atpre, cellC_atpre, cellD_atpre);

	}

	@Override
	public void setCellContent(int x, int y, MobService mob){
		//pre
		preSetCellContent(x, y, mob);
		
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
		postSetCellContent(x, y, mob, map_atpre);
	}
}
